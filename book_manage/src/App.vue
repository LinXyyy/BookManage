<template>
  <v-app style="background-color: #F9F9F9">
    <app-bar v-if="showAppBar"></app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<style lang="scss">

</style>
<script>
  import AppBar from "@/views/index/components/AppBar";
  export default {
    components: {AppBar},
    computed: {
      showAppBar() {
        return this.$route.path !== '/login'
      }
    }
  }
</script>