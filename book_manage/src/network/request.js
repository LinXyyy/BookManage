import axios from "axios";
import store from "@/store/index"

export function network(config) {
    const instance = axios.create({
        baseURL: '/api/BookManage_war_exploded',
        headers: {
            'account': store.state.account
        }
    });

    instance.interceptors.request.use(config => {
        return config
    }, error => {

    })

    instance.interceptors.response.use(res => {
        return res.data
    }, error => {

    })

    return instance(config);
}
