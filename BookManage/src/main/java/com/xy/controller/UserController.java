package com.xy.controller;

import com.xy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author x1yyy
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/registerUser")
    public Map<String, Object> register(String account, String password, String role) {
        return userService.register(account, password, role);
    }

    @ResponseBody
    @RequestMapping("/login")
    public Map<String, Object> login(@ModelAttribute("account") String account, @ModelAttribute("password") String password) {
        return userService.login(account, password);
    }
}
