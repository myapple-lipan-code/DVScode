<template>
  <div class="footerComponent">
    <div class="selfResume">
      <ul>
        <li><span>技术支持：</span><span v-text="selfResume.support"></span></li>
        <li><span>服务邮箱：</span><span v-text="selfResume.email"></span></li>
        <li><span>公司网址：</span><span v-text="selfResume.link"></span></li>
<!--        <li><span v-text="selfResume.bans"></span></li>-->
      </ul>
    </div>
    <div class="blogLink">

    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        selfResume: {
          support: '0755 - 26649500',
          email: 'support@callray.com.cn',
          link: 'www.callray.com.cn',
          bans:"深圳市友邻通讯设备有限公司版权所有(C) 1997-2017"
        }
      }
    }
  }
</script>
<style scoped>
  .footerComponent{

    position: relative;
    width: 100%;
    height: 60px;
    border: 1px solid #ddd;
    background-color: #273747;
    box-sizing: border-box;

  }
  .footerComponent .selfResume{
    position: absolute;
    bottom: 10px;
    left: 50%;
    margin-left: -320px;
    width: 620px;
    height: 30px;
  }
  .footerComponent .selfResume ul{
    width: 100%;
    height: 100%;
  }
  .footerComponent .selfResume ul li{
    float: left;
    width: 200px;
    height: 30px;
    line-height: 30px;
    font-size: .75em;
    color: white;
    text-align: center;
  }
  .footerComponent .selfResume ul li span:nth-child(1){
    display: inline-block;
    width: 60px;
    text-align: right;
  }

  .footerComponent .blogLink{
    position: absolute;
    right: 10%;
    width: 160px;
    height: 100px;
  }
</style>
