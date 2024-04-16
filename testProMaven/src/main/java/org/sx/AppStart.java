package org.sx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Springboot 项目启动类注解
// 扫描注解的作用域：就是启动类统计或者子级
@MapperScan("org.sx.mapper") //让这个路径下的所有接口都在运行中生成实现类，由mybatis-plus生成

public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class,args);
    }
}
