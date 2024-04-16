package org.sx.controller;


import org.springframework.web.bind.annotation.*;
import org.sx.pojo.TestModel;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")

public class TestController {

    private String username;
    private String password;

    //定义一个GET请求：返回一个字符串 hello springboot
    //url: http://localhost:8080/test/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello springboot get";
    }

    //定义一个POST请求：返回一个字符串 hello springboot
    //url: http://localhost:8080/test/hello
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String helloPost() {
        return "hello springboot post";
    }


    //接收前端请求
    //1、通过request方法,HttpServletRequest
    //url:http://localhost:8080/test/request
    @GetMapping("/request")
    public String request(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username + "==========  password = " + password);
        return "request 接收请求参数";
    }

    //接收前端请求
    //2、通过请求方法的形式参数接收
    //url:http://localhost:8080/test/params1
    @GetMapping("/params1")
    public String params1(String username, String password) {
        System.out.println("username = " + username + "==========  password = " + password);
        return "params1 接收请求参数";
    }

    //接收前端请求
    //3、通过请求方法的形式参数接收
    //url:http://localhost:8080/test/params2
    @GetMapping("/params2")
    public String params2(@RequestParam(value = "username", required = true, defaultValue = "111") String username, String password) {
        System.out.println("username = " + username + "==========  password = " + password);
        return "params2 接收请求参数";
    }


    //接收前端请求
    // 4、通过请求方法的形式参数接收（形式参数是一个对象）
    //url:http://localhost:8080/test/model?username=admin&password=123456
    @GetMapping("/model")
    public String model(TestModel testModel) {
        System.out.println(testModel);
        return "model 接收请求参数";
    }

    //接收前端请求
    //5、通过请求路径中的参数，映射到请求方法的形式参数上，restFul编码风格有关系
    //url:http://localhost:8080/test/restFul/admin/123456
    @GetMapping("/restFul/{username}/{aaa}")
    public String restFul(@PathVariable String username, @PathVariable("aaa") String password) {
        System.out.println("username = " + username + "====>>>> password = " + password);
        return "restFul 接收请求参数";
    }

    //接收前端请求
    //6、json格式数据，放在请求体里面的：post请求、put请求、@RequestBody注解。获取请求体里面的内容
    //url:http://localhost:8080/test/json
//    @GetMapping("/json")
    @PostMapping("/json")
    public String json(@RequestBody TestModel testModel) {
        System.out.println("username = " + testModel.getUsername() + "    password = " + testModel.getPassword());
        return "json 接收请求参数";
    }
}