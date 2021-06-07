<template>


  <div id="channelApp">
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <el-form :model="channelForm" ref="dengmiQueryForm" label-width="100px"  >
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item label="开始分机">
            <el-input v-model="channelForm.beginExt"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="结束分机">
            <el-input v-model="channelForm.endExt" v-bind:disabled="endExtDisable"></el-input>
          </el-form-item>
        </el-col>

      </el-row >
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item label="录音客户端IP">
            <el-input v-model="channelForm.computer"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="分机IP">
            <el-input v-model="channelForm.channelIPAddr"></el-input>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item label="分机MAC地址">
            <el-input v-model="channelForm.channelMac"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="CTI程序IP">
            <el-input v-model="channelForm.ctiAppIPAddr"></el-input>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row type="flex" justify="center" align="middle">
      <el-col :span="10">
        <el-form-item label="录音方案">
          <el-select v-model="channelForm.cardType" placeholder="请选择" style=" width: 100%">
            <el-option
              v-for="(item,index)  in cardTypes"
              :key="index"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>

      </el-col>
      <el-col :span="10">
        <el-form-item label="通道类型">
          <el-select v-model="channelForm.channelType" placeholder="请选择" style=" width: 100%">
            <el-option
              v-for="(item,index)  in channelTypes"
              :key="index"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>

    </el-row>
      <el-row type="flex" justify="center" align="middle">
      <el-col :span="10">
        <el-form-item label="虚拟分机">
          <el-input v-model="channelForm.channelVirtualExt"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="10">
        <el-form-item label="包大小">
          <el-input v-model="channelForm.channelPacketSize"></el-input>
        </el-form-item>
      </el-col>

    </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="10">
          <el-form-item label="SBC服务IP">
            <el-input v-model="channelForm.channelSBCServer"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="部门">
            <el-select v-model="extDepartment" placeholder="请选择" style=" width: 100%"
                       multiple @change="getDepartmentIds"
                       :default-first-option=true
                       :reserve-keyword=true>
              <el-option
                v-for="item in departments"
                :key="item.id"
                :label="item.name"
                :value="item.id" >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row >
        <el-col :span="10">
            <el-checkbox v-model="channelForm.enable"   true-label="1" false-label="0">是否启用</el-checkbox>
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
  import { getChannelById,addChannel,updateChannel } from "@/api/channel";
  import BreadcrumbComponent from '../../components/common/BreadcrumbComponent'
  export default {
    name: "channelDetail",
    components:{
      BreadcrumbComponent
    },
    data() {
      return {
        appData:{
          title:"分机配置"
        },
        id:'',
        endExtDisable: false,
        channelForm: {
          beginExt: '',
          endExt: '',
          computer: '',
          channelIPAddr: '',
          channelMac: '',
          ctiAppIPAddr: '',
          cardType: '',
          channelType: '',
          channelPacketSize: '',
          channelVirtualExt: '',
          channelSBCServer: '',
          enable: '0',
          departmentIds: []
        },
        extDepartment:[],
        channelEnable:false
      };

    },
    methods: {
      submitForm() {
        let id  = this.id;
        //获取选中的部门值
        if(this.id  != null && this.id !== ""){
          //进行修改操作
          let params = {
            id:id,
            beginExt: this.channelForm.beginExt,
            endExt: this.channelForm.endExt,
            computer: this.channelForm.computer,
            channelIPAddr: this.channelForm.channelIPAddr,
            channelMac: this.channelForm.channelMac,
            ctiAppIPAddr: this.channelForm.ctiAppIPAddr,
            cardType: this.channelForm.cardType,
            channelType: this.channelForm.channelType,
            channelPacketSize: this.channelForm.channelPacketSize,
            channelVirtualExt: this.channelForm.channelVirtualExt,
            channelSBCServer: this.channelForm.channelSBCServer,
            enable: this.channelEnable,
            departmentIds: this.channelForm.departmentIds
          };
          updateChannel(params).then((resp) => {
            if(resp.data.result){
              this.$message.success('修改分机成功:'+params.beginExt);
              this.returnForm();
            }else{
              this.$message.warning("修改分机失败");
            }
          });

          console.log(params)
        }else{
          let params = {
            beginExt: this.channelForm.beginExt,
            endExt: this.channelForm.endExt,
            computer: this.channelForm.computer,
            channelIPAddr: this.channelForm.channelIPAddr,
            channelMac: this.channelForm.channelMac,
            ctiAppIPAddr: this.channelForm.ctiAppIPAddr,
            cardType: this.channelForm.cardType,
            channelType: this.channelForm.channelType,
            channelPacketSize: this.channelForm.channelPacketSize,
            channelVirtualExt: this.channelForm.channelVirtualExt,
            channelSBCServer: this.channelForm.channelSBCServer,
            enable: this.channelEnable,
            departmentIds: this.channelForm.departmentIds
          };
          addChannel(params).then((resp) => {
            if(resp.data.result){
              this.$message.success('添加账号成功');
              this.returnForm();
            }else{
              this.$message.success('添加账号成功');
            }
          });
        }
      },
      returnForm() {
        var path = this.$route.query.redirect;
        this.$router.replace({ path: path === '/channelIndex' || path === undefined ? '/channelIndex' : path })
      },
      getDepartmentIds(val){
        this.channelForm.departmentIds = val;
        console.log(val)
      }


    },
    created() {
      this.id = this.$route.params.id;
      if(this.id  != null && this.id !== ""){
        let that = this;
        this.endExtDisable = true;
        getChannelById(
           this.id
        ).then((resp) => {
          let code = resp.data.code;
          if (code === 200) {
            //that.channelForm = resp.data.data;
            let channelData = resp.data.data;
            that.channelForm.beginExt = channelData.extension;
            that.channelForm.computer = channelData.computer;
            that.channelForm.channelIPAddr = channelData.channelIPAddr;
            that.channelForm.channelMac = channelData.channelMac;
            that.channelForm.ctiAppIPAddr =channelData.ctiAppIPAddr;
            that.channelForm.cardType = channelData.cardType;
            that.channelForm.channelType =channelData.channelType;
            that.channelForm.channelVirtualExt = channelData.channelVirtualExt;
            that.channelForm.channelPacketSize = channelData.channelPacketSize;
            that.channelForm.channelSBCServer = channelData.channelSBCServer;
            that.channelForm.enable =channelData.enable+"";
            that.channelForm.departmentIds = channelData.departmentIds;
            //that.extDepartment = channelData.departments;
            if(channelData.departments  && channelData.departments.length > 0) {
              for (let i = 0; i < channelData.departments.length; i++) {
                that.extDepartment.push(channelData.departments[i].id);
              }
            }

          } else {
            that.$message.error(resp.data.msg);
          }
        });
      }else{
        this.endExtDisable = false;
        this.extDepartment = [];
        this.channelForm =  {
          beginExt: '',
            endExt: '',
            computer: '',
            channelIPAddr: '',
            channelMac: '',
            ctiAppIPAddr: '',
            cardType: '',
            channelType: '',
            channelPacketSize: '',
            channelVirtualExt: '',
            channelSBCServer: '',
            enable: '0',
            departmentIds: []
        }
      }
    },
    computed:{
      domainList () {
        return this.$store.state.appConfigList; // 获取 state 中的 domainList
      },
      cardTypes(){
        let domainList = this.$store.state.appConfigList;
       return domainList.cardTypes;
      },
      channelTypes(){
        let domainList = this.$store.state.appConfigList;
        return domainList.channelTypes;
      },
      departments(){
        let departments = this.$store.state.departmentList;
        return departments.departments;
      }
    },
    mounted() {
      this.$store.dispatch('getAllAppConfig'); // 直接调用 store/index.JS 中的方法
      this.$store.dispatch('getAllDepartment');
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
