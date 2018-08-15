<template>
  <div>
    <p><span>{{ msg }}</span></p>
    <router-view></router-view>
  </div>
</template>

<script>
export default{
  name: 'modelView',
  props: ['type', 'id', 'name', 'tabType'],
  data () {
    return {
      msg: '暂无有效信息'
    }
  },
  created () {
    this.$router.push({name: 'model', params: {'name': this.name, 'id': this.id, 'type': this.type}})
  },
  watch: {
    $route (to, from) {
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
