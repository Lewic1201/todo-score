import axios from 'axios'
import router from './../router'

// http request 拦截器
axios.interceptors.request.use(
  function (config) {
    if (localStorage.getItem('token')) {
      config.headers.token = localStorage.getItem('token')
    }
    return config
  },
  function (err) {
    return Promise.reject(err)
  }
);

// http response 拦截器
axios.interceptors.response.use(
  function (response) {
    return response
  },
  function (error) {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // 401 清除 token 信息并跳转到登录页面
          localStorage.removeItem('token');   //  清除本地的token
          // 只有在当前路由不是登录页面才跳转
          router.currentRoute.path !== '/login' &&
          router.replace({
            path: '/login',
            query: {
              redirect: router.currentRoute.path
            }
          })
      }
    }
    // console.log(JSON.stringify(error))
    // console : Error: Request failed with status code 402
    return Promise.reject(error.response.data)
  }
);

export default axios
