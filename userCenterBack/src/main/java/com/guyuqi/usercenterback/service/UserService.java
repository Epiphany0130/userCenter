package com.guyuqi.usercenterback.service;

import com.guyuqi.usercenterback.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
* @author guyuqi
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-05-13 15:39:53
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 ID
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request 设置 Session
     * @return 脱敏后的用户信息
     */
    User doLogin(String userAccount, String userPassword, HttpServletRequest request);
}
