package com.xy.service.impl;

import com.xy.service.LogService;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LogServiceImplTest extends TestCase {

    public void testGetLog() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LogService logService = applicationContext.getBean("logServiceImpl", LogService.class);

        logService.getLog();
    }
}