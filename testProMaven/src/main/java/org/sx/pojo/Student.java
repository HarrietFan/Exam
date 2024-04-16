package org.sx.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
*   总结：1、但凡设计到实体类，都把@Data、@AllArgsConstructor、@NoArgsConstructor 注解加上。
*
*/

//Student类与mysql中exam数据库的student表对应
@Data //提供了get、set、toString方法
@AllArgsConstructor     //提供了全参数构造函数
@NoArgsConstructor      //提供了无参数构造函数
@TableName(value = "student")       //指定当前类与数据库中的哪张表对应，默认情况下和类名小写的那张表对应
public class Student {
    //@TableId：用于主键上的注解
    //value: 指定数据库表student中哪个字段和Student类成员变量studentId对应，如果满足驼峰命名映射规则或名字相同，可以不写
    //type：AUTO：设置主键自增策略
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;
    //@TableField:用于非主键的成员变量
    //value:指定数据库表student中哪个字段和Student类成员变量studentName对应，如果满足驼峰命名映射规则或名字相同，可以不写
    //exist: 如果说当前实体类中某个成员变量不属于对用的表中的某一个字段，那么就需要使用该属性说明，exist=false
    @TableField(value = "student_name")
    private String studentName;
    @TableField(value = "student_number")
    private String studentNumber;
    @TableField(value = "student_state")
    //逻辑删除注解，在使用mybatis-plus接口提供的方法时，会自动添加一个条件where student_state=0;
    //在调用mybatis-plus接口提供的delete方法时，会自动把delete from student where student_name = XXX 改成：
    // update student set student_state = 1 where student_name = XXX
    @TableLogic
    private  Integer studentState;
}
