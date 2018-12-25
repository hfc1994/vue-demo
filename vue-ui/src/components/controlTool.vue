<template>
  <div id="gridContainer" :style="{ width: width + 'px', height: height + 'px'}">
    <div></div>
    <div class="controlTool arrow-up" @click="controlEvent('up')"></div>
    <div></div>
    <div class="controlTool arrow-left" @click="controlEvent('left')"></div>
    <div>
      <div class="controlTool magnify" @click="controlEvent('magnify')"></div>
      <div class="controlTool shrink" @click="controlEvent('shrink')"></div>
    </div>
    <div class="controlTool arrow-right" @click="controlEvent('right')"></div>
    <div></div>
    <div class="controlTool arrow-down" @click="controlEvent('down')"></div>
    <div></div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'controlTool',
  props: {
    initSize: {
      type: Number,
      default: 180
    }
  },
  data() {
    return {
      height: this.initSize,
      width: this.initSize
    }
  },
  methods: {
    controlEvent(type) {
      this.$emit('doControl', type)
    }
  }
}
</script>

<style>
#gridContainer {
  position: absolute;
  right: 2%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  z-index: 5;
}

.controlTool {
  background-repeat: no-repeat;
  background-size: 100%;
}

.controlTool:hover {
  transform: scale(0.9)
}

.controlTool:active {
  transform: scale(0.85)
}

.arrow-up {
  background-image: url("../assets/svg/arrow-up.svg");
}

.arrow-right {
  background-image: url("../assets/svg/arrow-right.svg");
}

.arrow-down {
  background-image: url("../assets/svg/arrow-down.svg");
}

.arrow-left {
  background-image: url("../assets/svg/arrow-left.svg");
}

.magnify {
  background: url("../assets/svg/magnify.svg") no-repeat;
  background-size: 100%;
  float: left;
  height: 100%;
  width: 50%;
}

.shrink {
  background: url("../assets/svg/shrink.svg") no-repeat;
  background-size: 100%;
  float: right;
  height: 100%;
  width: 50%;
}

</style>

