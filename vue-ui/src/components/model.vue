<template>
  <div id="model" v-show="!modelShow">
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
      <table v-else-if="tabType === 'three'">
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
    <table v-else>
      <p><span>暂时啥也没有</span></p>
    </table>
    </div>
    <el-button type="primary" icon="el-icon-caret-right"
               style="margin-top: 20px;" @click="graphClick"></el-button>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'model',
  data () {
    return {
      msg: 'model暂无有效数据',
      isShow: false,
      id: '',
      type: '',
      tabType: '',
      name: '',
      modelShow: false
    }
  },
  created () {
    this.id = this.$route.params.id
    let tmp = this.$route.params.type
    this.type = tmp.split("_")[1]     //film,music,book and so on
    this.name = tmp.split("_")[0]     //douban,maoyan and so on
    this.tabType = this.$route.params.tabType //one,two and so on
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
      if (to.path.indexOf('/model') !== -1) {
        if (this.$route.params.id === '') {
          this.msg = '暂无有效数据'
          this.isShow = false
        } else {
          this.msg = '信息如下'
          this.isShow = true
          this.id = this.$route.params.id
          let tmp = this.$route.params.type
          this.type = tmp.split("_")[1]
          this.name = tmp.split("_")[0]
          this.tabType = this.$route.params.tabType
        }
      }
    }
  },
  methods: {
    graphClick: function () {
      this.modelShow = true
      this.$router.push({name: 'graph', params: {tabType: this.tabType,name: this.name, type: this.type}})
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
