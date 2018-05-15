package com.qq.util.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import com.alibaba.fastjson.JSONObject;
import com.qq.util.CommonUtil;
import com.qq.util.http.HttpUtil;

/**
 * @Title:
 * @Description:
 * @Author: liyang.a
 * @Since: 2018年5月15日
 * @Version: 1.1.0
 */
public class WxUtil {
    private static Logger log = LoggerFactory.getLogger(WxUtil.class);
    
    public static String appID = "wxbfea9876382f69e2";
    public static String appsecret = "5ec3c9ba664cca5b5afc950d1606a2af";
    public static String TOKEN = "qwerasdzxc";
    public static String encodingAesKey = "TwZVSx5KJYdNMcRFN482xCUDbA4sxV9Y3QmiAp7xQWJ";

    public static String openID = "oJ6431M_QZNaxVPAA0ekI9rjB1u8";

    // 获取access_token
    private static String getToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
	    + appID + "&secret=" + appsecret;

    public static String getAccessToken() {
	String accessTokenJson = HttpUtil.doGet(getToken);
	log.debug("获取accessTokenJson:" + accessTokenJson);
	if (!CommonUtil.isEmpty(accessTokenJson)) {
	    JSONObject json = JSONObject.parseObject(accessTokenJson);
	    return (String) json.get("access_token");
	}
	return null;

    }
}
