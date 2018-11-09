<template>
  <div id="baiduGraph">
    <span>图表名称：{{chartType}}</span>
    <el-button size="mini" @click="changeChartType">切换图表类型</el-button>
    <el-button size="mini" @click="handleZoom">{{buttonText}}</el-button>
    <charts :options="getPolarData()" :auto-resize="true"></charts>
  </div>
</template>

<script>
/* eslint-disable*/
import charts from 'vue-echarts'
import {api} from './fetchData.js'

export default {
  name: 'vueECharts',
  components: {charts},
  data () {
    return {
      buttonText: '放大',
      typeIndex: 0,
      chartData: {},
      chartType: '',
      columns: [],  // 数据的字段名
      rows: [], // 数据字段名+字段值
      typeArray: [
        'histogram',  // bar
        'line',
        'pie',  
        'rose', // pie
        'map',
        'lineStack',  // 堆叠区域图line
        'heatmap',
        'treemap',  // 矩形树图
        'wordcloud',
        'radar',
        'gauge',  // 仪表盘
        'funnel', // 漏斗图
        'scatter',  // 散点图
        'bar',
        'histogramStack', // 堆叠柱状图
        'ring', // 环图
        'graph', // 关系图
        'waterfall' // 瀑布图
        ],
    }
  },
  mounted: function () {
    api.getMockData().then(res => {
      this.columns = res.data.key.split(',')
      this.rows = res.data.value

      this.chartType = this.typeArray[this.typeIndex]

    }).catch(err => {
      console.error(err)
    })
  },
  methods: {
    handleZoom: function() {
      if (this.buttonText === '放大') {
        this.buttonText = '缩小'
        this.$emit('zoomEvent','100%')
      } else {
        this.buttonText = '放大'
        this.$emit('zoomEvent','60%')
      }
      // 百度的这个库已经实现了各种重绘
      // this.$refs['chartNode'].echarts.resize()
      // 点击放大缩小按钮后手动触发重绘
      // let e = document.createEvent('Event')
      // e.initEvent('resize', true, true)
      // window.dispatchEvent(e)
    },
    getPolarData () {
      let data = []

      for (let i=0; i<=360; i++) {
        let t = i/180 * Math.PI
        let r = Math.sin(2 * t) * Math.cos(2 * t)
        data.push([r,i])
      }

      return {
        title: {
          text: '极坐标双数数值轴'
        },
        legend: {
          data: ['line']
        },
        polar: {
          center: ['50%', '54%']
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        angleAxis: {
          type: 'value',
          startAngle: 0
        },
        radiusAxis: {
          min: 0
        },
        series: [
          {
            coordinateSystem: 'polar',
            name: 'line',
            type: 'line',
            showSymbol: false,
            data: data
          }
        ],
        animationDuration: 2000
      }
    },
    getChartTitle () {
      // 图表标题
      return {
        left: 'center',
        text: '图表切换测试',
        top: 20,
        textStyle: {
          color: '#ccc'
        }
      }
    },
    getXAxis (columns) {
      return {
        type: 'category',
        axisLabel: {
          inside: false,
          textStyle: {
            color: '#000'
          }
        },
        axisTick: {
          show: true,
          alignWithLabel: true
        },
        z: 10
      }
    }
  }
}
</script>

<style>
#baiduGraph {
  height: 400px;
  width: 100%;
}

.echarts {
  width: 100%;
  min-width: 400px;
  height: 100%;
}
</style>
