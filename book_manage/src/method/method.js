import {network} from "@/network/request";
import store from "@/store/index"

export async function getAllBook() {
    network({
        url: '/queryBook',
        method: 'get'
    }).then(res => {
        store.commit("setBooks", res.data.books)
    })
}
export async function getMyBook() {
    network({
        url:  '/queryBookOnBorrowed',
        method: 'get'
    }).then(res => {
        store.commit('setBooks', res.data)
    })
}