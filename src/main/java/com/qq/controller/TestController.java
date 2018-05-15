package com.qq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    
}
