package com.qq.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qq.util.weixin.WxUtil;

/**
 * @Title:
 * @Description:
 * @Author: liyang.a
 * @Since: 2018年5月14日
 * @Version: 1.1.0
 */
@Controller
public class TestController {
    Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/index")
    public String index() {
	log.debug("1231231231");
	log.info("231");
	log.error("123");
	System.out.println(12312);
	return "hello";
    }

    /**
     * 
     * @param userOpenId
     *            用户openId
     * @param message
     *            发送的消息内容
     * @param exceptionStr
     *            测试字段
     * @return
     * @Description: 客服发送文本消息
     */
    @RequestMapping("/sendCustomerText")
    public Object sendCustomerText(String userOpenId, String message, String exceptionStr) {
	log.debug("sendCustomerText接口参数：userOpenId:" + userOpenId + ",message=" + message + ",exceptionStr="
		+ exceptionStr);
	String result = null;
	String token = null;
	Map<String, String> map = new HashMap<String, String>();
	try {
	    token = WxUtil.getAccessToken();
	    log.debug("获取token:" + token);
	    log.debug("获取exceptionStr长度:" + exceptionStr.length());
	    log.debug("截取exceptionStr:" + exceptionStr.substring(0, 5));
	    result = WxUtil.sendMessage(token, userOpenId, message, null);
	    map.put("result", result);
	} catch (Exception e) {
	    log.error("sendCustomerText接口发生异常", e);
	    result = WxUtil.sendMessage(token, userOpenId, message, e);
	    map.put("result", result);
	}
	return new ModelAndView("result", map);
    }

    /**
     * 
     * @param userOpenId
     *            用户openId
     * @param mediaId
     *            上传的图片媒体id
     * @return
     * @Description: 发送图片
     */
    @RequestMapping("/sendCustomerImage")
    public Object sendCustomerImage(String userOpenId, String mediaId) {
	log.debug("sendCustomerImage接口参数：userOpenId:" + userOpenId + ",mediaId=" + mediaId);
	String result = null;
	String token = null;
	Map<String, String> map = new HashMap<String, String>();
	try {
	    token = WxUtil.getAccessToken();
	    log.debug("获取token:" + token);
	    result = WxUtil.sendImage(token, userOpenId, mediaId);
	    map.put("result", result);
	} catch (Exception e) {
	    log.error("sendCustomerImage接口发生异常", e);
	}
	return new ModelAndView("result", map);
    }

}
