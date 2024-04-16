package org.sx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.sx.annotation.JwtToken;
import org.sx.mapper.UserMapper;
import org.sx.pojo.PageData;
import org.sx.pojo.User;
import org.sx.utils.ResponseMessage;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate; //

    // login
    // url: http://localhost:8080/user/login
    // method: post
    // params:json

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User loginUser = userMapper.login(username,password);
        // 验证 loginUser 是否为 null， 如果为 null 表示没有这个用户，登陆失败 否则 登录成功
        // Objects 工具提供对应的函数，isNull 表示为null,nonNull不为null
        if (Objects.nonNull(loginUser)) {
            // 把用户数据存入 redis, set方法：key.value. 存放数据。 get方法： key。取出数据
            redisTemplate.opsForValue().set(username,loginUser);
            return new ResponseMessage(200,"login success",loginUser.getUserImage());
        }
        return new ResponseMessage(500,"please check your username or password");
    }

    // register
    // url: http://localhost:8080/user/register
    // method: post
    // params:json
    @PostMapping("/register")
    public ResponseMessage register(@RequestBody User user) {
        // 方法一：
        User checkUser = userMapper.checkUsername(user.getUsername());
        if (Objects.isNull(checkUser)){
            userMapper.insert(user);
            userMapper.insertRole(user.getUserId());
            return new ResponseMessage(200,"Registration user and role success");
        }
        return new ResponseMessage(500,"Registration user is exist");
    }

    // 用户登录后查看用户列表
    // login
    // url:  http://localhost:8080/user/userList
    // method: get
    // params: pageData
    @GetMapping("/userList")
    @JwtToken(required = true,hasRoles = {"管理员"})
    public ResponseMessage userList(PageData pageData){
        Integer page = pageData.getPage();
        Integer size = pageData.getSize();
        String param = pageData.getParam();

        //  创建分页对象page. 参数一：当前页；参数二：显示条数。
        Page<User> page1 = new Page<>(page,size);
        // 查询用户表和角色表数据，涉及到多表操作，自己定义SQL
        Page<User> userPage =  userMapper.userList(page1,param);
        // 返回数据
        return new ResponseMessage(200,"user list", userPage);
    }

    // 删除用户
    //deleteUserByUsername
    // url:  http://localhost:8080/user/deleteUserByUsername
    // method: delete
    // params: username
    @DeleteMapping("/deleteUserByUsername/{username}")
    @JwtToken(required = true,hasRoles = {"管理员"})
    public ResponseMessage deleteUserByUsername(@PathVariable String username){
        userMapper.delete(new QueryWrapper<User>().eq("username",username));
        return new ResponseMessage(200,"delete user success");
    }

    //  insertUser
    // url:  http://localhost:8080/user/insertUser
    // method: post
    // params: json user
    // 新增角色信息且检查用户名是否已经存在
    @PostMapping("/insertUser")
    @Transactional
    @JwtToken(required = true,hasRoles = {"管理员"})
    public ResponseMessage insertUser(@RequestBody User user){

        // 方法一：
        User checkUser = userMapper.checkUsername(user.getUsername());
        if (Objects.isNull(checkUser)){
            userMapper.insert(user);
            userMapper.insertRole(user.getUserId());
            return new ResponseMessage(200,"insert user and role success");
        }
        return new ResponseMessage(500,"insert user is exist");
    }

    // 更新用户信息
    // updateUserByUsername
    // url:  http://localhost:8080/user/updateUserByUsername
    // method: put
    // params: json user
    @PutMapping("/updateUserByUsername")
    @JwtToken(required = true,hasRoles = {"管理员"})
    public ResponseMessage updateUserByUsername(@RequestBody User user){
        userMapper.update(user,new QueryWrapper<User>().eq("username",user.getUsername()));
        return new ResponseMessage(200,"update user success");
    }

    // userSelf
    // url:  http://localhost:8080/user/userSelf
    // method: get
    // params: username
    @GetMapping("/userSelf/{username}")
    @JwtToken(required = true,hasRoles = {"管理员","学生"})
    public ResponseMessage userSelf(@PathVariable String username){
        User user = (User) redisTemplate.opsForValue().get(username);
        return new ResponseMessage(200,"update user success",user);
    }

    // logout
    // url:  http://localhost:8080/user/logout
    // method: get
    // params: username
    @GetMapping("/logout/{username}")
    @JwtToken(required = true,hasRoles = {"管理员","学生"})
    public void logout(@PathVariable String username){
        // 清空 redis 当前用户的数据
        redisTemplate.delete(username);
    }
}
