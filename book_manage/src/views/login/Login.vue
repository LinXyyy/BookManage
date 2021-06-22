<template>
  <v-card :img="backgroundImage" width="100vw" height="100vh">

    <div style="text-align: center">
      <v-dialog v-model="isRegister" width="400px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="ma-2" outlined color="#FB7299" style="float: right" v-on="on" v-bind="attrs">
            注册账号
          </v-btn>
        </template>
        <register></register>
      </v-dialog>
    </div>

    <div class="content">

<!--      <h1>图书管理系统</h1>-->

      <input type="text" id="id" name="account" v-model="account" autocomplete="off" placeholder="账号">

      <input type="password" id="password" v-model="password" name="password" placeholder="密码">

      <button class="login" id="login" @click="login(account, password)">登录</button>

    </div>

    <v-snackbar v-model="snackbar" :timeout="timeout" color="#FB7299">
      {{ snackbarText }}
    </v-snackbar>
  </v-card>
</template>

<script>
  import {network} from "@/network/request";
  import Register from "@/views/login/components/Register";

  export default {
    name: "Login",
    components: {Register},
    data:() => ({
      backgroundImage: require('@/assets/backgroundimage.jpg'),
      account: '',
      password: '',
      snackbar: false,
      timeout: 1000,
      snackbarText: '',
      isRegister: false
    }),

    methods: {
      login(account, password) {

        if (account === '' || password === '') {
          this.snackbarText = '账号或密码为空'
          this.snackbar = !this.snackbar
          return
        }

        let user = this.$qs.stringify({
          account,
          password
        })

        network({
          url: '/login',
          method: 'post',
          data: user
        }).then(res => {
          if (res.resultCode === 0) {
            this.$store.commit('setAccount', account)
            this.$store.commit('setUserRole', res.data.userRole)
            this.$router.push("/allBook")
          }
          if (res.resultCode === 1) {
            this.snackbarText = '账号或密码错误'
            this.snackbar = !this.snackbar
          }
        })
      }
    }
  }
</script>

<style scoped>
  .content{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    text-align: center;
  }
  .content h1{
    color: #000000;
    text-transform: uppercase;
    font-weight: 500;
    margin: 20px;
  }
  .content input[type="text"],.content input[type="password"]{
    background: none;
    display: block;
    margin: 20px auto;
    text-align: center;
    border: 2px solid #3498db;
    padding: 10px 10px;
    width: 250px;
    outline: none;
    border-radius: 24px;
    transition: 0.25s;
  }
  .content input[type="text"]:focus,.content input[type="password"]:focus{
    width: 300px;
    border-color: #2ecc71;
  }
  .content .login{
    background: none;
    display: block;
    margin: 1px auto;
    text-align: center;
    border: 2px solid #2ecc71;
    padding: 10px 40px;
    outline: none;
    border-radius: 24px;
    transition: 0.25s;
    cursor: pointer;
  }
  .content .login:hover{
    background: #2ecc71;
    color: white;
  }
</style>