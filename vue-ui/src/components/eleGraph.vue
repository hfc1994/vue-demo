<template>
  <div id="graph">
    <el-button size="mini" @click="changeChartType">切换图表类型</el-button>
    <el-button size="mini" @click="handleZoom">{{buttonText}}</el-button>
    <ve-chart :data="chartData" :settings="chartSetting" ref="chartNode" style="width:100%;" v-if="!isMap"></ve-chart>
    <ve-map :data="chartData" :settings="chartSetting" style="width: 100%;" v-else></ve-map>
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
      typeArray: ['line','bar','histogram','pie','ring','funnel','waterfall','radar','map'],
      typeIndex: 0,
      chartData: {},
      chartSetting: {},
      buttonText: '放大',
      columns: [],
      rows: [],
      isMap: false
    }
  },
  created () {
    api.getMockData().then(res => {
      this.columns = res.data.key.split(',')
      this.rows = res.data.value
      this.chartData.columns = this.columns
      this.chartData.rows = this.rows

      this.chartSetting = {type: this.typeArray[this.typeIndex]}
    }).catch(err => {
      console.log(err)
    })
  },
  methods: {
    changeChartType: function (){
      this.isMap = false
      if (this.typeIndex <= this.typeArray.length - 2) {
        this.typeIndex++
      } else {
        this.typeIndex = 0
      }
      this.chartSetting = {}
      this.chartSetting.type = this.typeArray[this.typeIndex]
      if (this.typeArray[this.typeIndex] === 'map') {
        this.isMap = true
        let arr = []
        arr.push(this.columns[1])
        this.chartSetting = {
          position: 'china',
          dimension: this.columns[0],
          metrics: arr
        }
      }
    },
    handleZoom: function() {
      if (this.buttonText === '放大') {
        this.buttonText = '缩小'
        this.$emit('zoomEvent','100%')
        // this.dialogWidth = '100%'
      } else {
        this.buttonText = '放大'
        // this.dialogWidth = '60%'
        this.$emit('zoomEvent','60%')
      }
      // this.$refs['chartNode'].echarts.resize()
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


