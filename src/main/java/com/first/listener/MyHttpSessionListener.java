package com.first.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		// TODO Auto-generated method stub
		System.out.println("Session 被创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		// TODO Auto-generated method stub
		 System.out.println("Session 被销毁");
	}

}
