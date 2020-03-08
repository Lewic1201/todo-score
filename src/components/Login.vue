<template>
  <el-form>
    <el-form-item label="用户名" prop="username">
      <el-input v-model="loginReq.username"/>
    </el-form-item>
    <el-form-item label="密---码" prop="password">
      <el-input v-model="loginReq.password"/>
    </el-form-item>
    <el-button @click="toLogout()" size="medium">登出</el-button>
    <el-button @click="toLogin()" type="primary" size="medium">登录</el-button>
  </el-form>

</template>

<script>
  export default {
    data() {
      return {
        loginReq: {
          username: '',
          password: '',
        },
        tableData: [],
        loginRes: {
          token: ''
        }
      }
    },
    methods: {
      toLogin() {
        let postData = JSON.stringify({
          username: this.loginReq.username,
          password: this.loginReq.password,
        });
        this.axios({
          method: 'post',
          url: '/v1/login',
          data: postData
        }).then(response => {
          this.loginRes = response.data.data;
          localStorage.setItem('token', this.loginRes.token);
          this.$message({
            type: 'success',
            message: '登录成功!'
          });
          console.log(response);
          let redirect = decodeURIComponent(this.$route.query.redirect || '/')
          this.$router.push({path: redirect})
        }).catch(error => {
          console.log(error);
        });
      },
      toLogout() {
        localStorage.removeItem('token');
      }
    }
  }
</script>

<style scoped>
  .el-input {
    width: 400px;
  }

  .el-form {
    margin: 0 auto;
    padding: 200px;
    width: 600px;
    border: 1px solid #4a1233
  }
</style>
