<template>
  <nav>
  	<ul>
  		<li>
  			<router-link to="/" exact>录音查询</router-link>
  			<router-link to="/statistics" exact>录音统计</router-link>
  			<router-link to="/operatelog" exact>操作日志</router-link>
  			<router-link to="/appConfig" exact>系统配置</router-link>
        <router-link to="/channel" exact>分机配置</router-link>
  		</li>
  	</ul>
  </nav>
</template>

<script>
  export default {
    name: 'pageheader',
  }
</script>

<style scoped>
ul{
	list-style-type: none;/*消除黑点*/
	text-align: center;
	margin:0;
}
li{
	display: inline-block;
	margin: 0 10px;
}
a{
	color: #fff;/*设置字体颜色*/
	text-decoration: none;/*消除下划线*/
	padding: 12px;
	border-radius: 5px;
}
nav{
	background: grey;
	padding: 30px 0;
}
/*选中则发生一些变化*/
.router-link-active{
	background: rgba(255,255,255,0.8);
	color: #444;
}
</style>
