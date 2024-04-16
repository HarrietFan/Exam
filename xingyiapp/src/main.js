// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false

// 引入elementui
import ElementUI from 'element-ui';
// 引入elementui样式
import 'element-ui/lib/theme-chalk/index.css';
// 全局注册使用ElementUI
Vue.use(ElementUI)

// 引入axios
import axios from 'axios'
// 设置代理的域名
axios.defaults.baseURL = 'http://localhost:8080/';
// 设置cookie,session跨域配置
axios.defaults.withCredentials=true;
// 设置post请求体
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.headers.put['Content-Type'] = 'application/json'
// 设置全局 axios 写法
Vue.prototype.$axios = axios

// 定义一个图片请求路劲
Vue.prototype.$fileUrl = 'http://localhost:8080/images/';

// 导航钩子，前端权限配置
router.beforeEach(function (to,from,next) {
  let username = sessionStorage.getItem("loginUsername");
  if(to.meta.isLogin){
    if(username != null){
      next();
    }else {
      next('/'); // 未登录
    }
  }else{
    next();// 放行
  }
})


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
