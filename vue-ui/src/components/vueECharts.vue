<template>
  <div id="baiduGraph">
    <span>图表名称：{{chartType}}</span>
    <el-button size="mini" @click="changeChartType(false,'sub')">上一个</el-button>
    <el-button size="mini" @click="changeChartType(false,'add')">下一个</el-button>
    <el-button size="mini" @click="handleZoom">{{buttonText}}</el-button>
    <charts :options="optionData" :auto-resize="true"></charts>
  </div>
</template>

<script>
/* eslint-disable*/
import charts from 'vue-echarts'
import {api} from './fetchData.js'
const cityGeo = require('../assets/citygeo.js');
import cityGeo2 from '../assets/china.json'

charts.registerMap('china', cityGeo2)

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
      optionData: {},
      dataset: [],
      columnsLength: 0,
      typeArray: [
        'histogram',  // bar
        'line',
        'pie',  
        'rose', // pie
        'map',
        'heatmap',
        'lineStack',  // 堆叠区域图line
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
    api.getMockDataset().then(res => {
      this.dataset = res.data

      this.changeChartType(true)
    }).catch(err => {
      console.error(err)
    })
  },
  methods: {
    changeChartType (fromMounted, operator) {
      if (!fromMounted) {
        switch (operator) {
          case 'add':
            this.typeIndex ++
            if (this.typeIndex === this.typeArray.length) {
              this.typeIndex = 0
            }
            break
          case 'sub':
            this.typeIndex --
            if (this.typeIndex === -1) {
              this.typeIndex = this.typeArray.length - 1
            }
            break
        }
      }

      this.chartType = this.typeArray[this.typeIndex]
      let option = {}
      option.dataset = this.getDataset()
      option.color = this.getGlobalColor()
      option.legend = this.getLegend()
      if (this.chartType === 'histogram') {
        option.title = this.getTitle('柱状图')
        option.tooltip = this.getToolTip('axis')
        option.xAxis = this.getXAxis()
        option.yAxis = this.getYAxis()
        option.dataZoom = this.getDataZoom(false, false, false, false)
        option.series = this.getBarseries()
      } else if (this.chartType === 'line') {
        option.title = this.getTitle('折线图')
        option.tooltip = this.getToolTip('axis')
        option.xAxis = this.getXAxis()
        option.yAxis = this.getYAxis()
        option.series = this.getLineseries()
      } else if (this.chartType === 'pie' || this.chartType === 'rose') {
        option.title = this.getTitle('绿化面积饼图')
        option.tooltip = this.getToolTip('item')
        option.series = this.getPieseries(this.chartType)
      } else if (this.chartType === 'map') {
        option.title = this.getTitle('地图')
        option.tooltip = this.getToolTip('item')
        option.series = this.getMapSeries()
      } else if (this.chartType === 'heatmap') {
        option.title = this.getTitle('热力图')
        option.tooltip = this.getToolTip('item')
        option.legend = this.getHeatmapLegend()
        option.visualMap = this.getVisualMap()
        option.geo = this.getHeatmapGeo()
        option.series = this.getHeatmapSeries()
      }

      this.optionData = option
    },
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
    getTitle (val) {
      // 图表标题
      return {
        left: 'center',
        text: val,
        top: 20,
        textStyle: {
          color: '#ccc'
        }
      }
    },
    getLegend () {
      let legendCol = []
      for(let i=1; i<this.dataset.length; i++) {
        legendCol.push((this.dataset[i])[0])
      }
      return {
        data: legendCol
      }
    },
    getToolTip (val) {
      // 无类目轴的建议使用item，有类目轴的建议用axis
      return {
        trigger: val
      }
    },
    getDataset () {
      return {
        source: this.dataset
      }
    },
    getGlobalColor () {
      // 设置全局调色盘
      return ['#19d4ae', '#5ab1ef', '#fa6e86', '#ffb980', 
      '#0067a6', '#c4b4e4', '#d87a80', '#9cbbff', '#d9d0c7', 
      '#87a997', '#d49ea2', '#5b4947', '#7ba3a8']
    },
    getXAxis () {
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
    },
    getYAxis () {
      return {
        type: 'value',
        axisLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        axisLabel: {
          textStyle: {
            color: '#999'
          }
        }
      }
    }, 
    getDataZoom (xSlider, ySlider, xInside, yInside) {
      let dataZoom = []
      if (xSlider) {
        dataZoom.push({
          type: 'slider',
          show: true,
          xAxisIndex: [0],
          start: 0,
          end: 30
        })
      }
      if (ySlider) {
        dataZoom.push({
          type: 'slider',
          show: true,
          yAxisIndex: [0],
          start: 0,
          end: 30
        })
      }
      if (xInside) {
        dataZoom.push({
          type: 'inside',
          xAxisIndex: [0],
          start: 0,
          end: 30
        })
      }
      if (yInside) {
        dataZoom.push({
          type: 'inside',
          yAxisIndex: [0],
          start: 0,
          end: 30
        })
      }
      return dataZoom
    },
    getBarseries () {
      let series = []
      let colLen = this.dataset[0].length -1

      for (let i=0; i< colLen; i++) {
        series.push({type: 'bar'})
      }
      return series
    },
    getLineseries () {
      let series = []
      let colLen = this.dataset[0].length -1

      for (let i=0; i< colLen; i++) {
        series.push({type: 'line'})
      }
      return series
    },
    getPieseries (isRose) {
      let pieData = []
      for(let i=1; i<this.dataset.length; i++) {
        pieData.push({value:(this.dataset[i])[1],name:(this.dataset[i])[0]})
      }

      let ret = {
          type: 'pie',
          radius: '75%',
          data: pieData
        }
      if (isRose === 'rose') {
        ret.radius = ['20%', '75%']
        ret.roseType = 'area'
      }
      return ret
    },
    getMapSeries () {
      let mapData = []
      for(let i=1; i<this.dataset.length; i++) {
        mapData.push({value:(this.dataset[i])[1],name:(this.dataset[i])[0]})
      }

      return {
        name: '不知道干嘛用的',
        type: 'map',
        mapType: 'china',
        selectedMode: 'single',
        data: mapData
      }
    },
    getHeatmapSeries () {
      let mapData = []

      for (let i=1; i<this.dataset.length; i++) {
        let data = this.dataset[i]
        let axisData = cityGeo.default.cityGeo[this.dataset[i][0]][0]
        mapData.push({name: this.dataset[i][0], value: axisData.concat(this.dataset[i][1])})
      }

      return {
        name: '不知道干嘛用的',
        type: 'heatmap',
        coordinateSystem: 'geo',
        data: mapData,
        label: {
          normal: {
            formatter: '{b}',
            posotion: 'right',
            show: true
          },
          emphasis: {
            show: true
          }
        },
        itemStyle: {
          normal: {
            color: '#fff'
          }
        }
      }
    },
    getHeatmapGeo () {
      return {
        map: "china",
        roam: true,
        label: {
          emphasis: {
            show: true,
            color: '#fff'
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
    },
    getVisualMap () {
      return {
        show: true,
        min: 0,
        max: 100,
        calculable: true,
        inRange: {
          color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
        },
        textStyle: {
          color: '#fff'
        }
      }
    },
    getHeatmapLegend () {
      return {
        orient: 'vertical',
        y: 'bottom',
        x: 'right',
        data: ['heatmap---'],
        textStyle: {
          color: '#fff'
        }
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
