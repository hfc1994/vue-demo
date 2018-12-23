<template>
  <div class="testCharts">
    <charts :options="optionData" :auto-resize="true"></charts>
  </div>
</template>

<script>
/* eslint-disable*/
import charts from 'vue-echarts/components/ECharts'
import 'echarts/map/js/china'
import cities from 'echarts/map/json/china-cities'
import china from 'echarts/map/json/china'
import {dataOfNameKey, dataOfCodeKey, metaData} from '../assets/coord.js'

export default {
  name: 'testCharts',
  components: {charts},
  props: ['index'],
  data () {
    return {
      optionData: {},
      geo: {}, // 地理数据
      nameMockData: [],
      codeMockData: []
    }
  },
  created: function (){},
  mounted: function () {
    this.initGeo()

    let option = {
      title: {
        left: 'center',
        text: '地图',
        top: 20,
        textStyle: {
          color: '#ccc'
        }
      },
      tooltip: {
        trigger: 'item',
        label: {
          show: false
        }
      },
      series: [{
        name: 'demo',
        type: 'map',
        mapType: 'china',
        selectedMode: 'single',
        roam: true,
        itemStyle: {
          normal: {
            areaColor: '#e8e8e8',
            borderColor: '#00effc',
            borderWidth: 1,
            label: {
              show: false,
              color: '#fff',
            }
          },
          emphasis: {
            label: {
              show:false
            }
          }
        }
      }]
    }

    this.optionData = option
  },
  methods: {
    initGeo () {
      cities.features.forEach(city => {
        this.geo[city.properties.name] = city.properties.cp
      })
      china.features.forEach(province => {
        this.geo[province.properties.name] = province.properties.cp
      })
    },
        
  }
}
</script>

<style>
.testCharts {
  height: 100%;
  width: 600px;
  margin-top: 20px;
}

.echarts {
  width: 900px !important;
  /* min-width: 600px; */
  height: 600px !important;
}
</style>
