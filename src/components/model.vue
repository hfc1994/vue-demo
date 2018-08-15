<template>
  <div id="model">
    <span>id:{{ id }}</span>
    <span>type:{{ type}}</span>
    <span>name:{{ name }}</span>
  </div>
</template>

<script>
import ModelView from './modelView.vue'

export default {
  components: {ModelView},
  name: 'model',
  data () {
    return {
      msg: '暂无有效数据',
      isShow: false,
      name: '',
      id: '',
      type: ''
    }
  },
  watch: {
    $route (to, from) {
      console.log('begin')
      if (this.$route.params.id === '') {
        this.msg = '暂无有效数据'
        this.isShow = false
      } else {
        this.msg = '信息如下'
        this.isShow = true
        this.name = this.$route.params.name
        this.id = this.$route.params.id
        this.type = this.$route.params.type
      }
    }
  }
}
</script>
