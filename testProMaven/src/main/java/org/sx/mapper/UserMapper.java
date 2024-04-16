package org.sx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sx.pojo.Course;
import org.sx.pojo.User;

public interface UserMapper extends BaseMapper<User> {
    //    login 方法
    //    public abstract User login(String username,String password);
    //    User login(String username,String password);
    User login(@Param("username") String username,
               @Param("password") String password);

    // userList
    Page<User> userList(@Param("page") Page<User> page1,
                        @Param("param") String param);


    // 新增角色信息
    @Insert("insert into `user_role_rel` value(#{userId},2)")
    void insertRole(Integer userId);

    // 检测用户名是否存在，方法一：
    @Select("SELECT * FROM user WHERE username = #{username}")
    User checkUsername(String username);


}