<template>
  <div>
    <v-toolbar flat height="60px">
      <v-row>
        <v-col v-if="this.$store.state.userRole === 1">
          <v-dialog v-model="isAddBook" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-btn outlined color="#FF0000" v-bind="attrs" v-on="on">添加图书</v-btn>
            </template>
            <add-book-card></add-book-card>
          </v-dialog>
        </v-col>
        <v-spacer></v-spacer>
        <v-col cols="3">
          <v-menu offset-y :close-on-content-click="closeOnContentClick">
            <template v-slot:activator="{ on, attrs }">
              <v-text-field outlined background-color="#F1F2F4"
                            label="搜索" append-icon="mdi-book-search" dense
                            hide-details class="searchInput"
                            v-bind="attrs" v-on="on" readonly></v-text-field>
            </template>
            <v-card class="text-center">
              <div style="width: 200px; margin: auto">
                <v-select
                    v-model="type"
                    :items="states"
                    menu-props="auto"
                    label="请选择搜索类型"
                    hide-details
                    single-line style="margin: 10px 0"
                ></v-select>
                <v-text-field label="请输入搜索条件" v-model="enter" style="margin: 10px 0"></v-text-field>
                <v-btn @click="search" outlined color="#00FF00" style="margin: 10px 0">搜索</v-btn>
              </div>
            </v-card>
          </v-menu>

        </v-col>
      </v-row>
    </v-toolbar>
    <v-snackbar v-model="snackbar" :timeout="timeOut" color="#FB7299">
      {{ snackbarText }}
    </v-snackbar>
  </div>
</template>

<script>
  import AddBookCard from "@/views/index/components/allBook/components/addBookCard";
  import {network} from "@/network/request";
  export default {
    name: "ToolBar",
    components: {AddBookCard},
    data:() => ({
      isAddBook: false,
      type: '',
      states: [
          '名称',
          '类型',
          '出版社'
      ],
      closeOnContentClick: false,
      enter: '',
      snackbar: false,
      timeOut: 1000,
      snackbarText: ''
    }),
    methods: {
      search() {
        let bookType
        let bookName
        let publisher
        if (this.type === '类型') {
          bookType = this.enter
        }
        if (this.type === '名称') {
          bookName = this.enter
        }
        if (this.type === '出版社') {
          publisher = this.enter
        }

        let condition = this.$qs.stringify({
          bookType,
          bookName,
          publisher
        })

        network({
          url: 'queryBook',
          method: 'post',
          data: condition
        }).then(res => {
          if (res.resultCode === 0) {
            this.$store.commit('setBooks', res.data.books)
          }
          if (res.resultCode === 1) {
            this.snackbar = true
            this.snackbarText = '当前条件下没有图书'
          }
        })

      },
    }
  }
</script>

<style scoped>

</style>