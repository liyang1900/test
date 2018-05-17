package com.qq.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qq.util.LogUtil;
import com.qq.util.sign.SignUtil;
import com.qq.util.weixin.WxUtil;

/**
 * @Title:
 * @Description:
 * @Author: liyang.a
 * @Since: 2018年5月14日
 * @Version: 1.1.0
 */
@Controller
public class WeiXinVerifyController {

    Logger log = LoggerFactory.getLogger(WeiXinVerifyController.class);

    @RequestMapping(value = "/verifyToken", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp,
	    String nonce, String echostr) {
	log.debug("verifyToken接口参数：signature:" + signature + ",timestamp=" + timestamp + ",nonce=" + nonce + ",echostr="
		+ echostr);
	boolean flag = SignUtil.checkSignature(WxUtil.TOKEN, signature, timestamp, nonce);
	log.debug("验证结果flag:" + flag);
	if (flag) {
	    try {
		// 验证成功,则原样返回echostr参数内容
		response.getWriter().print(echostr);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

}
