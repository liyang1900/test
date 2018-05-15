package com.qq.util;

/**
 * @Title:
 * @Description:
 * @Author: liyang.a
 * @Since: 2018年5月15日
 * @Version: 1.1.0
 */
public class CommonUtil {

    public static boolean isEmpty(String str) {
	if (str == null || "".equals(str)) {
	    return true;
	}
	return false;
    }
}
