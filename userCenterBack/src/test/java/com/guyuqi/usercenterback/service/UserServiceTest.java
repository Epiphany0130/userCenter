package com.guyuqi.usercenterback.service;
import java.util.Date;

import com.guyuqi.usercenterback.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("Yupi");
        user.setUserAccount("123");
        user.setAvatarUrl("https://baomidou.com/assets/asset.cIbiVTt_.svg");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("456");

        boolean result = userService.save(user);

        Assertions.assertTrue(result);

    }
}