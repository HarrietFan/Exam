package org.sx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sx.annotation.JwtToken;
import org.sx.mapper.CourseMapper;
import org.sx.mapper.UserMapper;
import org.sx.pojo.Course;
import org.sx.pojo.PageData;
import org.sx.pojo.User;
import org.sx.utils.ResponseMessage;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 用户登录后查看课程列表
    // login
    // url:  http://localhost:8080/course/courseList
    // method: get
    // params: pageData
    @GetMapping("/courseList")
    @JwtToken(required = true,hasRoles = {"管理员"})
    public ResponseMessage userList(PageData pageData){
        Integer page = pageData.getPage();
        Integer size = pageData.getSize();
        String param = pageData.getParam();

        //  创建分页对象page. 参数一：当前页；参数二：显示条数。
        Page<Course> page1 = new Page<>(page,size);
        // 查询用户表和角色表数据，涉及到多表操作，自己定义SQL
        Page<Course> userPage =  courseMapper.courseList(page1,param);
        // 返回数据
        return new ResponseMessage(200,"user list", userPage);
    }

    // myCourse
    // url:  http://localhost:8080/course/myCourse
    // method: get
    // params: username
    @GetMapping("/myCourse/{username}")
    @JwtToken(required = true,hasRoles = {"管理员","学生"})
    public ResponseMessage userSelf(PageData pageData,@PathVariable String username){
        Integer page = pageData.getPage();
        Integer size = pageData.getSize();
        String param = pageData.getParam();

        //  创建分页对象page. 参数一：当前页；参数二：显示条数。
        Page<Course> page1 = new Page<>(page,size);
        // 查询用户表和角色表数据，涉及到多表操作，自己定义SQL
        Page<Course> userPage =  courseMapper.myCourse(page1,username);
        return new ResponseMessage(200,"update myCourse success",userPage);
    }
}