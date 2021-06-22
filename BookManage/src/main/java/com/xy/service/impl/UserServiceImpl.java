package com.xy.service.impl;

import com.xy.dao.UserMapper;
import com.xy.pojo.User;
import com.xy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author x1yyy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> register(String account, String password, String role) {
        Map<String, Object> results = new HashMap<>();

        User user = userMapper.queryUserByAccount(account);

        if (user == null) {
            Map<String, Object> newUser = new HashMap<>();

            newUser.put("account", account);
            newUser.put("password", password);
            newUser.put("role", role);

            if (userMapper.addUser(newUser) != 1) {
                results.put("resultCode", 2);
                results.put("resultMessage", "register failed");
            }
            else {
                results.put("resultCode", 0);
                results.put("resultMessage", "success");
            }

            return results;
        }

        results.put("resultCode", 1);
        results.put("resultMessage", "the account is existed");

        return results;
    }

    @Override
    public Map<String, Object> login(String account, String password) {
        Map<String, Object> results = new HashMap<>();

        User user = userMapper.queryUserByAccount(account);

        if (user == null || !password.equals(user.getPassword())) {
            results.put("resultCode", 1);
            results.put("resultMessage", "account or password error");
            results.put("data", null);
        }
        else {
            Map<String, Object> data = new HashMap<>();

            results.put("resultCode", 0);
            results.put("resultMessage", "success");
            data.put("userRole", user.getRole());
            results.put("data", data);
        }

        return results;
    }
}
