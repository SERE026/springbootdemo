package com.first.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 
 * @ClassName: RedisCache
 * @Description: TODO(使用第三方缓存服务器redis，处理二级缓存)
 *
 */
public class RedisCache implements Cache {
	private final Logger LOG = LoggerFactory.getLogger(RedisCache.class);

	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("必须传入ID");
		}
		LOG.debug("MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getSize() {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		int result = 0;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			result = Integer.valueOf(jedis.dbSize().toString());
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		return result;

	}

	@Override
	public void putObject(Object key, Object value) {
		if (LOG.isDebugEnabled())
			LOG.debug("putObject:" + key.hashCode() + "=" + value);
		if (LOG.isInfoEnabled())
			LOG.info("put to redis sql :" + key.toString());
		Jedis jedis = null;
		JedisPool jedisPool = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			jedis.set(SerializeUtil.serialize(key.hashCode()), SerializeUtil.serialize(value));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}

	}

	@Override
	public Object getObject(Object key) {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		Object value = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			value = SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key.hashCode())));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		if (LOG.isDebugEnabled())
			LOG.debug("getObject:" + key.hashCode() + "=" + value);
		return value;
	}

	@Override
	public Object removeObject(Object key) {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		Object value = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			value = jedis.expire(SerializeUtil.serialize(key.hashCode()), 0);
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		if (LOG.isDebugEnabled())
			LOG.debug("getObject:" + key.hashCode() + "=" + value);
		return value;
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = CachePool.getInstance().getJedis();
			jedisPool = CachePool.getInstance().getJedisPool();
			jedis.flushDB();
			jedis.flushAll();
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public static class CachePool {
		JedisPool pool;
		private static final CachePool cachePool = new CachePool();

		public static CachePool getInstance() {
			return cachePool;
		}

		private CachePool() {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(100);
			config.setMaxIdle(10);
			config.setNumTestsPerEvictionRun(30000);
			config.setTimeBetweenEvictionRunsMillis(1800000);
			config.setSoftMinEvictableIdleTimeMillis(10000);
			config.setMaxWaitMillis(1500);
			config.setTestOnBorrow(true);
			config.setTestWhileIdle(true);
			config.setBlockWhenExhausted(false);
			
			pool = new JedisPool(config, "192.168.1.222",6379,1500,"foobared");
		}

		public Jedis getJedis() {
			Jedis jedis = null;
			boolean borrowOrOprSuccess = true;
			try {
				jedis = pool.getResource();
			} catch (JedisConnectionException e) {
				borrowOrOprSuccess = false;
				if (jedis != null)
					pool.returnBrokenResource(jedis);
			} finally {
				if (borrowOrOprSuccess)
					pool.returnResource(jedis);
			}
			jedis = pool.getResource();
			return jedis;
		}

		public JedisPool getJedisPool() {
			return this.pool;
		}

	}

	public static class SerializeUtil {
		public static byte[] serialize(Object object) {
			ObjectOutputStream oos = null;
			ByteArrayOutputStream baos = null;
			try {
				// 序列化
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				byte[] bytes = baos.toByteArray();
				return bytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public static Object unserialize(byte[] bytes) {
			if (bytes == null)
				return null;
			ByteArrayInputStream bais = null;
			try {
				// 反序列化
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
