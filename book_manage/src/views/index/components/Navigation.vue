<template>
  <v-navigation-drawer clipped permanent app color="#F7F7F7">
    <v-list style="text-align: center;">

      <v-list-item link exact to="/allBook" color="#09AAFF">
        <v-list-item-content>
          <v-list-item-title>
            <v-icon>mdi-book</v-icon>
            全部图书
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <div v-if="$store.state.userRole === 0">
        <v-list-item color="#09AAFF" link to="/myBook">
          <v-list-item-content>
            <v-list-item-title>我的书籍</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </div>
      <div v-else>
        <v-list-item color="#09AAFF" link to="/log">
          <v-list-item-content>
            <v-list-item-title>查看日志</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </div>
    </v-list>
  </v-navigation-drawer>
</template>

<script>

  export default {
    name: "Navigation"
  }
</script>

<style scoped>

</style>