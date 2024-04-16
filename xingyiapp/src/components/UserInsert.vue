<template>
  <div>
    <el-form label-position="right" label-width="37%" :model="user" style="margin-top: 13%;">
      <el-form-item label="账号">
        <el-col :span="8">
          <el-input v-model="user.username"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="密码">
        <el-col :span="8">
          <el-input v-model="user.password" type="password"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-col :span="8">
          <el-input v-model="user.userEmail" type="email"></el-input>
        </el-col>
      </el-form-item>
    </el-form>
    <el-form>
      <!-- 使用ElementUI的按钮-->
      <el-button type="primary" @click="insertUser" style="margin-left: 38%;">新增</el-button>
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
        userEmail: '',
      }
    },
    methods: {
      insertUser() {
          let _this = this;
          this.$axios({
            headers: {
              "Authorization": this.username,
            },
            url: 'user/insertUser',
            method: 'post',
            data: this.user
          }).then(res => {
            _this.$message(res.data.msg);
            setTimeout(() => {
              _this.loadUserList();
            }, 1500);
          }).catch(error => { // 请求失败的回调函数
            _this.$message.error(error); // 弹出错误信息
          })
        },
      back(){
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
      // this.user = JSON.parse(sessionStorage.getItem('insertUserInfo'));
    }
  }
</script>
<style scoped>
  </style>
