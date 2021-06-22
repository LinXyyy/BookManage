package com.xy.service;

import java.util.Map;

/**
 * @author x1yyy
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param account 要注册的账号
     * @param password 要注册的密码
     * @param role 用户角色
     * @return userID
     */
    Map<String, Object> register(String account, String password, String role);

    /**
     * 用户登录校验
     * @param account 账号
     * @param password 密码
     * @return success : failed
     */
    Map<String, Object> login(String account, String password);
}
