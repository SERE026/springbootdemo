package com.first.dao.mapper;

import java.util.List;
import java.util.Map;

import com.first.dao.base.CommonMapper;
import com.first.model.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>, CommonMapper<User> {
	
	public List<Map<String,Object>> queryMapList(Map<String,Object> param);

}