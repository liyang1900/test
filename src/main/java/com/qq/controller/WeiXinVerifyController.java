package com.qq.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qq.util.sign.SignUtil;
import com.qq.util.weixin.SHA1;

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
    
    private static String appID = "wxbfea9876382f69e2";
    private static String appsecret = "5ec3c9ba664cca5b5afc950d1606a2af";

    private static String TOKEN = "qwerasdzxc";
    private static String encodingAesKey = "TwZVSx5KJYdNMcRFN482xCUDbA4sxV9Y3QmiAp7xQWJ";
    

    @RequestMapping(value = "/verifyToken", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp,
	    String nonce, String echostr) {
	log.debug("verifyToken", signature);
	boolean flag = SignUtil.checkSignature(TOKEN, signature, timestamp, nonce);
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
