<template>
  <div id="app">
    <header-component v-show="showNav"></header-component>
    <div id="v-content" v-bind:style="{minHeight: Height+'px'}"><router-view /></div>
    <footer-component v-show="showNav"></footer-component>
  </div>
</template>

<script>
  import headerComponent from './components/common/MeheaderComponent'
  import navComponent from './components/common/navComponent'
  import footerComponent from './components/common/footerComponent'

  export default {
    name: 'App',
    components:{
      headerComponent,
      navComponent,
      footerComponent

    },
    data(){
      return {
        showNav : false,
        Height: 0
      }
    },
    mounted(){
      this.$router.push('/vsys');
      //动态设置内容高度 让footer始终居底   header+footer的高度是100
      this.Height = document.documentElement.clientHeight - 100;
      //监听浏览器窗口变化　
      window.onresize = ()=> {this.Height = document.documentElement.clientHeight -100}
    }



  }

</script>

<style>
  #app {
    width: 100%;
    min-width: 1120px;
    height: 100%;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    background-color: white;
    overflow: auto;
  }

</style>
