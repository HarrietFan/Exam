package org.sx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourse {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private Integer courseId;
    private String courseName;
    private String teacher;
}
