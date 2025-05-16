package com.guyuqi.usercenterback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guyuqi.usercenterback.model.domain.User;
import com.guyuqi.usercenterback.model.domain.request.UserLoginRequest;
import com.guyuqi.usercenterback.model.domain.request.UserRegisterRequest;
import com.guyuqi.usercenterback.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.guyuqi.usercenterback.contant.UserConstant.ADMIN_ROLE;
import static com.guyuqi.usercenterback.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author GuYuqi
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {

        if(userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.doLogin(userAccount, userPassword, request);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String username, HttpServletRequest request) {

        if(!isAdmin(request)) {
            return new ArrayList<>();
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        return userService.list(queryWrapper);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {

        if(!isAdmin(request)) {
            return false;
        }

        if(id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 鉴权
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
