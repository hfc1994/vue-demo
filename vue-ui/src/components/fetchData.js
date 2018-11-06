/**
 * Created by user-hfc on 2018/8/19.
 */
import axios from 'axios'

class API {
  getItemList () {
    return instance.get(`/item/getItems`)
  }
  getFilmByYear (numLimit) {
    return instance.get(`/film/queryFilmByYear/${numLimit}`)
  }
  getFilmByType () {
    return instance.get(`/film/queryByType`)
  }
  getFilmByStar () {
    return instance.get(`/film/queryByStar`)
  }
  getBookByPublishing (tag) {
    return instance.get(`/book/queryByPublishing/${tag}`)
  }
  getBookByPrices (price) {
    return instance.get(`/book/queryByPrices/${price}`)
  }
  getBookByScores (score) {
    return instance.get(`/queryByScores/${score}`)
  }
  getMockData () {
    return instance.get(`/mock/getMockData`)
  }
}

const baseUrl = '/'
let instance = axios.create({
  baseURL: baseUrl
})

const api = new API()

export {api}
