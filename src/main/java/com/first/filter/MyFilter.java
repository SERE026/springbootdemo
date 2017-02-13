package com.first.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter {

    @Override
    public void destroy() {
        //System.out.println("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        //System.out.println("执行过滤操作");
        
        HttpServletResponse hresponse = (HttpServletResponse) response;
        hresponse.setHeader("Access-Control-Allow-Origin", "*");
		hresponse.setHeader("Access-Control-Allow-Credentials", "true");
//		hresponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");  
        hresponse.setHeader("Access-Control-Max-Age", "3456000");  
        hresponse.setHeader("Access-Control-Allow-Headers", "JSESSIONID,SESSIONID,session,sessions,version,token,codetype,signtype,codeType,signType,IMSI,IMEI,browsor,imei,imsi,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type"); 
        hresponse.setHeader("Content-Type", "text/plain charset=UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        //System.out.println("过滤器初始化");
    }

}
