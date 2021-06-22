<template>
  <v-card height="260px">
    <v-card-title>
      注册账号
    </v-card-title>

    <v-card-text>
      <v-text-field label="账号" outlined dense v-model="account" :rules="check(this.account, 'account')"></v-text-field>
      <v-text-field label="密码" outlined dense type="password" v-model="password" :rules="check(this.password, 'password')"></v-text-field>
    </v-card-text>

    <v-card-actions>
      <v-row>
        <v-spacer></v-spacer>
        <v-btn color="#12B7F5" style="color: #FFFFFF" @click="register">
          注册
        </v-btn>
        <v-spacer></v-spacer>
      </v-row>
    </v-card-actions>

    <v-snackbar bottom v-model="snackbar" color="#FB7299" timeout="1000">
      {{ snackBarTip }}
    </v-snackbar>
  </v-card>
</template>

<script>
  import {network} from "@/network/request";
  import router from "@/router";

  export default {
    name: "Register",
    data: () => ({
      account: '',
      password: '',
      accountIsNull: ['请输入账号'],
      passwordIsNull: ['请输入密码'],
      snackbar: false,
      snackBarTip: ''
    }),
    methods: {
      check(input, type) {
        if (input === '') {
          return type === 'account' ? this.accountIsNull : this.passwordIsNull
        }
      },

      register() {
        let account = this.account;
        let password = this.password;

        if (account === '' || password === '') {
          this.snackBarTip = '请输入账号密码'
          this.snackbar = true
          return
        }

        let userInfo = this.$qs.stringify({
          account,
          password
        })

        network({
          url: '/registerUser',
          method: 'post',
          data: userInfo
        }).then(res => {
          if (res.resultCode === 1) {
            this.snackBarTip = '账号已存在'
            this.snackbar = true
          }
          if (res.resultCode === 2) {
            this.snackBarTip = '注册失败'
            this.snackbar = true
          }
          if (res.resultCode === 0) {
            this.snackBarTip = '注册成功,即将自动登录'
            this.snackbar = true
            this.$store.commit('setAccount', account)
            this.$store.commit('setUserRole', 0)

            setTimeout(function () {
              router.push('/allBook')
            }, 1500)
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>