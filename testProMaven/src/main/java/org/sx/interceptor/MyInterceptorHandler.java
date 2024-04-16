package org.sx.interceptor;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.sx.annotation.JwtToken;
import org.sx.pojo.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

// 定义拦截器
@Component
public class MyInterceptorHandler implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // Ctrl + i 重写 preHandle 方法，该方法可以在请求进入控制器方法之前执行处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // Object handler: 如果请求进入的是控制器的方法，那么他就是一个HandlerMethod 的一个对象，否则不是
        if(!(handler instanceof HandlerMethod)){
            // 表示不是进入控制器方法
            return true;
        }
        // 进入控制器方法
        /*
        *  1、 验证是否拦截
        *  2、验证是否登录
        *  3、验证当前用户是否有这个权限可以访问
        *
        * */
        // 把 Object handler 还原为 HandlerMethod 对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取到访问方法上的JwtToken 注解
        JwtToken jwtToken = handlerMethod.getMethodAnnotation(JwtToken.class);
        // 验证 jwtToken 对象是否为空，为空放行，不为空表示需要验证权限
        if(Objects.isNull(jwtToken)){
            // 表示不需要拦截
            return true;
        }
        // 需要拦截处理
        // 通过 request 对象，获取到访问方法中的请求头里面的参数
        String username =  request.getHeader("Authorization");

        // 判断用户名是否为空
        if (StringUtils.isEmpty(username)){
            response.getWriter().write("this user is not login, please do login");
            return false;
        }

        // 通过username 取出 redis 中对应的用户数据
        User user = (User) redisTemplate.opsForValue().get(username);
        // 验证 user 是否为空，为空表示未登录，执行登录
        if (Objects.isNull(user)){
            // 为空，返回未登录提示
            response.getWriter().write("this user is not login, please do login");
            return false;
        }

        // 当前用户已经登陆，取出用户数据中的角色信息
        String roleName = user.getRole().getRoleName();
        // 取出jwtToken 注解对象中的 hasRoles 参数
        String[] hasRoles = jwtToken.hasRoles();
        // 如何验证 hasRoles 中是否包含了 roleName;
        boolean isContains = Arrays.asList(hasRoles).contains(roleName);
        // 验证 isContains 的值
        if (isContains){
            // 有这个角色，允许访问
            return true;
        }
        // 没有这个角色，拒绝访问
        response.getWriter().write("this user has not permission");
        return false;
    }
}
