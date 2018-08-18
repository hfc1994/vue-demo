<template>
    <div class="second">
        <h1>{{ msg }}</h1>
        <h1>dataType:{{ $route.params.dataType }}</h1>
        <h2>num:{{ $route.params.num }}</h2>
        <a href="/">回首页</a>
        <!-- <router-link to="/second/postgresql/20">
        新数据
        </router-link> -->
        <button @click="newData()">新数据</button>
        <button @click="toIndex()">回首页</button>
    </div>
</template>

<script>
export default {
  name: 'second',
  data () {
    return {
      msg: 'welcome to the second page'
    }
  },
  beforeRouteUpdate (to, from, next) {
    next()
  },
  methods: {
    newData: function () {
      this.$router.push({name: 'second', params: {'dataType': 'postgresql', 'num': '20'}})
    },
    toIndex: function () {
      this.$router.push({name: 'HelloWorld'})
    }
  }
}
</script>

<style>
h1{
    font-weight: bold;
}

a{
    text-decoration-style: none;
}
</style>
