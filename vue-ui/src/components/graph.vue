<template>
  <div id="graph" ref="echartsNode" v-loading="loading" element-loading-text="加载中"
        element-loading-spinner="el-icon-loading" element-loading-background="rgba(255, 255, 255, 0.675)">
  </div>
</template>

<script>
/* eslint-disable */
import echarts from '../../node_modules/echarts'
import {api} from './fetchData.js'

export default {
  name: 'graph',
  data () {
    return {
      msg: 'graph页面',
      tabType: '',
      type: '',
      name: '',
      loading: false
    }
  },
  created () {
    this.tabType = this.$route.params.tabType //one,two and so on
    this.type = this.$route.params.type       //film,music,book and so on
    this.name = this.$route.params.name       //douban,maoyan and so on
  },
  mounted: function () {
    this.loading = true
    if (this.name === 'douban') {
      this.handleDouBanData(this.tabType, this.type)
    } else {
      this.handleOtherData(this.name, this.type)
    }
    //后续api请求是异步的，如果在这里改为false，会导致loading效果时间很短，看不到
//    this.loading = false
  },
  watch: {
    $route (to, from) {
    }
  },
  methods: {
    handleDouBanData: function (tabType, type) {
      let dom = document.getElementById('graph')
      let myChart = echarts.init(dom)
      if (type === 'film') {
        if (tabType === 'one') {
          //按年份,为了做折线图，数量阈值为0
          api.getFilmByYear(0).then(response => {
            let option = this.produceYearOption(response.data)
            myChart.setOption(option)
            this.echartsResizeListener(myChart)
            this.loading = false
          })
        } else if (tabType === 'two') {
          //按评分，需要做饼状图
          api.getFilmByStar().then(response => {
            let option = this.produceStarOption(response.data)
            myChart.setOption(option)
            this.echartsResizeListener(myChart)
            this.loading = false
          })
        } else if (tabType === 'three') {
          //按类型
          api.getFilmByType().then(response => {
            let option = this.produceTypeOption(response.data)
            myChart.setOption(option)
            this.echartsResizeListener(myChart)
            this.loading = false
          })
        } else {
          this.handleOtherData('douban', type)
        }
      } else if (type === 'book') {
        if (tabType === 'four') {
          //按关键字
          api.getBookByPublishing('计算机').then(response => {
            let option = this.produceTagOption(response.data)
            myChart.setOption(option)
            this.echartsResizeListener(myChart)
            this.loading = false
          })
        }
        else {
          this.handleOtherData('douban', type)
        }
      } else {
        this.handleOtherData('douban', type)
      }
    },
    handleOtherData: function (name, type) {
      //除了豆瓣的数据，其它暂时没有，暂时不处理
      let dom = document.getElementById('graph')
      dom.innerHTML='<span>当前暂无生成图表的数据</span>'

      this.loading = false
    },
    produceYearOption: function (data) {
      // 折线图
      let year = [];
      let total = [];
      for (let obj of data)
      {
        year.push(obj.year)
        total.push(obj.total)
      }

      let option = {
        xAxis: {
          type: 'category',
          data: year
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: total,
          type: 'line',
          name: '电影数量',
          sampling: 'average',
          itemStyle: {
            normal: {
              color: 'rgb(255, 70, 131)'
            }
          }
        }],
        title: {
          left: 'center',
          text: '历年出品电影数量（数据不全）'
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            restore: {},
            saveAsImage: {}
          }
        },
        tooltip: {
          trigger: 'axis',
          positon: function (pt) {
            return [pt[0], '10%']
          }
        }
      //  dataZoom: [{
      //    type: 'inside',
      //    start: 0,
      //    end: 10
      //  },{
      //    start: 0,
      //    end: 10,
      //  }]
      }

      return option
    },
    produceStarOption: function (data) {
      // 饼图
      let length = data.length
      let pieData = []

      for (let i=0; i<length; i++)
      {
        let str = '[' + data[i].score + '-' + (Number(data[i].score)+1) + ')分数段'
        pieData[i] = {}
        pieData[i]['name'] = str
        pieData[i]['value'] = data[i].total
      }

      let option = {
        backgroundColor: '#2c343c',

        title: {
          left: 'center',
          text: '各评分段电影数量（数据不全）',
          top: 20,
          textStyle: {
            color: '#ccc'
          }
        },

        tooltip: {
          trigger: 'item',
          formatter: '{a}<br/>{b} : {c} ({d}%)'
        },

        visualMap: {
          show: false,
          min: 0,
          max: 10000,
          inRange: {
            colorLightness: [0, 0.5]
          },
          color: ['rgb(150, 220, 230)','rgb(70, 170, 230)']
        },

        series: [
          {
            name: '评分段',
            type: 'pie',
            radius: '50%',
            data: pieData,
        //  roseType: 'radius',
            label: {
              normal: {
                textStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                }
              }
            },
            labelLine: {
              normal: {
                lineStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                },
                smooth: 0.2,
                length: 10,
                length2: 20
              }
            },
            itemStyle: {
              normal: {
              //  color: 'red',
                shadowBlur: 20,
                shadowColor: 'rgba(166, 177, 188, 0.3)'
              }
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
              return Math.random() * 200;
            }
          }]
        }
      return option
    },
    produceTypeOption: function (data) {
      // 柱状图
      let type = [];
      let total = [];
      for (let obj of data)
      {
        type.push(obj.type)
        total.push(obj.total)
      }

      let option = {
        title: {
          left: 'center',
          text: '各类型电影数量（数据不全）'
        },
        xAxis: {
          data: type,
          axisLabel: {
            inside: true,
            textStyle: {
                color: '#fff'
            }
          },
          axisTick: {
              show: false
          },
          axisLine: {
              show: false
          },
          z: 10
        },
        yAxis: {
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
        },
        dataZoom: [
          {
            type: 'inside'
          }
        ],
        series: [
          {
            type: 'bar',
            itemStyle: {
              normal: {
                color: 'rgba(0, 0, 0, 0.05)'
              }
            },
            barGap: '-100%',
            barCategoryGap: '40%',
            data: total,
            animation: false
        },{
            type: 'bar',
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    {offset: 0, color: '#83bff6'},
                    {offset: 0.5, color: '#188df0'},
                    {offset: 1, color: '#188df0'}
                  ]
                )
              },
              emphasis: {
                  color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                      {offset: 0, color: '#2378f7'},
                      {offset: 0.7, color: '#2378f7'},
                      {offset: 1, color: '#83bff6'}
                    ]
                  )
                }
              },
              data: total
          }],
      }

      return option
    },
    produceTagOption: function (data) {
      // 雷达图
      let publishing = []
      let total = []

      for (let obj of data)
      {
        let publish = {}
        publish['name'] = obj.publishing
        publish['max'] = 30
        publishing.push(publish)
        total.push(obj.total)
      }

      let option = {
        title: {
            left: 'center',
            text: '各出版社出版的带有‘计算机’关键字图书的数量（数据不全）'
        },
        tooltip: {},
        legend: {
            data: ['图书数量']
        },
        radar: {
        //  shape: 'circle',
          name: {
            textStyle: {
              color: '#fff',
              backgroundColor: '#999',
              borderRadius: 3,
              padding: [3, 5]
            }
          },
          indicator: publishing
        },
        series: [{
          name: '含有‘计算机’字样的图书数量',
          type: 'radar',
          data: [{
            value: total,
            name: data[0]
          }]
        }]
      }

      return option
    },
    echartsResizeListener: function(echarts) {
      // echart重绘
      window.onresize = function() {
        echarts.resize();
      }
    }
  }
}
</script>

<style>
#graph {
  height: 500px;
  width: 100%;
  /*background-color: aqua;*/
}
</style>
