<template>
  <div>
    <book-table :books="this.$store.state.books"></book-table>
  </div>
</template>

<script>
  import {network} from "@/network/request";
  import BookTable from "@/views/index/components/allBook/components/BookTable";
  import {getMyBook} from "@/method/method";

  export default {
    name: "MyBook",
    components: {BookTable},
    created() {
      getMyBook()
    }
  }
</script>

<style scoped>

</style>