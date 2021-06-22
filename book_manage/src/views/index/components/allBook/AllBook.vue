<template>
  <div>
    <tool-bar></tool-bar>
    <v-divider></v-divider>
    <book-table :books="this.$store.state.books"></book-table>
  </div>
</template>

<script>
  import {network} from "@/network/request";
  import ToolBar from "@/views/index/components/allBook/components/ToolBar";
  import BookTable from "@/views/index/components/allBook/components/BookTable";
  import {getAllBook} from "@/method/method";

  export default {
    name: "AllBook",
    components: {BookTable, ToolBar},
    data:() =>({
      showBorrowCard: false
    }),

    created() {
      getAllBook()
    }
  }
</script>

<style scoped>

</style>