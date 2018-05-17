package com.qq.util.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    // 群发消息
    private static String sendMsg = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    // 发送图片
    private static String sendImage = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    public static String getAccessToken() {
	String accessTokenJson = HttpUtil.doGet(getToken);
	log.debug("获取accessTokenJson:" + accessTokenJson);
	if (!CommonUtil.isEmpty(accessTokenJson)) {
	    JSONObject json = JSONObject.parseObject(accessTokenJson);
	    return (String) json.get("access_token");
	}
	return null;
    }

    public static String sendMessage(String token, String userOpenId, String msg, Throwable e) {
	JSONObject json = new JSONObject();
	json.put("touser", userOpenId);
	json.put("msgtype", "text");
	JSONObject content = new JSONObject();
	JSONObject contentDetail = new JSONObject();
	contentDetail.put("输入信息：", msg);
	if (e != null) {
	    StackTraceElement[] errStr = e.getStackTrace();
	    StringBuffer sb = new StringBuffer();
	    sb.append(e.toString() + "  ");
	    for (StackTraceElement element : errStr) {
		sb.append(element.toString() + " ");
	    }
	    if (sb.length() > 200) {
		String str = sb.substring(0, 200);
		contentDetail.put("异常信息e：", str);
	    }
	}
	content.put("content", contentDetail.toString());
	json.put("text", content);
	String params = json.toJSONString();
	log.debug("获取json:" + json);

	String result = HttpUtil.sendPost(sendMsg + token, params);
	return result;
    }

    public static String sendImage(String token, String userOpenId, String mediaId) {
	JSONObject json = new JSONObject();
	json.put("touser", userOpenId);
	json.put("msgtype", "image");
	JSONObject content = new JSONObject();
	content.put("media_id", mediaId);
	json.put("image", content);
	String params = json.toJSONString();
	String result = HttpUtil.sendPost(sendImage + token, params);
	return result;
    }

    public static void main(String args[]) {
	String token = getAccessToken();
	System.out.println(token);
	String msg = "hello";
	String result = sendMessage(token, openID, msg, null);
	System.out.println(result);
    }
}
