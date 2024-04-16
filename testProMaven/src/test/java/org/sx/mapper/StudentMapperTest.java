package org.sx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.sx.pojo.Student;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


// StudentMapper 接口测试类.Springboot 中使用junit测试，只需要在类上再加一个注解 @Springboot
// 每一个方法，都是一个测试方法，方法上加上注解@Test,该注解是org.junit.jupiter.api.Test包下的
// 每一种测试方法，其实都是一个可执行的方法，类似于main方法
@SpringBootTest
public class StudentMapperTest {

    /*
     *   在Sprint中，若A类中要引入B类的对象，可以通过字段装配的方式实现，A、B都要在容器中
     *   自动装配：  在IOC（Spring）容器中，把对象引出来
     *   IOC容器：  创建对象的过程，维护对象的过程等都是交给容器来处理，我们直接在容器中取出来想要用的对象即可
     *   @Autowired： 注解可以实现
     *   @Resource： 注解可以实现
     */
    //自动装配StudentMapper接口对象
    @Resource
    private StudentMapper studentMapper;

    /*   查询方法：
     *   1、全部查询
     *   2、条件查询
     *   3、模糊查询
     *   4、动态SQL查询
     *   5、分页查询
     *   6、分页查询 + 动态SQL模糊查询
     */
    // 全部查询：    把数据库表的student中的数据都查询出来，用java实体类Student来接收，遍历输出
    @Test
    public void selectAll() {
        //SQL:  select * from student
        List<Student> studentList = studentMapper.selectList(null);
        // foreach 循环，  快捷键：遍历集合对象.iter
        for (Student student : studentList) {
            System.out.println((student));
        }
    }


    // 条件查询一：通过学生名字，查询学生信息
    @Test
    public void selectStudentByStudentName() {
        // SQL: select * from student where student_name = XXX
        // 1、 创建条件构造器对象
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        // 2、条件是等于关系：eq
        queryWrapper.eq("student_name", "xiaoming");
        // 3、基于条件查询用户，selectOne 方法，查询一个学生
        Student student = studentMapper.selectOne(queryWrapper);
        System.out.println(student);
    }

    // 条件查询二：条件是满足：学生名和学生学号都存在且正确
    @Test
    public void login() {
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("student_name", "xiaoming");
//        queryWrapper1.eq("student_number", "20204811101");
        queryWrapper1
                .eq("student_name","xiaodu")
                .eq("student_number","20204811105");
        Student student = studentMapper.selectOne(queryWrapper1);
        System.out.println(student);
    }

    //  模糊查询： 查询学生名字带有某个字符的人
    @Test
    public void selectLike(){
        //  SQL : select * from student where student_name like %xxx%
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        //  Like函数，就是用来设置sql中like XXX的条件
        //  查询学生名字中含有‘h’的学生信息
        queryWrapper.like("student_name","h");
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    //  动态sql
    @Test
    public void selectstudent(){
        //  SQL : select * from student
        //  SQL : select * from student where student_name like %xxx%
        //  如果说student_name参数存在（不为null,或者空白字符串）表示做模糊查询，否则做查询全部
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        String studentName = "m";
        queryWrapper.like(studentName != null && !"".equals(studentName),"student_name",studentName);
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        for(Student student : studentList) {
            System.out.println(student);
        }
    }

    //  分页查询，配置分页插件
    //  就是通过 limit start,size 限制查询的数据条数，从而实现展示内容
    //  start：起始位置      size：显示条数
    @Test
    public void pageLimit(){
        // start = （page - 1) * size
        // page: 当前页
        // 前端要实现分页，需要传递参数：page、size
        //  创建分页对象page. 参数一：当前页；参数二：显示条数。
        Page<Student> page = new Page<>(1,2);
        //  如果 student_name 不为空或空白字符段，则模糊查询+分页查询        ！！！作业
        //  selectPage函数中。参数一：分页对象；参数二：条件构造器
        Page<Student> studentPage =  studentMapper.selectPage(page,null);
        System.out.println("总条数：" + studentPage.getTotal());
        //  当前页
        List<Student> studentList = studentPage.getRecords();
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void pageLimit1(){
        // start = （page - 1) * size
        // page: 当前页
        // 前端要实现分页，需要传递参数：page、size
        //  创建分页对象page. 参数一：当前页；参数二：显示条数。
        Page<Student> page = new Page<>(1,2);
        //  如果 student_name 不为空或空白字符段，则模糊查询+分页查询        ！！！作业
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name","h");
        //  selectPage函数中。参数一：分页对象；参数二：条件构造器
        Page<Student> studentPage =  studentMapper.selectPage(page,null);
        System.out.println("总条数：" + studentPage.getTotal());
        //  当前页
        List<Student> studentList = studentPage.getRecords();
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

}
