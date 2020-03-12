<template>
  <div class="body">
    <div class="head"></div>
    <div class="content">
      <div class="sidebar">
        <el-button type="primary" :disabled="packDisabled" size="mini" :icon="packIcon"
          style="background-color: rgba(255, 255, 255, 0.57)" @click="changeTreeBar" title="收起">
        </el-button>
        <el-button type="primary" :disabled="taskDisabled" size="mini" :icon="taskIcon"
          style="margin-left: 0px; margin-top: 15px" title="饿了么ECharts的VUE封装库" @click="showDialog">
        </el-button>
        <el-button type="primary" :disabled="taskDisabled" size="mini" :icon="taskIcon"
          style="margin-left: 0px; margin-top: 15px" title="百度Echarts的VUE封装库" @click="showDialog2">
        </el-button>
        <el-button type="primary" :disabled="taskDisabled" size="mini" :icon="taskIcon"
          style="margin-left: 0px; margin-top: 15px" title="自定义demo" @click="showDialog3">
        </el-button>
        <el-button type="primary" :disabled="taskDisabled" size="mini" :icon="taskIcon"
          style="margin-left: 0px; margin-top: 15px" title="任务4" @click="showDialog4">
        </el-button>
        <el-button type="primary" :disabled="taskDisabled" size="mini" :icon="taskIcon"
          style="margin-left: 0px; margin-top: 15px" title="任务4" @click="showDialog5">
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
    <el-dialog title="饿了么ECharts封装库" :visible.sync="dialogVisible" id="graphDialog"
                :width="dialogWidth">
      <ele-graph @zoomEvent="handleChildZoom"></ele-graph>
    </el-dialog>
    <el-dialog title="百度ECharts封装库" :visible.sync="dialogVisible2" id="graphDialog2"
                :width="dialogWidth2">
      <vue-e-charts @zoomEvent="handleChildZoom2" :index="1"></vue-e-charts>
      <!-- <vue-e-charts @zoomEvent="handleChildZoom2" :index="2"></vue-e-charts> -->
    </el-dialog>
    <el-dialog title="自定义测试demo" :visible.sync="dialogVisible3" id="graphDialog3"
                width="60%">
      <test-charts></test-charts>
    </el-dialog>
    <el-dialog title="控制器" :visible.sync="dialogVisible4" id="svgTest" width="60%">
      <control-tool :init-height="180" :init-width="180"></control-tool>
    </el-dialog>
    <el-dialog title="测试" :visible.sync="dialogVisible5" id="zujianTest" width="60%">
      <el-col :span="11">
        <el-date-picker type="datetimerange"
          range-separator="至"
          start-placeholder="开始"
          end-placeholder="结束"
          value-format="yyyy-MM-dd HH:mm:ss.SSS"
          v-model="datetime"
          :clearable="false"
          size="mini"
          style="width:100%;">
        </el-date-picker>
      </el-col>
      <el-col>
         <el-select v-if="item.type==='A'" v-model="item.value5[0]" clearable placeholder="请选择">
           <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>

        <el-select v-else-if="item.type==='B'" v-model="item.value5" clearable multiple style="margin-left: 20px;" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
        <el-button @click="click">kkk</el-button>
      </el-col>
    </el-dialog>
  </div>
</template>

<script>
/* eslint-disable */
import modelView from './modelView.vue'
import model from './model.vue'
import {api} from './fetchData.js'
import eleGraph from './eleGraph.vue'
import vueECharts from './vueECharts.vue'
import testCharts from './testCharts.vue'
import controlTool from './controlTool.vue'

export default {
  name: 'publish',
  components: {
    modelView,
    model,
    eleGraph,
    vueECharts,
    testCharts,
    controlTool,
  },
  data () {
    return {
      datetime: ['2019-02-12 14:09:30.123',''],
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
      dialogWidth: '60%',
      dialogVisible2: false,
      dialogVisible3: false,
      dialogVisible4: false,
      dialogVisible5: false,
      dialogWidth2: '60%',
      options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
      item: {
        type: 'A',
        value5: []
      }
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
    click() {
      if (this.item.type === 'A') {
        this.item.type = 'B'
      } else {
        this.item.type = 'A'
      }
    },
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
    },
    handleChildZoom2: function (val) {
      this.dialogWidth2 = val
    },
    showDialog2: function () {
      this.dialogVisible2 = true
      this.dialogWidth2 = '60%'
    },
    showDialog3: function () {
      this.dialogVisible3 = true
    },
    showDialog4: function () {
      this.dialogVisible4 = true
    },
    showDialog5: function () {
      this.dialogVisible5 = true
    },
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
