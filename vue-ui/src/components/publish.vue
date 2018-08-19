<template>
  <div class="body">
    <div class="head"></div>
    <div class="content">
      <div class="sidebar">
        <el-button type="primary" :disabled="packDisabled" size="mini" :icon="packIcon"
          style="background-color: rgba(255, 255, 255, 0.57)" @click="changeTreeBar" title="收起">
        </el-button>
        <el-button type="primary" :disabled="taskDisabled" size="mini" :icon="taskIcon"
          style="margin-left: 0px; margin-top: 15px" title="任务">
        </el-button>
        <el-button type="primary" :disabled="packDisabled" size="mini" icon="el-icon-menu"
          style="margin-left: 0px; margin-top: 15px;background-color: rgba(255, 255, 255, 0.57)" @click="popMsgBox" title="弹窗">
        </el-button>
        <el-button type="primary" :disabled="packDisabled" size="mini" icon="el-icon-menu"
                   style="margin-left: 0px; margin-top: 15px;background-color: rgba(255, 255, 255, 0.57)" @click="fetchItemData" title="取数据">
        </el-button>
      </div>
      <transition name="fade">
        <div class="treebar" v-show="isShow">
          <!-- <el-button type="primary" size="mini" icon="el-icon-download" title="加载"
            @click="loadModel" style="margin-top: 50px"></el-button> -->
          <el-tree :data="modelSource" node-key="id" :default-expanded-keys="['1']" :props="defaultProps"
            @node-click="handleNodeClick" id="the-el-tree"></el-tree>
        </div>
      </transition>
      <div class="detail">
        <el-tabs type="border-card" @tab-click="clickTabs" :value="tabType">
          <el-tab-pane label="节点1" name="one">
            <div v-show="tabType === 'one'">
              <model-view :type="type" :id="id" :name="name" :tabType="tabType"></model-view>
            </div>
          </el-tab-pane>
          <el-tab-pane label="节点2" name="two">
            <div v-show="tabType === 'two'">
              <model-view :type="type" :id="id" :name="name" :tabType="tabType"></model-view>
            </div>
          </el-tab-pane>
          <el-tab-pane label="节点3" name="three">
            <div v-show="tabType === 'three'">
              <model-view :type="type" :id="id" :name="name" :tabType="tabType"></model-view>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import modelView from './modelView.vue'
import model from './model.vue'
import {api} from './fetchData.js'

export default {
  name: 'publish',
  components: {modelView, model},
  data () {
    return {
      packDisabled: false,
      taskDisabled: true,
      packIcon: 'el-icon-arrow-left',
      taskIcon: 'el-icon-document',
      isShow: true,
      tabType: 'three',
      modelSource: [{'name':'数据库列表','id':'1','children':[{'children':[{'name':'mysql001','id':'10001','type':'db'},{'name':'mysql002','id':'10002','type':'db'}],'name':'mysql','id':'mysql'},{'children':[{'name':'oracle001','id':'10003','type':'db'},{'name':'oracle002','id':'10004','type':'db'}],'name':'oracle','id':'oracle'},{'children':[{'name':'postgresql001','id':'10005','type':'db'},{'name':'postgresql002','id':'10006','type':'db'}],'name':'postgresql','id':'postgresql'}]}],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      id: '',
      name: '',
      type: ''
    }
  },
  created: function () {
//    this.id = '10002'
//    this.name = 'oracle10002'
//    this.type = 'db'
//    this.$router.push({name: 'model', params: {'id': this.id, 'name': this.name, 'type': this.type}})
  },
  methods: {
    changeTreeBar: function () {
      if (this.packIcon === 'el-icon-arrow-left') {
        this.packIcon = 'el-icon-arrow-right'
        this.title = '展开'
        this.isShow = false
      } else {
        this.packIcon = 'el-icon-arrow-left'
        this.title = '收起'
        this.isShow = true
      }
    },
    loadModel: function () {
      this.$router.push({name: 'model', params: {id: '10001', name: 'mysql', type: 'db', tabType: this.tabType}})
    },
    clickTabs: function (tab) {
      if (tab.index === '0') {
        this.tabType = 'one'
      } else if (tab.index === '1') {
        this.tabType = 'two'
      } else {
        this.tabType = 'three'
      }
      this.$router.push({ name: 'model', params: {id: this.id, name: this.name, type: this.type, tabType: this.tabType} })
    },
    handleNodeClick: function (data, node) {
      if (node.isLeaf) {
        this.id = node.parent.data.id
        this.name = data.name
        this.type = data.type
        this.$router.push({ name: 'model', params: {id: this.id, name: this.name, type: this.type, tabType: this.tabType} })
      }
    },
    popMsgBox: function () {
      let h = this.$createElement
      this.$msgbox({
        title: '使用说明',
        message: h('p', null, [
          h('textarea', { style: 'width: 100%;height: 90px;'}, '测试弹窗功能所使用的文字')
        ])
      }).then( action => {
        //测试发现，只有点击确定关闭才会有这个后续
        //直接右上角“×”关闭不会触发此处
        alert('后续')
      })
    },
    fetchItemData: function () {
      let msgbox = this.$msgbox
      let h1 = this.$createElement
      this.$msgbox({
        title: '情况',
        message: h1('p', null, [
            h1('textarea', { style: 'width: 100%;height: 90px;'}, '即将要向数据接口请求数据了')
        ])
      }).then(action => {
        api.getItemList().then(function (response) {
          console.log(response.data)
          msgbox({
            title: '结果',
            message: h1('p', null, [
              h1('textarea', {style: 'width: 100%;height: 90px;'}, response.data[0].item)
            ])
          })
        })
      })
    }
  }
}
</script>

<style>
* {
  margin: 0px;
  padding: 0px;
}

html,
body {
  height: 100%;
}

#app {
  height: 100%;
}

.body {
  height: 100%;
}

.head {
  height: 35px;
  width: 100%;
  background-color: rgb(64, 147, 255);
}

.content {
  top: 35px;
  height: 100%;
  width: 100%;
  position: absolute;
  display: flex;
}

.sidebar {
  width: 50px;
  height: 100%;
  background-color: rgb(64, 147, 255);
}

.treebar {
  flex: 0 0 250;
  left: 50px;
  width: 250px;
  height: 100%;
  background-color: rgb(248, 248, 248);
  box-shadow: 2px 0px 5px 0px rgb(131, 131, 131);
}

.detail {
  flex: 1 0;
  height: 100%;
  margin: 0px 10px;
  background-color: rgb(231, 231, 231);
  box-shadow: 0px 0px 4px 2px rgba(131, 131, 131, 0.7);
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

#the-el-tree{
  background-color: rgb(248, 248, 248);
  margin-left: 10px;
}
</style>
