<template>
  <div>
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
        width="260">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="goUpdateUser(scope.row)">修改</el-button>
          <el-button type="danger" size="small" @click="deleteUserByUsername(scope.row.username)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        fileUrl: this.$fileUrl,
        username:'',
        tableData:[],
      }
    },
    methods: {
      loadUserSelf(){
        // 加载用户数据函数
        let _this = this;
        this.$axios({
          headers:{
            "Authorization": this.username,
          },
          url:'user/userSelf/' + this.username,
          method: 'get',
          params: this.pageData, // get 请求提交数据给服务器，使用params
        }).then(res => {
          _this.tableData.push(res.data.data)
        }).catch(error =>{
          _this.$message.error(error);
        })
      },
      goUpdateUser(user){
        // 数据回显： 在修改页面上展示出被修改用户信息
        // 前端 A 页面传值给 B 页面，可以使用 sessionStorage: 如果是字符串可以直接键值对存储，如果是 js 对象需要转为 json 字符串存储
        sessionStorage.setItem('updateUserInfo',JSON.stringify(user));
        this.$router.push('/index/userEdit');
      },
      deleteUserByUsername(username){
        console.log(username);
      },
    },

    mounted() {
      this.username = sessionStorage.getItem('loginUsername');
      this.loadUserSelf();

    }
  }
</script>

<style scoped>

</style>
