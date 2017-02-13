package com.first.dao.provider;

import org.apache.ibatis.mapping.MappedStatement;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class SelectWithLockProvider extends MapperTemplate {


	    public SelectWithLockProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
	        super(mapperClass, mapperHelper);
	    }

	    /**
	     * 根据ID 带锁方法
	     *
	     * @param ms
	     */
	    public String selectByPrimaryKeyWithLock(MappedStatement ms) {
	        final Class<?> entityClass = getEntityClass(ms);
	        //灏嗚繑鍥炲�间慨鏀逛负瀹炰綋绫诲瀷
	        setResultType(ms, entityClass);
	        StringBuilder sql = new StringBuilder();
	        sql.append(SqlHelper.selectAllColumns(entityClass));
	        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
	        sql.append(SqlHelper.wherePKColumns(entityClass));
	        sql.append("for update");
	        return sql.toString();
	    }
	}

