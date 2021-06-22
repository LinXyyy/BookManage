<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
        <tr>
          <th class="text-center">
            书名
          </th>
          <th class="text-center">
            种类
          </th>
          <th class="text-center">
            出版社
          </th>
          <th class="text-center" v-if="!($route.path === '/myBook')">
            数量
          </th>
          <th class="text-center" v-if="$route.path === '/myBook'">
            借书日期
          </th>
          <th class="text-center">
            价格
          </th>
          <th class="text-center">
            操作
          </th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="book in books" class="text-center">
          <td>
            {{ book.bookName }}
          </td>
          <td>
            {{ book.bookType }}
          </td>
          <td>
            {{ book.publisher }}
          </td>
          <td v-if="!($route.path === '/myBook')">
            {{ book.count }}
          </td>
          <td v-if="$route.path === '/myBook'">
            {{ date(book.date) }}
          </td>
          <td>
            {{ book.price }}
          </td>
          <td>
            <div v-if="$store.state.userRole === 1">
              <v-btn outlined color="#FB7299" @click="deleteBook(book.bookId)" style="margin: 0 5px">删除</v-btn>
              <v-btn outlined color="#FF35FF" @click="updateBook(book.bookId)" style="margin: 0 5px">修改</v-btn>
            </div>
            <div v-else>
              <div v-if="$route.path === '/allBook'">
                <v-btn outlined color="#66FF66" @click="borrowBook(book.bookId)" :disabled="book.count === 0" style="margin: 0 5px">
                  借阅
                </v-btn>
              </div>
              <div v-else>
                <v-btn outlined color="#66FF66" @click="stillBook(book.bookId)" style="margin: 0 5px">
                  还书
                </v-btn>
              </div>
            </div>
          </td>
        </tr>
        </tbody>
      </template>
    </v-simple-table>

    <v-dialog v-model="showUpdateCard" width="400px">
      <update-card :book-id="bookId"></update-card>
    </v-dialog>

    <v-dialog v-model="showBorrowCard" width="400px">
      <borrow-card :book-id="bookId"></borrow-card>
    </v-dialog>

    <v-snackbar :timeout="timeOut" v-model="snackbar">
      {{ snackbarText }}
    </v-snackbar>
  </div>
</template>

<script>
  import BorrowCard from "@/views/index/components/allBook/components/BorrowCard";
  import {network} from "@/network/request";
  import {getAllBook, getMyBook} from "@/method/method";
  import UpdateCard from "@/views/index/components/allBook/components/updateCard";
  import * as dayjs from "dayjs";

  export default {
    name: "BookTable",
    components: {UpdateCard, BorrowCard},
    data() {
      return {
        showBorrowCard: false,
        showUpdateCard: false,
        bookId: '',
        snackbarText: '',
        snackbar: false,
        timeOut: 1000
      }
    },
    computed: {
      date() {
        return function (date) {
          return dayjs(date).format('YYYY-MM-DD HH:mm:ss')
        }
      },
    },
    props: {
      books: ''
    },
    methods: {
      deleteBook(bookId) {
        bookId = this.$qs.stringify({
          bookId
        })
        network({
          url: '/deleteBook',
          method: 'post',
          data: bookId
        }).then(res => {
          if (res.resultCode === 2) {
            this.snackbar = !this.snackbar
            this.snackbarText = '此书正在被借阅,无法删除'
          } else {
            this.snackbar = !this.snackbar
            this.snackbarText = '删除成功'
            getAllBook()
          }
        })
      },
      updateBook(bookId) {
        this.bookId = bookId
        this.showUpdateCard = !this.showUpdateCard
      },
      borrowBook(bookId) {
        this.bookId = bookId
        this.showBorrowCard = !this.showBorrowCard
      },
      stillBook(bookId) {

        bookId = this.$qs.stringify({
          bookId
        })

        network({
          url: '/stillBook',
          method: 'post',
          data: bookId
        }).then(res => {
          if (res.resultCode === 0) {
            this.snackbar = !this.snackbar
            this.snackbarText = '还书成功'
            getMyBook()
          }
          if (res.resultCode === 1) {
            this.snackbar = !this.snackbar
            this.snackbarText = '还书失败'
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>