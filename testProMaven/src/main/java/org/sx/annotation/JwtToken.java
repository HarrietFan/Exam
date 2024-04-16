package org.sx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解，实现用户身份拦截
@Target(ElementType.METHOD) // 该注解可以作用在什么上，method 表示方法
@Retention(RetentionPolicy.RUNTIME) // 该注解什么时候生效，runtime 运行时
public @interface JwtToken {
    // 是否需要登录了才可以登录
    boolean required() default true;
    // 该方法可以辈什么角色访问
    String[] hasRoles() default {};
}
