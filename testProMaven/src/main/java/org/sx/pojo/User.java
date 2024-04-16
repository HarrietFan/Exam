package org.sx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    private String userImage;
    private String username;
    private String password;
    private String userEmail;
    @TableLogic
    private Integer userState;
    @TableField(exist = false) // 表示role 不属于数据库表user的一个字段
    private Role role;
}
