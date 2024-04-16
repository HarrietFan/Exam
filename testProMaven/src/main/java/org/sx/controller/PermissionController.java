package org.sx.controller;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sx.annotation.JwtToken;
import org.sx.mapper.PermissionMapper;
import org.sx.pojo.Permission;
import org.sx.pojo.User;
import org.sx.utils.ResponseMessage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/permission")

public class PermissionController {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // permissionList
    // url: http://localhost:8080/permission/permissionList/
    // method: get
    // params: username, 通过这个username 参数， 取出当前用户在 redis 中的数据内容
    @GetMapping("/permissionList/{username}")
    @JwtToken(required = true,hasRoles = {"管理员","学生"})
    public ResponseMessage permissionList(@PathVariable String username) {
        // 通过 username 取出 redis 中的用户信息，强转为 User 对象
        User user = (User) redisTemplate.opsForValue().get(username);
        // 验证当前 user 是否为 null
        if (Objects.isNull(user))
            return new ResponseMessage(500, "this user is not login");
        String roleName = user.getRole().getRoleName();
        List<Permission> permissionList = new ArrayList<>();
        // 通过当前角色，去 redis 里面取出菜单栏信息
        permissionList = (List<Permission>) redisTemplate.opsForValue().get(roleName);
        // 判断集合中是否包含元素
        if (Objects.isNull(permissionList)) {
            // 在 redis 中取出来的菜单栏没有数据
            // 执行 SQL 查询
            permissionList = permissionMapper.permissionList(roleName);
            // 把数据存入 redis
            redisTemplate.opsForValue().set(roleName,permissionList);
        }
        // 定义一个存放菜单栏数据的集合
        List<Permission> dataList = new ArrayList<>();
        // 封装数据
        for (Permission parentPermission : permissionList) {
            // 遍历查询得到的菜单栏数据，第一次遍历，取出一级菜单
            // 如何验证当前遍历得到的对象是不是一级菜单
            if (parentPermission.getParentId().equals(0)) {
                // 定义一个集合，用于存放二级菜单
                List<Permission> childrenList = new ArrayList<>();
                // 说明是一级菜单
                for (Permission childPermission : permissionList) {
                    // 第二次循环，得到二级菜单
                    // 获取到属于当前一级菜单的二级菜单
                    if (parentPermission.getNodeId().equals(childPermission.getParentId()))
                        // 把当前的二级菜单存入到集合中
                        childrenList.add(childPermission);
                }
                // 把二级菜单得到的数据存入一级菜单
                parentPermission.setChildrenList(childrenList);
                //
                dataList.add(parentPermission);
            }
        }
        return new ResponseMessage(200,"permission list", dataList);
    }
}
