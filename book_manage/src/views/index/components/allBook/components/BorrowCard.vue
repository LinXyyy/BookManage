<template>
  <div>
    <v-card>
      <v-card-title>
        借阅
      </v-card-title>
      <v-card-text class="text-center">
        是否要借阅此书?
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn text style="margin: 0 5px" @click="borrowBook">是</v-btn>
      </v-card-actions>
    </v-card>
    <v-snackbar :timeout="timeOut" v-model="snackbar">
      {{ snackbarText }}
    </v-snackbar>
  </div>
</template>

<script>
  import {network} from "@/network/request";
  import {getAllBook, getMyBook} from "@/method/method";

  export default {
    name: "BorrowCard",
    data() {
      return {
        snackbarText: '',
        snackbar: false,
        timeOut: 1000
      }
    },
    props: {
      bookId: ''
    },
    methods: {
      borrowBook() {
        let bookId = this.bookId
        bookId = this.$qs.stringify({
          bookId
        })

        network({
          url: '/borrowBook',
          method: 'post',
          data: bookId
        }).then(res => {
          if (res.resultCode === 0) {
            this.snackbar = !this.snackbar
            this.snackbarText = '借书成功'
            getMyBook()
          }
          if (res.resultCode === 1) {
            this.snackbar = !this.snackbar
            this.snackbarText = '用户可借书数量已达上限'
          }
          if (res.resultCode === 3) {
            this.snackbar = !this.snackbar
            this.snackbarText = '你已拥有此书'
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>