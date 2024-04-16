<template>
  <div>
    <!--      查询表单-->
    <el-form :inline="true" :model="pageData" class="demo-form-inline" align="center">
      <el-form-item>
        <el-input v-model="pageData.param" placeholder="请输入关键词..."></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="queryUser">查询</el-button>
        <el-button type="primary" @click="insertUser">新增</el-button>
      </el-form-item>
    </el-form>
  <!--      用户列表-->
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        prop="userId"
        label="序号"
        align="center"
        width="150">
      </el-table-column>
      <el-table-column
        label="头像"
        align="center"
        width="150">
        <template slot-scope="scope">
          <img :src="fileUrl + scope.row.userImage" style="height: 100px;width: 260px">
        </template>
      </el-table-column>
      <el-table-column
        prop="username"
        label="账号"
        align="center"
        width="200">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码"
        align="center"
        width="150">
      </el-table-column>
      <el-table-column
        prop="userEmail"
        label="邮箱"
        align="center"
        width="260">
      </el-table-column>
      <el-table-column
        prop="role.roleName"
        label="账号"
        align="center"
        width="150">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        align="center"
        width="220">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="UpdateUser(scope.row)">修改</el-button>
          <el-button type="danger" size="small" @click="deleteUserByUsername(scope.row.username)">删除</el-button>
        </template>
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
        fileUrl: this.$fileUrl,
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
      loadUserList(){
        // 加载用户数据函数
        let _this = this;
        this.$axios({
          headers:{
            "Authorization": this.username,
          },
          url:'user/userList',
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
        this.loadUserList();
      },
      handleCurrentChange(newPage){
        this.pageData.page = newPage;
        this.loadUserList();},
      queryUser(){
        // 把 page 的值，设置为1，防止数据查询出来没有显示
        this.pageData.page = 1;
        this.loadUserList();
      },
      UpdateUser(user){
        // 数据回显： 在修改页面上展示出被修改用户信息
        // 前端 A 页面传值给 B 页面，可以使用 sessionStorage: 如果是字符串可以直接键值对存储，如果是 js 对象需要转为 json 字符串存储
        sessionStorage.setItem('updateUserInfo',JSON.stringify(user));
        this.$router.push('/index/userEdit');
      },
// 新增
      insertUser(user){
        // 数据回显：在修改页面上展示出被修改用户信息
        // 前端 A 页面传值给 B 页面，可以使用 sessionStorage：如果是字符串等可以直接键值对存储，如果是对象，需要把 js 对象转为 json 字符串存储
        sessionStorage.setItem('insertUserInfo',JSON.stringify(user));
        // console.log("hsgajdg");
        this.$router.push('/index/userInsert');
      },

      //删除
      deleteUserByUsername(username){
        let _this = this;
        this.$axios({
          headers:{
            "Authorization": this.username,
          },
          url: 'user/deleteUserByUsername/'+username,
          method: 'delete',
        }).then(res =>{
          _this.$message(res.data.msg);
          setTimeout(()=>{
            _this.loadUserList();
          },1500);
        }).catch(error => { // 请求失败的回调函数
          _this.$message.error(error); // 弹出错误信息
        })
      },
    },
    mounted() {
      this.username = sessionStorage.getItem('loginUsername');
      // 调用加载用户数据的函数
      this.loadUserList();

    }
  }
</script>

  <style scoped>

  </style>
