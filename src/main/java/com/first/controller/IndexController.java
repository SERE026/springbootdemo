package com.first.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.first.model.User;

import freemarker.template.Configuration;

//@RestController
@Controller
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private Configuration configuration;
	
	@ResponseBody
	@RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        Integer r = a + b;
        logger.info("/add, host: service_id: result:" + r);
        return r;
    }
	
	
	@RequestMapping(value = "/get" ,method = RequestMethod.POST)
    public Map<String,User> get(@RequestParam String id) {
		Map<String,User> map = new HashMap<String,User>();
        logger.info("/add, host: service_id: result:" + map);
        for(int i=0;i<3;i++){
	        User user = new User();
	        user.setId(i);
	        user.setNickname("SX"+i);
	        map.put("users"+i, user);
        }
        return map;
    }
	
	@RequestMapping(value = "/f/get")
    public Map<String,User> fget(@RequestParam String id) {
		Map<String,User> map = new HashMap<String,User>();
        logger.info("/add, host: service_id: result:" + map);
        for(int i=0;i<3;i++){
	        User user = new User();
	        user.setId(i);
	        user.setNickname("SX"+i);
	        map.put("users"+i, user);
        }
        return map;
    }
	
	@RequestMapping(value = "/test")
    public String test(Map<String,Object> map) {
        map.put("name","test");
        map.put("age","20");
        logger.info("/add, host: service_id: result:" + map);
//        freeMarkerConfig.resolveMap(map, "test.ftl");
        return "test";
    }
	
	/**
	 * freemarker 模板生成静态页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index1")
	public ModelAndView index1(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("index");
	    User user = new User();
	    user.setNickname("<h2>小明</h2>");
	    user.setPassword("<a href='https://www.hao123.com/'>百度</a>");
	    List<User> users = new ArrayList<User>();
	    users.add(user);
	    mav.addObject("users", users);
	    return mav;
	}

	/**
	 * freemarker 不生成静态页面
	 * @return
	 */
	@RequestMapping("/index2")
	public ModelAndView index2(HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView("index");
	    mav.addObject("title", "网站标题");
	    //说明：在这里可以控制不生成静态htm
	    mav.addObject("CREATE_HTML", false);
	    return mav;
	}
}
