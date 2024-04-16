package org.sx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @TableId(value = "course_id",type = IdType.AUTO)
    private Integer courseId;
    private String username;
    private String courseName;
    private String teacher;
    private String courseHour;
    private String credit;

    @TableField(exist = false) // 表示username 不属于数据库表user的一个字段
    private User user;
}
