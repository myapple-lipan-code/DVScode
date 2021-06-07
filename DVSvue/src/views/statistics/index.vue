<template>
  <div id="vsys">
  	<h1>录音统计</h1>
  </div>
</template>

<script>
  export default {
    name: 'vsys',
    created () {
      this.$parent.showNav = true
    }
  }
</script>

<style scoped>
#vsys{
	margin: 0;
	padding: 0;
	background: lightblue;
	height: 600px;
	width: 100%;
}
</style>
