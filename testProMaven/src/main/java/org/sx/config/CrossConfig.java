package org.sx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.sx.interceptor.MyInterceptorHandler;

// 配置跨域
@Configuration
// 实现WebMvcConfigurer接口
public class CrossConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptorHandler myInterceptorHandler;

    // 重写addCorsMappings方法
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // /**表示所有请求都支持跨域访问
                .allowedOrigins("*") // 所有的外部域都可跨域访问
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS") // 哪些请求需要跨域配置
                .allowCredentials(true) // 是否支持跨域用户凭证
                .maxAge(300) // 超时时长设置为5分钟。时间单位是秒
                .allowedHeaders("*"); // 请求体的头部
    }

    // 注册拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptorHandler).addPathPatterns("/**");
    }

    // 注册请求资源路径，比如访问 http://localhost:8080/images/img-1.jpg 就去访问 磁盘E:\lessones\images\img-1.jpg
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:E:\\lessones\\images\\");
    }
}