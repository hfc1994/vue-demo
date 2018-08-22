<template>
  <p v-if="isShow"><span>{{ msg }}</span></p>
  <router-view v-else></router-view>
</template>

<script>
/* eslint-disable */
export default{
  name: 'modelView',
  props: ['type', 'id', 'tabType'],
  data () {
    return {
      msg: 'modelView暂无有效数据',
      isShow: true
    }
  },
  created () {
  },
  mounted () {
    if (this.id !== null && this.id !== '') {
      this.$router.push({ name: 'model', params: {id: this.id, type: this.type, tabType: this.tabType} })
    }
  },
  watch: {
    $route (to, from) {
      console.log('modelView')
      if (to.path.indexOf("/model") !== -1)
      {
        if (this.$route.params.id === '') {
          this.msg = '暂无有效数据'
          this.isShow = true
        } else {
          this.id = this.$route.params.id
          this.type = this.$route.params.type
          this.tabType = this.$route.params.tabType
          this.isShow = false
        }
      }
    }
  }
}
</script>
