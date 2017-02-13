package com.first.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.util.SignUtil;

@RestController
public class WeixinController {

	private final Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
	
	@RequestMapping(value = "/wechat/security", method = RequestMethod.GET)
    public void security(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

	// post 方法用于接收微信服务端消息
    @RequestMapping(value = "security", method = RequestMethod.POST)
    public void security() {
        System.out.println("这是 post 方法！");
    }
}
