<template>
  <v-app-bar app color="#FFFFFF" elevation="1" clipped-left>
    <v-row>
      <v-col cols="2">{{ title }}</v-col>

      <v-spacer></v-spacer>

      <v-btn color="#FB7299" @click="exit" text>退出登录</v-btn>
    </v-row>
  </v-app-bar>
</template>

<script>
  export default {
    name: "AppBar",
    data:() => ({
      title: '图书管理系统'
    }),
    methods: {
      exit() {
        this.$store.commit('setAccount', '')
        this.$store.commit('setBooks', '')
        this.$router.push("/")
      }
    }
  }
</script>

<style scoped>

</style>