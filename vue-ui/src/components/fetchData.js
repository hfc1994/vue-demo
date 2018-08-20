/**
 * Created by user-hfc on 2018/8/19.
 */
import axios from 'axios'

class API {
  getItemList () {
    return instance.get('/item/getItems')
  }
}

const baseUrl = '/'

let instance = axios.create({
  baseURL: baseUrl
})

const api = new API()

export {api}
