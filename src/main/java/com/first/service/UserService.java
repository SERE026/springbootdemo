package com.first.service;

import java.util.List;
import java.util.Map;

import com.first.model.User;
import com.github.pagehelper.Page;

public interface UserService {
	
	public int save(User user);
	
	public User selectByPrimaryKeyWithLock(Integer id);
	
	public Page<User> select(User user,Integer pageNo,Integer pageSize);
	
	/**
	 * mongo 分页
	 * @param user
	 * @param lastId 上一页最近的ID
	 * @param pageSize
	 * @param sort 1:正序 -1,:倒序
	 * @return
	 */
	public List<User> selectFromMongo(User user,Integer lastId,Integer pageSize,Integer sort);
	
	public Map selectFromMongoGroupByCity(User user,Integer lastId,Integer pageSize,Integer sort);
	
	public Page<Map<String,Object>> select(Map<String,Object> param,Integer pageNo,Integer pageSize);
	
}
