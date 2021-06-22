<template>
  <div>
    <v-card>
      <v-card-title>
        添加图书
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
    name: "addBookCard",
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
    methods: {
      addBook() {
        if (this.bookName === '' || this.bookType === '' || this.publisher === '' || this.price === '' || this.count === '') {
          this.showSnackbar = !this.showSnackbar
          this.snackbarText = '存在未输入属性,请检查'
          return
        }

        let bookName = this.bookName
        let bookType = this.bookType
        let publisher = this.publisher
        let price = this.price
        let count = this.count

        let book = this.$qs.stringify({
          bookName,
          bookType,
          publisher,
          price,
          count
        })

        network({
          url: '/addBook',
          method: 'post',
          data: book
        }).then(res => {
          if (res.resultCode === 0) {
            this.showSnackbar = !this.showSnackbar
            this.snackbarText = '添加成功'
            this.bookName = ''
            this.bookType = ''
            this.publisher = ''
            this.price = ''
            this.count = ''

            getAllBook()

          }
          if (res.resultCode === 1) {
            this.showSnackbar = !this.showSnackbar
            this.snackbarText = '添加失败'
          }
          if (res.resultCode === 2) {
            this.showSnackbar = !this.showSnackbar
            this.snackbarText = '书库中已拥有此书'
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>