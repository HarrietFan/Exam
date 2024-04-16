<template>
  <div>
    <!--      查询表单-->
    <el-form :inline="true" :model="pageData" class="demo-form-inline" align="center">
      <el-form-item>
        <el-input v-model="pageData.param" placeholder="请输入关键词..."></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="queryUser">查询</el-button>
      </el-form-item>
    </el-form>
    <!--      用户列表-->
    <el-table
      :data="tableData"
      border
      style="width: 100%">
        <el-table-column
          prop="courseId"
          label="课程号"
          align="center"
          width="260">
        </el-table-column>
        <el-table-column
        prop="courseName"
        label="课程名"
        align="center"
        width="260">
      </el-table-column>
        <el-table-column
        prop="teacher"
        label="授课老师"
        align="center"
        width="260">
      </el-table-column>
      <el-table-column
        prop="courseHour"
        label="学时"
        align="center"
        width="260">
      </el-table-column>
      <el-table-column
        prop="credit"
        label="学分"
        align="center"
        width="260">
      </el-table-column>
    </el-table>
    <!--
          分页按钮
           @size-change: 当 size 发生变化时，执行函数，默认把发生变化之后的 size 传递过去
           @current-change: 当 page 发生变化时，执行函数，默认把发生变化之后的 page 传递过去
           :current-sizes: 可共选择的
    -->
    <div class="block" align="center">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageData.page"
        :page-sizes="[5,10,15,20]"
        :page-size="pageData.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        username:'',
        tableData:[], // 用户数据
        pageData : {
          page: 1,
          size: 10,
          param: '',
        },
        total:0,
      }
    },
    methods: {
      loadCourseList(){
        // 加载用户数据函数
        let _this = this;
        this.$axios({
          headers:{
            "Authorization": this.username,
          },
          url:'course/courseList',
          method: 'get',
          params: this.pageData, // get 请求提交数据给服务器，使用params
        }).then(res => {
          _this.tableData = res.data.data.records;
          _this.total = res.data.data.total;
        }).catch(error =>{
          _this.$message.error(error);
        })
      },
      handleSizeChange(newSize){
        this.pageData.size = newSize;
        this.loadCourseList();
      },
      handleCurrentChange(newPage){
        this.pageData.page = newPage;
        this.loadCourseList();},
      queryUser(){
        // 把 page 的值，设置为1，防止数据查询出来没有显示
        this.pageData.page = 1;
        this.loadCourseList();
      },
      // goExam(user){
      //   // 数据回显： 在修改页面上展示出被修改用户信息
      //   // 前端 A 页面传值给 B 页面，可以使用 sessionStorage: 如果是字符串可以直接键值对存储，如果是 js 对象需要转为 json 字符串存储
      //   // sessionStorage.setItem('Exam',JSON.stringify(user));
      //   this.$router.push('/index/courseList');
      // },
    },
    mounted() {
      this.username = sessionStorage.getItem('loginUsername');
      // 调用加载用户数据的函数
      this.loadCourseList();

    }
  }
</script>

<style scoped>

</style>
