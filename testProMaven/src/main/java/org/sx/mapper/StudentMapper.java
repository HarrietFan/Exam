package org.sx.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sx.pojo.Student;

// student 类操作数据库的接口
@Mapper //解决当前接口让mybatis-plus在程序运行过程中
public interface StudentMapper extends BaseMapper<Student> {

}
