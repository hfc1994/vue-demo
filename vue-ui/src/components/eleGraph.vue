<template>
  <div id="graph">
    <span>图表名称：{{theName}}</span>
    <el-button size="mini" @click="changeChartType">切换图表类型</el-button>
    <el-button size="mini" @click="handleZoom">{{buttonText}}</el-button>
    <ve-chart :title="title" :data="chartData" :settings="chartSetting" ref="chartNode" style="width:100%;" v-if="chartType === 'normal'"></ve-chart>
    <ve-map :data="chartData" :settings="chartSetting" style="width: 100%;" v-else-if="chartType === 'map'"></ve-map>
    <ve-heatmap :data='chartData' :settings="chartSetting" style="width: 100%;" v-else-if="chartType === 'heatmap'"></ve-heatmap>
    <ve-wordcloud :data="chartWordData" :settings="chartSetting" ref="chartNode" style="width:100%;" v-else-if="chartType === 'wordcloud'"></ve-wordcloud>
    <ve-gauge :data="chartData" :settings="chartSetting" ref="chartNode" style="width:100%;" v-else-if="chartType === 'gauge'"></ve-gauge>
    <ve-scatter :data="chartData" :settings="chartSetting" ref="chartNode" style="width:100%;" v-else-if="chartType === 'scatter'"></ve-scatter>
  </div>
</template>

<script>
/* eslint-disable*/
import {api} from './fetchData.js'

export default {
  name: 'eleGraph',
  data () {
    return {
      loading: false,
      typeArray: [
        'histogram',
        'line',
        'pie',
        'rose',
        'map',
        'lineStack',  // 堆叠区域图
        'heatmap',
        // '矩形树图暂时没有'，
        'wordcloud',
        'radar',
        'gauge',  // 仪表盘
        'funnel', // 漏斗图
        'scatter',  // 散点图
        'bar',
        'histogramStack', // 堆叠柱状图
        'ring', // 环图
        'waterfall' // 瀑布图
        ],
      typeIndex: 0,
      chartData: {},
      chartSetting: {},
      buttonText: '放大',
      columns: [],
      rows: [],
      chartType: 'normal',
      theName: '',
      chartWordData: {}, // 词云使用的数据对象
      title: {}
    }
  },
  created () {
    api.getMockData().then(res => {
      this.columns = res.data.key.split(',')
      this.rows = res.data.value
      this.chartData.columns = this.columns
      this.chartData.rows = this.rows

      this.theName = this.typeArray[this.typeIndex] 
      this.chartSetting = {type: this.theName}
      this.title = {
        left: 'center',
        text: '图表切换测试',
        top: 20,
        textStyle: {
          color: '#ccc'
        }
      }
    }).catch(err => {
      console.log(err)
    })
  },
  methods: {
    changeChartType: function (){
      this.chartType = 'normal'
      if (this.typeIndex <= this.typeArray.length - 2) {
        this.typeIndex++
      } else {
        this.typeIndex = 0
      }
      this.chartSetting = {}
      this.theName = this.typeArray[this.typeIndex] 
      this.chartSetting.type = this.theName
      if (this.theName === 'map') {
        this.chartType = 'map'
        let arr = []
        arr.push(this.columns[1])
        this.chartSetting = {
          position: 'china',
          dimension: this.columns[0],
          metrics: arr
        }
      } else if (this.theName === 'rose') {
        this.chartSetting.type = 'ring'
        this.chartSetting.roseType = 'radius'
      } else if (this.theName === 'lineStack') {
        this.chartSetting.type = 'line'
        this.chartSetting.stack = {'总面积': this.getStack(this.columns)}
        this.chartSetting.area = true
      } else if (this.theName === 'histogramStack') {
        this.chartSetting.type = 'histogram'
        this.chartSetting.stack = {'总面积': this.getStack(this.columns)}
      } else if (this.theName === 'heatmap') {
        this.chartType = 'heatmap'
        this.chartSetting = {
          position: 'china',
          type: 'map',
          geo: {
            label: {
              emphasis: {
                show: false
              }
            },
            itemStyle: {
              normal: {
                areaColor: '#323c48',
                borderColor: '#111'
              },
              emphasis: {
                areaColor: '#2a333d'
              }
            }
          }
        }
      } else if (this.theName === 'wordcloud') {
        this.chartType = 'wordcloud'
        this.chartWordData = {
          columns: ['省份', '面积'],
          rows: this.getWordCloudData()
        }
        this.chartSetting = {
          shape: 'diamond'
        }
      } else if (this.theName === 'gauge') {
        this.chartType = 'gauge'
        this.chartSetting = {
          dimension: '省份',
          metrics: '绿化面积',
          seriesMap: {
            '省份': {
              min: 0,
              max: 100
            }
          }
        }
      } else if (this.theName === 'scatter') {
        this.chartType = 'scatter'
      }
    },
    handleZoom: function() {
      if (this.buttonText === '放大') {
        this.buttonText = '缩小'
        this.$emit('zoomEvent','100%')
      } else {
        this.buttonText = '放大'
        this.$emit('zoomEvent','60%')
      }
      // this.$refs['chartNode'].echarts.resize()
      // 点击放大缩小按钮后手动触发重绘
      let e = document.createEvent('Event')
      e.initEvent('resize', true, true)
      window.dispatchEvent(e)
    },
    getStack: function(arr) {
      return [arr[1], arr[2], arr[3]]
    },
    getWordCloudData: function() {
      let ret = []
      let col = this.chartData.columns
      this.chartData.rows.forEach(e => {
        ret.push({'省份':e[col[0]], '面积':parseInt(e[col[1]])+parseInt(e[col[2]])+parseInt(e[col[3]])})
      })
      return ret
    }
  }
}
</script>

<style>
#graph {
  height: 500px;
  width: 100%;
}
</style>


