<template>
  <div id="model">
    <span>{{ msg }}</span>
    <div v-show="isShow" id="table">
      <table v-if="tabType === 'one'">
        <tr>
          <th>节点ID</th>
          <th>类型</th>
          <th>数据库名</th>
        </tr>
        <tr>
          <td>{{ tabType }}</td>
          <td>{{ type }}</td>
          <td>{{ name }}</td>
        </tr>
      </table>
      <table v-else-if="tabType === 'two'">
        <tr>
          <th>节点ID</th>
          <th>数据库ID</th>
          <th>类型</th>
          <th>数据库名</th>
        </tr>
        <tr>
          <td>{{ tabType }}</td>
          <td>{{ id }}</td>
          <td>{{ type }}</td>
          <td>{{ name }}</td>
        </tr>
      </table>
      <table v-else>
        <tr>
          <th>节点ID</th>
          <th>数据库ID</th>
          <th>数据库名</th>
        </tr>
        <tr>
          <td>{{ tabType }}</td>
          <td>{{ id }}</td>
          <td>{{ name }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'model',
  data () {
    return {
      msg: '暂无有效数据',
      isShow: false,
      name: '',
      id: '',
      type: '',
      tabType: ''
    }
  },
  created () {
    this.name = this.$route.params.name
    this.id = this.$route.params.id
    this.type = this.$route.params.type
    this.tabType = this.$route.params.tabType
    if (this.id === '') {
      this.msg = '暂无有效数据'
      this.isShow = false
    } else {
      this.msg = '信息如下'
      this.isShow = true
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
        this.tabType = this.$route.params.tabType
      }
    }
  }
}
</script>

<style>

table, td, th{
  margin:0 auto;
  border: solid 1px;
  border-collapse: collapse;
}

td,th{
  padding: 10px;
}

#table{
  margin-top: 10px;
}

#model{
  left: 0px;
  right: 0px;
}

</style>
