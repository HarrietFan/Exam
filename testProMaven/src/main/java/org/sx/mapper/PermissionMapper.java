package org.sx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.sx.pojo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    // 通过角色名字查询对应的菜单栏
    // @Select 注解： 类似于映射文件中的 select 标签
    @Select("SELECT " +
            "p.* " +
            "FROM " +
            "`permission` p " +
            "LEFT JOIN `role_permission_rel` rpr ON rpr.permission_id = p.permission_id " +
            "LEFT JOIN `role` r ON r.role_id = rpr.role_id " +
            "WHERE " +
            "r.role_name = #{roleName}")
    List<Permission> permissionList(String roleName);
}
