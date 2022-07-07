<template>
  <div id="app">
    <top />
    <index />
  </div>
</template>

<script>
import top from './components/common/01.top.vue';
import index from './components/common/02.index.vue';
export default {
  name: 'app',
  components: {
    top,
    index
  }
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
