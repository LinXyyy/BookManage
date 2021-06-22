package com.xy.service.impl;

import com.xy.service.UserService;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceImplTest extends TestCase {

    public void testRegister() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);

        System.out.println(userService.register("12345", "12345", "1"));
    }

    public void testLogin() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);

        System.out.println(userService.login("12345", "12345"));
    }
}