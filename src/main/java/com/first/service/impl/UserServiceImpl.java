package com.first.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.first.dao.mapper.UserMapper;
import com.first.model.User;
import com.first.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


@Service
@Transactional(readOnly=false,rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public int save(User user) {
		int r = userMapper.insert(user);
		mongoTemplate.insert(user);
		return r;
	}

	@Override
	public User selectByPrimaryKeyWithLock(Integer id) {
		return userMapper.selectByPrimaryKeyWithLock(id);
	}

	@Override
	public Page<User> select(User user,Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("id desc");
		Page<User> page = (Page<User>) userMapper.select(user);
		LOG.info("--------分页查询-------{}",ReflectionToStringBuilder.toString(page));
		return page;
	}

	@Override
	//将缓存保存进andCache，并当参数pageNo小于10时才保存进缓存，默认使用参数值及类型作为缓存的key    
	@Cacheable(value="select",key="#param.get(\"idcard\")",/*keyGenerator = "wiselyKeyGenerator",*/condition="#pageNo <10 && #pageSize<=5") 
	public Page<Map<String, Object>> select(Map<String, Object> param, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		PageHelper.orderBy("u.id desc");
		Page<Map<String,Object>> page =  (Page<Map<String, Object>>) userMapper.queryMapList(param);
		LOG.info("--------分页查询-------{}",ReflectionToStringBuilder.toString(page));
		return page;
	}

	
	@Override
	public List<User> selectFromMongo(User user, final Integer lastId, Integer pageSize,Integer sort) {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		
		// 条件查询
		if(user!=null && StringUtils.isNotBlank(user.getNickname())){
			//完全匹配
			Pattern pattern = Pattern.compile("^"+user.getNickname()+"$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("nickname").regex(pattern));
	    }
		
		/*if(user!=null && StringUtils.isNotBlank(user.getEmail())){
			
			//左匹配 
	        Pattern pattern = Pattern.compile("^.*"+user.getEmail()+"$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("email").regex(pattern));
	    }*/
		
		/*if(user!=null && StringUtils.isNotBlank(user.getEmail())){
			
			//右匹配 
	        Pattern pattern = Pattern.compile("^"+user.getEmail()+".*$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("email").regex(pattern));
	    }*/
		
		
		if(user!=null && StringUtils.isNotBlank(user.getEmail())){
				
			//模糊匹配  *yinchengmall*
	        Pattern pattern = Pattern.compile("^.*"+user.getEmail()+".*$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("email").regex(pattern));
	    }
		
		//db.emps.find({"$and":[{"age":{"$gt":20}},{"family":{"$elemMatch":{"job":"科长"}}}]}).pretty();
		//query.addCriteria(Criteria.where("family").elemMatch(Criteria.where("job").is("科长")));
		
		// 分页的时候，where条件和排序容易发生逻辑错误，编码时要注意 如果是大于lastId，则应该顺序，如果是小于lastId ，则应该倒序
		List<Order> orders = new ArrayList<Order>();
		if(sort==1) {
			query.addCriteria(Criteria.where("id").gt(lastId));
			orders.add(new Order(Direction.ASC, "id"));
		} else {
			query.addCriteria(Criteria.where("id").lt(lastId));
			orders.add(new Order(Direction.DESC, "id"));
		}
		
		
		Sort dsort = new Sort(orders);
		query.with(dsort);
		
		query.limit(pageSize);
		
		List<User> list = mongoTemplate.find(query, User.class);
		
		Long count = mongoTemplate.count(Query.query(Criteria.where("id").exists(true)),User.class);
		LOG.info("count---->{}",count);
		return list;
	}

	@Override
	public Map selectFromMongoGroupByCity(User user, Integer lastId, Integer pageSize, Integer sort) {
		// TODO Auto-generated method stub
		Query query = new Query();
		
		query.addCriteria(Criteria.where("city").exists(true));
		
		String reduce = "function(doc, aggr){" +
                "            aggr.count += 1" +
                "        }";
		
		DBObject result = mongoTemplate.getCollection("user").group(new BasicDBObject("city", 1), 
                query.getQueryObject(), 
                new BasicDBObject("count", 0),
                reduce);
		return result.toMap();
	}

}
