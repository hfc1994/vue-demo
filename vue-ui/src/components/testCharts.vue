<template>
  <div class="testCharts">
    <charts :options="optionData" :auto-resize="true"></charts>
  </div>
</template>

<script>
/* eslint-disable*/
import charts from "vue-echarts/components/ECharts"
import "echarts/map/js/china"
import cities from "echarts/map/json/china-cities"
import china from "echarts/map/json/china"
import { dataOfNameKey, dataOfCodeKey, metaData, star} from "../assets/coord.js"

export default {
  name: "testCharts",
  components: { charts },
  props: ["index"],
  data() {
    return {
      optionData: {},
      geo: {}, // 地理数据
      mockData: [],
      targetSite: '',
      paramName1: '',
      paramName2: ''
    }
  },
  created: function() {},
  mounted: function() {
    const data = this.generateBaseMockData()
    this.paramName1 = data[0][4]
    this.paramName2 = data[0][5]
    const ret = this.buildDataFromFormat1(data)
    this.generateChart(ret)
  },
  methods: {
    initGeo() {
      cities.features.forEach(city => {
        this.geo[city.properties.name] = city.properties.cp
      })
      china.features.forEach(province => {
        this.geo[province.properties.name] = province.properties.cp
      })
    },
    generateChart(srcData) {
      const that = this
      let option = {
        title: {
          left: "center",
          text: "地图",
          top: 20,
          textStyle: {
            color: "#ccc"
          }
        },
        tooltip: {
          trigger: "item",
          formatter: function(val) {
            let content = val.name + " / " + val.value[2] + "<br/>" 
              + that.paramName1 + ':' + val.value[3]

            if (val.value[4] !== undefined) {
              content = content + "<br/>" + that.paramName2 + ':'  + val.value[4]
            }
            return content
          },
          extraCssText: "text-align: left"
        },
        geo: {
          show: true,
          map: "china",
          label: {
            normal: {
              show: false
            },
            emphasis: {
              show: false
            }
          },
          roam: true,
          selectedMode: "single",
          itemStyle: {
            areaColor: "#f3f3f3",
            borderColor: "#00effc",
            borderWidth: 1,
            label: {
              show: false
            }
          },
          emphasis: {
            label: {
              show: false
            },
            itemStyle: {
              areaColor: "#f3f3f3"
            }
          }
        },
        series: [{
          name: '区域分布',
          type: 'scatter',
          coordinateSystem: 'geo',
          data: this.generateSeriesData(srcData),
          itemStyle: {
            normal: {
              color: '#05C3F9'
            }
          }
        }, {
          name: '北京',
          type: 'scatter',
          coordinateSystem: 'geo',
          data: [{
            name: '北京关区',
            value: [116.45510, 40.25390, '0100'],
            symbol: star,
            symbolSize: 20
          }],
          silent: true,
          label: {
            show: false
          },
          itemStyle: {
            color: '#ff0000'
          }
        }]
      }
      console.log(option)
      this.optionData = option
    },
    generateSeriesData(srcData) {
      let ret = []
      srcData.forEach(data => {
        let obj = {
          name: data[0],
          // 依次是经度 纬度 区号 数值1
          value: [
            data[2],
            data[3],
            data[1],
            data[4]
          ],
          symbol: 'pin',
          symbolSize: 36,
          label: {
            show: true,
            formatter: '{b}',
            position: ['100%','100%'],
            textStyle: {
              color: '#000'
            }
          }
        }
        ret.push(obj)
        // 即有数值2
        if (data.length === 6) {
          obj.value.push(data[5])
          let obj2 = {
            name: data[0],
            value: [
              data[2],
              data[3],
              data[1],
              data[4],
              data[5]
            ],
            symbol: 'pin',
            symbolSize: 36,
            label: {
              show: true,
              formatter: function(val) {
                return val.value[4]
              },
              textStyle: {
                color: '#fff'
              }
            }
          }
          ret.push(obj2)

          if(data[0].indexOf(this.targetSite) !== -1) {
            obj2.itemStyle = {
              normal: {
                color: '#ff0000'
              }
            }
          }
        }

        if(data[0].indexOf(this.targetSite) !== -1) {
          obj.itemStyle = {
              normal: {
                color: '#ff0000'
              }
            }
        }
      })
      return ret
    },
    testMockData() {
      let format1
      let format2
      let format3
      let total = []
      metaData.forEach(data => {
        let count1 = Math.floor(Math.random() * 70 + 1) + 30
        let count2 = Math.floor(Math.random() * 70 + 1) + 30
        format1 = [data[0], count1, count2]
        format2 = [data[1], count1, count2]
        format3 = data.slice(0, 2).concat([count1, count2])
        total = total.concat([
          [this.buildDataFromFormat1(format1), this.buildDataFromFormat2(format2), this.buildDataFromFormat3(format3)]
        ])
      })
      console.log(total)
    },
    buildMockData () {
      let ret = []
      ret.push(['关区名', '关区编码', '经度', '纬度', '数值1', '数值2'])
      metaData.forEach(data => {
        let position = dataOfNameKey[data[0]]
        ret.push([
          data[0],
          data[1],
          position[0][0],
          position[0][1],
          Math.floor(Math.random() * 40 + 1) + 60,
          Math.floor(Math.random() * 40 + 1) + 60
        ])
      })

      return ret
    },
    generateBaseMockData () {
      let dst = []
      dst.push(['关区名', '关区编码', '经度', '纬度', '数值1', '数值2'])
      metaData.forEach(data => {
        if (Math.floor(Math.random() * 10 + 1) > 7) {
          let ret = []
          ret = [
            data[0],
            Math.floor(Math.random() * 40 + 1) + 60,
            Math.floor(Math.random() * 40 + 1) + 60
          ]

          dst.push(ret)
          this.targetSite = data[0]
        } else {
          return
        }
      })

      return dst
    },
    buildDataFromFormat1(srcData) {
      // 假设输入的是['关区名','数值1'...]
      // 输出['关区名','关区编码','经度','纬度','数值1'...]
      let array = []
      srcData.slice(1, srcData.length).forEach(src => {
        let data = dataOfNameKey[src[0]]
        let ret = [src[0], data[1]].concat(data[0], src.slice(1, src.length))
        array.push(ret)
      })

      return array
    },
    buildDataFromFormat2(srcData) {
      // 假设输入的是['关区编码','数值1'...]
      // 输出['关区名','关区编码','经度','纬度','数值1'...]
      let array = []
      srcData.slice(1, srcData.length).forEach(src => {
        let data = dataOfCodeKey[src[0]]
        let ret = [data[1], src[0]].concat(data[0], src.slice(1, src.length))
        array.push(ret)
      })

      return array
    },
    buildDataFromFormat3(srcData) {
      // 假设输入的是['关区名','关区编码','数值1'...]
      // 输出['关区名','关区编码','经度','纬度','数值1'...]
      let array = []
      srcData.slice(1, srcData.length).forEach(src => {
        let data = dataOfNameKey[src[0]]
        let ret = [src[0], src[1]].concat(data[0], src.slice(2, src.length))
        array.push(ret)
      })
      
      return array
    }
  }
}
</script>

<style>
.testCharts {
  height: 600px;
  width: 100%;
  margin-top: 20px;
}

.echarts {
  width: 100% !important;
  min-width: 400px !important;
  height: 100% !important;
}
</style>
