package org.sx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.sx.pojo.Course;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {
    // courseList
    Page<Course> courseList(@Param("page") Page<Course> page1,
                            @Param("param") String param);

    Page<Course> myCourse(@Param("page") Page<Course> page1,
                          @Param("username")String username);
}
