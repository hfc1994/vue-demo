<template>
  <div class="body">
    <div class="head"></div>
    <div class="content">
      <div class="sidebar">
        <el-button type="primary" :disabled="packDisabled" size="mini" :icon="packIcon"
          style="background-color: rgba(255, 255, 255, 0.57)" @click="changeTreeBar" title="收起">
        </el-button>
        <el-button type="primary" :disabled="taskDisabled" size="mini" :icon="taskIcon"
          style="margin-left: 0px; margin-top: 15px" title="任务" @click="showDialog">
        </el-button>
        <el-button type="primary" :disabled="packDisabled" size="mini" icon="el-icon-more"
          style="margin-left: 0px; margin-top: 15px;background-color: rgba(255, 255, 255, 0.57)" @click="popMsgBox" title="弹窗">
        </el-button>
        <el-button type="primary" :disabled="packDisabled" size="mini" icon="el-icon-more"
                   style="margin-left: 0px; margin-top: 15px;background-color: rgba(255, 255, 255, 0.57)" @click="fetchItemData" title="取数据">
        </el-button>
      </div>
      <transition name="fade">
        <div class="treebar" v-show="isShow">
          <!-- <el-button type="primary" size="mini" icon="el-icon-download" title="加载"
            @click="loadModel" style="margin-top: 50px"></el-button> -->
          <el-tree :data="modelSource" :default-expanded-keys="['1']" :props="defaultProps"
            @node-click="handleNodeClick" id="the-el-tree" highlight-current accordion></el-tree>
        </div>
      </transition>
      <div class="detail">
        <transition name="fade">
          <div id="top-bar">{{ navigation }}</div>
        </transition>
        <el-tabs type="border-card" @tab-click="clickTabs" :value="tabType">
          <el-tab-pane :label="tabOne" name="one">
            <div v-if="tabType === 'one'">
              <model-view :type="type" :id="id" :tabType="tabType"></model-view>
            </div>
          </el-tab-pane>
          <el-tab-pane :label="tabTwo" name="two">
            <div v-if="tabType === 'two'">
              <model-view :type="type" :id="id" :tabType="tabType"></model-view>
            </div>
          </el-tab-pane>
          <el-tab-pane :label="tabThree" name="three">
            <div v-if="tabType === 'three'">
              <model-view :type="type" :id="id" :tabType="tabType"></model-view>
            </div>
          </el-tab-pane>
          <el-tab-pane :label="tabFour" name="four">
            <div v-if="tabType === 'four'">
              <model-view :type="type" :id="id" :tabType="tabType"></model-view>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <el-dialog title="显示新图表" :visible.sync="dialogVisible" id="graphDialog"
                :width="dialogWidth">
      <ele-graph @zoomEvent="handleChildZoom"></ele-graph>
    </el-dialog>
  </div>
</template>

<script>
/* eslint-disable */
import modelView from './modelView.vue'
import model from './model.vue'
import {api} from './fetchData.js'
import eleGraph from './eleGraph.vue'

export default {
  name: 'publish',
  components: {modelView, model, eleGraph},
  data () {
    return {
      packDisabled: false,
      taskDisabled: false,
      packIcon: 'el-icon-arrow-left',
      taskIcon: 'el-icon-document',
      isShow: true,
      tabType: 'one',
      modelSource: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      id: '',
      type: '',
      tabOne: '节点1',
      tabTwo: '节点2',
      tabThree: '节点3',
      tabFour: '节点4',
      navigation: '',
      dialogVisible: false,
      dialogWidth: '60%'
    }
  },
  created: function () {
    let self = this
    api.getItemList().then(function (response) {
      self.modelSource = response.data
    }).catch(function (error) {
      console.log(error)
    })
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
      let ppid = setTimeout(function() {
        let e = document.createEvent('Event')
        e.initEvent('resize', true, true)
        window.dispatchEvent(e)

        clearTimeout(ppid)
      }, 700)// 树消失的动画是0.5秒
    },
    clickTabs: function (tab) {
      if (tab.index === '0') {
        this.tabType = 'one'
      } else if (tab.index === '1') {
        this.tabType = 'two'
      } else if (tab.index === '2'){
        this.tabType = 'three'
      } else {
        this.tabType = 'four'
      }
    },
    handleNodeClick: function (data, node) {
      if (node.isLeaf) {
        this.navigation = node.parent.data.name + '>' + data.name
        this.id = node.parent.data.id
        this.handleTabName(data.type)
        this.type = node.parent.data.type + "_" + data.type
        this.$router.push({ name: 'model', params: {id: this.id, type: this.type, tabType: this.tabType} })
      }
    },
    handleTabName: function (type) {
      this.tabOne = '按年份'
      this.tabTwo = '按评分'
      this.tabFour = '按关键字'
      if (type.indexOf('film') !== -1) {
        this.tabThree = '按类型'
      } else if (type.indexOf('book') !== -1) {
        this.tabThree = '按出版社'
      } else if (type.indexOf('music') !== -1) {
        this.tabThree = '按歌手'
      } else if (type.indexOf('phone') !== -1) {
        this.tabTwo = '按跑分'
        this.tabThree = '按厂商'
        this.tabFour = '按价格'
      } else if (type.indexOf('computer') !== -1) {
        this.tabTwo = '按跑分'
        this.tabThree = '按cpu'
        this.tabFour = '按价格'
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
        //直接右上角“×”关闭不会触发此处,还会有个异常
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
              h1('textarea', {style: 'width: 100%;height: 90px;'}, '看console.log')
            ])
          })
        })
      })
    },
    showDialog: function () {
      this.dialogVisible = true
      this.dialogWidth = '60%'
    },
    handleChildZoom: function (val) {
      this.dialogWidth = val
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
  /*flex: 0 0 250;*/
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

#top-bar {
  background-color: rgb(255, 255, 255);
  text-align: left;
  height: 25px;
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

/*.is-current.is-focusable{*/
  /*background-color: rgb(255, 255, 255);*/
/*}*/
</style>
