package com.first.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.model.User;
import com.first.service.UserService;
import com.github.pagehelper.Page;

@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/save" ,method = RequestMethod.POST)
    public Map<String,Object> save(User user) {
		Map<String,Object> map = new HashMap<String,Object>();
        logger.info("/saveUser:" + map);
        Date  date = new Date();
        user.setCurLoginDate(date);
        user.setLastLoginDate(date);
        user.setCity(1);
        user.setProvince(1);
        user.setIdcard("13520987898");
        user.setPassword("123456");
        
        int i = userService.save(user);
        map.put("id", user.getId());
        map.put("result", i);
        map.put("msg", "OOOO");
        return map;
    }
	
	@RequestMapping(value = "/user/get" ,method = RequestMethod.GET)
    public User get(@RequestParam Integer id) {
		User user = userService.selectByPrimaryKeyWithLock(id);
        logger.info("/getUser:" + user);
        return user;
    }
	
	@RequestMapping(value = "/user/list" ,method = RequestMethod.GET)
    public Map<String,Object> list(Integer pageNo,Integer pageSize) {
		
		User user = new User();
		user.setIdcard("13520987898");
		
		Page<User> page= userService.select(new User(), pageNo, pageSize);
        logger.info("/getUser:" + page);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data", page.getResult());
        map.put("count", page.getTotal());
        return map;
    }
	
	@RequestMapping(value = "/user/listmap" ,method = RequestMethod.GET)
    public Map<String,Object> listmap(Integer pageNo,Integer pageSize) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("idcard", "13520987898");
		param.put("password", "123456");
		
		Page<Map<String,Object>> page= userService.select(param, pageNo, pageSize);
        logger.info("/getUser:" + page);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data", page.getResult());
        map.put("count", page.getTotal());
        return map;
    }
	
	@RequestMapping(value = "/user/mlist" ,method = RequestMethod.GET)
    public List<User> mlist(User user,Integer lastId,Integer pageSize,Integer sort) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("idcard", "13520987898");
		param.put("password", "123456");
		
		List<User> list = userService.selectFromMongo(user, lastId, pageSize,sort);
        logger.info("/getUser:" + ReflectionToStringBuilder.toString(list));
        return list;
    }
	
	@RequestMapping(value = "/user/group" ,method = RequestMethod.GET)
    public Map group(User user,Integer lastId,Integer pageSize,Integer sort) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("idcard", "13520987898");
		param.put("password", "123456");
		
		Map map = userService.selectFromMongoGroupByCity(user, lastId, pageSize, sort);
        logger.info("/getUser:" + ReflectionToStringBuilder.toString(map));
        return map;
    }
	
	@RequestMapping(value = "/test/first", method = RequestMethod.GET)
    public Map<String, Object> testfirst (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        Page<User> page= userService.select(new User(), 1, 100);
        logger.info("/getUser:" + page);
        map.put("data", page.getResult());
        map.put("count", page.getTotal());
        map.put("sessionId", request.getSession().getId());
        //DelegatingFilterProxy
        //放入session
        request.getSession().setAttribute("userData", map);
        return map;
    }
	
	@RequestMapping(value = "/test/sessions", method = RequestMethod.GET)
    public Object testsessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("userData"));
        return map;
    }
}
