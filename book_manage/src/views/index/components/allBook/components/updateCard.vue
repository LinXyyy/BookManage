<template>
  <div>
    <v-card>
      <v-card-title>
        修改图书
      </v-card-title>

      <v-card-text>
        <v-text-field label="书名" outlined dense v-model="bookName"></v-text-field>
        <v-text-field label="类型" outlined dense v-model="bookType"></v-text-field>
        <v-text-field label="出版社" outlined dense v-model="publisher"></v-text-field>
        <v-text-field label="价格" outlined dense v-model="price"></v-text-field>
        <v-text-field label="数量" outlined dense v-model="count"></v-text-field>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn @click="addBook">
          确定
        </v-btn>
      </v-card-actions>
    </v-card>

    <v-snackbar v-model="showSnackbar" :timeout="timeOut">
      {{ snackbarText }}
    </v-snackbar>
  </div>
</template>

<script>
  import {network} from "@/network/request";
  import {getAllBook} from "@/method/method";

  export default {
    name: "updateCard",
    data:() => ({
      bookName: '',
      bookType: '',
      publisher: '',
      price: '',
      count: '',
      showSnackbar: false,
      timeOut: 1000,
      snackbarText: ''
    }),
    props: {
      bookId: ''
    },
    methods: {
      addBook() {
        if (this.bookName === '' && this.bookType === '' && this.publisher === '' && this.price === '' && this.count === '') {
          this.showSnackbar = !this.showSnackbar
          this.snackbarText = '未输入属性'
          return
        }

        let bookName
        let bookType
        let publisher
        let price
        let count
        let bookId = this.bookId

        if (this.bookName !== '') {
          bookName = this.bookName
        }
        if (this.bookType !== '') {
          bookType = this.bookType
        }
        if (this.publisher !== '') {
          publisher = this.publisher
        }
        if (this.price !== '') {
          price = this.price
        }
        if (this.count !== '') {
          count = this.count
        }

        let book = this.$qs.stringify({
          bookId,
          bookName,
          bookType,
          publisher,
          price,
          count
        })

        network({
          url: '/updateBook',
          method: 'post',
          data: book
        }).then(res => {
          if (res.resultCode === 0) {
            this.showSnackbar = !this.showSnackbar
            this.snackbarText = '修改成功'
            this.bookName = ''
            this.bookType = ''
            this.publisher = ''
            this.price = ''
            this.count = ''

            getAllBook()

          }
          if (res.resultCode === 1) {
            this.showSnackbar = !this.showSnackbar
            this.snackbarText = '修改失败'
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>