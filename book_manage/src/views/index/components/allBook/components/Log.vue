<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
      <tr>
        <th class="text-center">
          操作人
        </th>
        <th class="text-center">
          操作
        </th>
        <th class="text-center">
          时间
        </th>
        <th class="text-center">
          对象
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="log in logs" class="text-center">
        <td>{{ log.userName }}</td>
        <td>{{ log.methodName }}</td>
        <td>{{ date(log.date) }}</td>
        <td>{{ log.bookName }}</td>
      </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
  import {network} from "@/network/request";
  import * as dayjs from "dayjs";

  export default {
    name: "Log",
    data() {
      return {
        logs: ''
      }
    },
    computed: {
      date() {
        return function (date) {
          return dayjs(date).format('YYYY-MM-DD HH:mm:ss')
        }
      },
    },
    methods: {
      getLogs() {
        network({
          url: '/getLog',
          method: 'get'
        }).then(res => {
          this.logs = res.data
        })
      }
    },
    created() {
      this.getLogs()
    }
  }
</script>

<style scoped>

</style>