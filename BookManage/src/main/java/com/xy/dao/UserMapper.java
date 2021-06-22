package com.xy.dao;

import com.xy.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * @author x1yyy
 */
public interface UserMapper {

    /**
     * 通过账号查询用户
     * @param account 查询的账号
     * @return User
     */
    User queryUserByAccount(@Param("account") String account);

    /**
     * 注册一个账号,默认的是
     * @param user 用户
     * @return 1成功
     */
    int addUser(Map<String, Object> user);

    /**
     * 借书时更新user当前借书的数量
     * @param account user
     * @param nowCount 新数量
     * @return int
     */
    int updateUser( @Param("count") int nowCount, @Param("account") String account);
}
