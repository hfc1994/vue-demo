import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import second from '../components/second.vue'
import publish from '../components/publish.vue'
import model from '../components/model.vue'
import graph from '../components/graph.vue'

Vue.use(Router)

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '*',
      redirect: '/publish'
    }, {
      path: '/helloworld',
      name: 'HelloWorld',
      component: HelloWorld,
      children: [{
        path: 'second/:dataType/:num',
        name: 'second',
        component: second
      }]
    }, {
      path: '/publish',
      name: 'publish',
      component: publish,
      children: [{
        path: 'model/:id/:type/:tabType',
        name: 'model',
        component: model
      }, {
        path: 'graph/:tabType/:name/:type',
        name: 'graph',
        component: graph
      }]
    }
  ]
})
