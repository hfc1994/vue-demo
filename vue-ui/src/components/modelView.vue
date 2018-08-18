<template>
  <div>
    <p v-if="isShow"><span>{{ msg }}</span></p>
    <router-view v-else></router-view>
  </div>
</template>

<script>
/* eslint-disable */
export default{
  name: 'modelView',
  props: ['type', 'id', 'name', 'tabType'],
  data () {
    return {
      msg: '暂无有效数据',
      isShow: true
    }
  },
  mounted () {
//    this.$router.push({name: 'model', params: {name: this.name, id: this.id, type: this.type}})
  },
  watch: {
    $route (to, from) {
      if (this.$route.params.id === '') {
        this.msg = '暂无有效数据'
        this.isShow = true
      } else {
        this.name = this.$route.params.name
        this.id = this.$route.params.id
        this.type = this.$route.params.type
        this.tabType = this.$route.params.tabType
        this.isShow = false
      }
    }
  }
}
</script>
