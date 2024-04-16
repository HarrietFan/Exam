<template>
  <el-container>
    <el-header class="login-one">
      <el-row>
        <el-col :span="8">
          <div style="margin-top: 5px">
            <img :src="fileUrl + userImage" width="50" height="50" style="border-radius: 50%" />
          </div>
        </el-col>
        <el-col :span="8">
          <div style="margin-top: 8px">
            <el-button type="primary" round @click="goEditPassword">
              <i class="el-icon-edit"></i>修改密码</el-button>
            <el-button type="danger" round @click="logout">
              <i class="el-icon-switch-button"></i>退出登录</el-button>
            <el-button type="info" round>
              <i class="el-icon-s-tools" @click="systemManage"></i>系统管理</el-button>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="margin-top: 20px;margin-left: 350px;">
            欢迎<span style="color:#dc143c;font-size: 20px;font-weight: 500;">{{ username }}</span>登录
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-openeds="['1']" router>
          <template v-for="x in permissionList">
            <el-submenu :index="x.permissionIndex">
              <template slot="title"><i :class="x.permissionIcon"></i>{{ x.label }}
              </template>
              <template v-for="y in x.childrenList">
                <el-menu-item :index="y.permissionUrl" style="padding-left: 60px;" :class="y.permissionIcon">{{ y.label }}
                </el-menu-item>
              </template>
            </el-submenu>
          </template>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view />
        </el-main>
        <el-footer>
          Footer
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>
<script>
  export default {
    name: "index",
    data() {
      return {
        fileUrl:this.$fileUrl,
        userImage:'',
        username: '',
        permissionList: [],
      };
    },
    methods: {
      logout() {
        let _this = this;
        this.$axios({
          headers:{
            "Authorization":this.name,
          },
          url:'user/logout/' + this.username,
          method: 'get',
        }).then(()=>{
          // 清空sessionStorage 中的数据
          sessionStorage.clear();
          // 跳转到 login.vue
          setTimeout(()=>{
            _this.$router.push('/');
          },1500);
        })
      },
      goEditPassword() {
        this.$message.error("功能正在实现中");
      },
      systemManage() {
        this.$message.error("功能正在实现中");
      },
      loadPermission() {
        let _this = this;
        this.$axios({
          headers:{
            "Authorization": this.username,
          },
          url: 'permission/permissionList/' + _this.username,
          method: 'get',
        }).then(res => {
          _this.permissionList = res.data.data;
        }).catch(error => {
          _this.$message.error(error);
        }).catch(error=>{
          _this.$message.error(error);
        })
      }
    },
    mounted() {
      this.username = sessionStorage.getItem('loginUsername');
      this.userImage = sessionStorage.getItem('loginUserImage');
      this.loadPermission();
    }
  };

</script>

<style>
  html,
  body,
  #app,
  .el-container {
    height: 99.9%;
    margin-top: 0px;
  }

  .el-footer {
    background-color: #b3c0d1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .login-one {
    background-color: #1c77ac;
    background-repeat: no-repeat;
    overflow: hidden;
    height: 100%;
  }

  .el-main,
  .el-footer {
    border: 1px blue solid;
  }
</style>
