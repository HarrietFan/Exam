<template>
  <div>
    <el-form label-position="right" label-width="37%" :model="user" style="margin-top: 13%;">
      <el-form-item label="账号">
        <el-col :span="8">
          <el-input v-model="user.username" disabled="disabled"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="密码">
        <el-col :span="8">
          <el-input v-model="user.password" type="password"></el-input>
        </el-col>
      </el-form-item>
      <!-- 使用ElementUI的按钮-->
      <el-button type="primary" @click="updateUser" style="margin-left: 38%;">修改</el-button>
      <el-button type="info" @click="back" style="margin-left: 70px;">返回</el-button>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        username: '',
        user: {},
        password: '',
      }
    },
    methods: {
      updateUser() {
        this.$axios({
          headers: {
            "Authorization": this.username,
          },
          url: 'user/updateUserByUsername',
          method: 'put',
          data: this.user
        }).then(res => {
          this.$message.success(res.data.msg);
          setTimeout(() => {
            this.loadUserList();
          }, 1500);
        }).catch(error => {
          this.$message.error(error);
        });
      },
      back() {
        this.$router.go(-1);
      }
    },
    loadUserList() { // 加载用户数据的函数
      let _this = this;
      this.$axios({
        headers: {
          "Authorization": this.username,
        },
        url: 'user/userList',
        method: 'get',
        params: this.pageData, // get 请求提交数据给服务器，使用 params
      }).then(res => {
        _this.tableData = res.data.data.records;
        _this.total = res.data.data.total;
      }).catch(error => {
        _this.$message.error(error);
      })
    },
    mounted() {
      this.username = sessionStorage.getItem('loginUsername');
      this.user = JSON.parse(sessionStorage.getItem('updateUserInfo'));
    }
  }
</script>

<style scoped>

</style>
