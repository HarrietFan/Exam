package org.sx.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.sx.pojo.User;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void login() {
        String username = "20204811101";
        String password = "111";
        User loginUser = userMapper.login(username, password);
        System.out.println(loginUser);
    }

//    @Test
//    public void register() {
//        String username = "20204811101";
//        String password = "111";
//        String userEmail = "1310279983@qq.com";
//        User registerUser = userMapper.register(username, password, userEmail);
//        System.out.println(registerUser);
//    }
}