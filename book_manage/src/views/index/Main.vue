<template>
  <v-app>
    <navigation></navigation>

    <v-main style="margin: 0;padding: 0;">
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
  import AppBar from "@/views/index/components/AppBar";
  import Navigation from "@/views/index/components/Navigation";
  export default {
    name: "Main",
    components: {Navigation, AppBar}
  }
</script>

<style scoped>

</style>