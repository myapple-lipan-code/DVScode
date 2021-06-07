<template>


  <div id="channelApp">
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <el-form :model="appConfigForm" ref="dengmiQueryForm" label-width="150px" class="item" >
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item :label="$t('m.appconfigMsgCenterIP')">
            <el-input v-model="appConfigForm.msgCenterIP"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item :label="$t('m.appconfigIscallrayplay')" align="left">
            <el-radio v-model="appConfigForm.Iscallrayplay" label="0">{{$t('m.appconfigPluginPlay')}}</el-radio>
            <el-radio v-model="appConfigForm.Iscallrayplay" label="999">{{$t('m.appconfigHttpPlay')}}</el-radio>
          </el-form-item>
        </el-col>

      </el-row >
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item  :label="$t('m.appconfigMsgCenterPort')">
            <el-input v-model="appConfigForm.msgCenterPort"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item  :label="$t('m.appconfigHttpServerIp')">
          <el-input v-model="appConfigForm.httpServerIp"></el-input>
        </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item :label="$t('m.appconfigDataCenterIP')">
            <el-input v-model="appConfigForm.dataCenterIP"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item :label="$t('m.appconfigHttpServerPort')">
            <el-input v-model="appConfigForm.httpServerPort"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item label="DataCenterPort"  :label="$t('m.appconfigDataCenterPort')">
            <el-input v-model="appConfigForm.dataCenterPort"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="localPath"  :label="$t('m.appconfigLocalPath')">
            <el-input v-model="appConfigForm.localPath"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
        <el-row type="flex" justify="center" align="middle">
          <el-col :span="10">
            <el-form-item :label="$t('m.appconfigRatio')" >
              <el-select v-model="appConfigForm.ratio" placeholder="请选择" style=" width: 100%">
                <el-option
                  v-for="(item,index)  in ratioTypes"
                  :key="index"
                  :label="item"
                  :value="item">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item :label="$t('m.appconfigDownFileType')">
              <el-input v-model="appConfigForm.downFileType"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item  :label="$t('m.appconfigListenPort')" >
            <el-input v-model="appConfigForm.listenPort"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="Voice file format" style="display: none">
            <el-input ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item  :label="$t('m.appconfigAlarmLang')" >
            <el-select v-model="appConfigForm.alarmLang" placeholder="请选择" style=" width: 100%">
              <el-option
                v-for="(item,index)  in AlarmLangs"
                :key="index"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="Voice file format" style="display: none">
            <el-input ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item  :label="$t('m.appconfigMaxSelectNumber')" >
            <el-input v-model="appConfigForm.maxSelectNumber"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="Voice file format" style="display: none">
            <el-input ></el-input>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col>
          <el-button type="primary" @click="submitForm" icon="el-icon-search">保存</el-button>
          <el-button type="warning" @click="returnForm" icon="el-icon-search" plain>返回</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>

  import BreadcrumbComponent from '../../components/common/BreadcrumbComponent'
  import { saveAppConfig } from "@/api/index";
  export default {
    name: "channelDetail",
    components:{
      BreadcrumbComponent
    },
    data() {
      return {
        appConfigForm: {
          Iscallrayplay: "0",
          alarmLang: "zh_CN",
          dataCenterIP: "192.168.0.172",
          dataCenterPort: 9502,
          downFileType: "{1}-{7}{8}{9}-{3}-{4}-{5}",
          httpServerIp: "192.168.0.172",
          httpServerPort: "9503",
          listenPort: "10006",
          localPath: "",
          maxSelectNumber: "500",
          msgCenterIP: "192.168.0.172",
          msgCenterPort: 10005,
          ratio: "voip",
        },
        AlarmLangs:['zh_CN','en_US']
      };

    },
    methods: {
      submitForm() {
        let  that = this;
        saveAppConfig(this.appConfigForm).then((resp) => {
          let code = resp.data.code;
          if (code === 200) {
            //this.updateCurrentlang();
            that.$message.success(resp.data.message);
            this.reload();
          } else {
            that.$message.error(resp.data.message);
          }
        });
      },
      returnForm() {
        var path = this.$route.query.redirect;
        this.$router.replace({ path: path === '/channel' || path === undefined ? '/channel' : path })
      },

      //进行语言的奇幻
      updateCurrentlang() {
        let self_current_lang = this.appConfigForm.alarmLang
        if (self_current_lang) {
          // 切换系统语言
          this.$i18n.locale = self_current_lang
          // 将新语言保存到全局状态管理
          this.$store.dispatch('update_current_lang', self_current_lang)

          // 成功提示
          //this.$message.success('系统语言切换成功！')
        } else {
          //this.$message.warning('没有你想要的语言吗，选一个吧！')
        }
      },
    },
    created() {
      this.appConfigForm = this.appConfigs;
      if(this.appConfigs == null ){
        this.appConfigForm = {
          Iscallrayplay: "0",
          alarmLang: "zh_CN",
          dataCenterIP: "127.0.0.1",
          dataCenterPort: 9502,
          downFileType: "{1}-{7}{8}{9}-{3}-{4}-{5}",
          httpServerIp: "127.0.0.1",
          httpServerPort: "9503",
          listenPort: "10006",
          localPath: "",
          maxSelectNumber: "500",
          msgCenterIP: "127.0.0.1",
          msgCenterPort: 10005,
          ratio: "voip",
        }
      }
    },
    computed:{
      ratioTypes(){
        let domainList = this.$store.state.appConfigList;
        return domainList.ratioTypes;
      },
      appConfigs(){
        let domainList = this.$store.state.appConfigList;
        return domainList.appConfig;
      },
      appData:function () {
        return {
          title:this.$t('m.appconfigTitle')
        }
    },
    mounted() {
      this.$store.dispatch('getAllAppConfig'); // 直接调用 store/index.JS 中的方法
    },


    }
  }
</script>

<style scoped>
  .panel-heading {
    padding: 10px 15px;
    text-align: left;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    /*color: #31708f;
    background-color: #d9edf7;
    border-color: #bce8f1;*/
  }
  #channelApp{
    background-color: white;
  }


</style>
