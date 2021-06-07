<template>
  <div >
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane :label="$t('m.accountextmgr')" name="accountextmgr"  :key="'accountextmgr'"></el-tab-pane>
      <el-tab-pane :label="$t('m.accountagentmgr')" name="accountagentmgr" :key="'accountagentmgr'"></el-tab-pane>
    </el-tabs>


    <el-col :span="4">
      <el-tree
        :data="treeList"
        show-checkbox
        node-key="id"
        :default-expanded-keys="[0]"
        :default-checked-keys="[]"
        align="center"
        ref="accountTree"
        @check-change="accountTreeSelect">
      </el-tree>

    </el-col>
    <el-col :span="9" v-show="showMgrForm.agentShowUnMgrForm">
      <el-table
        :data="unMgrAgentList"
        border
        ref="unMgrAgent"
        @selection-change="checkUnMgrAgentAll"
        max-height="320"
        min-height="320"
        >
        <el-table-column
          fixed
          type="selection"
          width="55">
        </el-table-column>

        <el-table-column
          prop="name"
          :label="$t('m.accountname')"
          min-width="110" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountalias')"
          min-width="100" >
        </el-table-column>
        <el-table-column
          prop="property"
          :label="$t('m.accounttype')"
          min-width="100"
          :formatter="formatterAccountType" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountindesp')"
          min-width="110"
          :formatter="formatterAccountTindesp">
        </el-table-column>

      </el-table>
    </el-col>
    <el-col :span="2" v-show="showMgrForm.agentShowMgrBtn">
      <div class="opSetting">
        <div  @click="handleAgentBtnRightAll">
          <el-button icon="el-icon-d-arrow-right" :size="buttonSize" type="primary" >
          </el-button>
        </div>
        <div class="spacing" @click="handleAgentBtnRightSelect" >
          <el-button icon="el-icon-arrow-right"  :size="buttonSize" type="primary" >
          </el-button>
        </div>
        <div  class="spacing" @click="handleAgentBtnLeftAll">
          <el-button icon="el-icon-d-arrow-left" :size="buttonSize" type="primary">
          </el-button>
        </div>
        <div class="spacing"  @click="handleAgentLeftSelect">
          <el-button icon="el-icon-arrow-left"  :size="buttonSize" type="primary">
          </el-button>
        </div>
      </div>
    </el-col>

    <el-col :span="9" v-show="showMgrForm.agentShowAlreadyMgrForm">
      <el-table
        :data="mgrAgentList"
        border
        ref="mgrAgent"
        @selection-change="checkMgrAgent"
        max-height="320"
        min-height="320">
        <el-table-column
          fixed
          type="selection"
          width="30">
        </el-table-column>

        <el-table-column
          prop="name"
          :label="$t('m.accountname')"
          min-width="110" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountalias')"
          min-width="100" >
        </el-table-column>
        <el-table-column
          prop="property"
          :label="$t('m.accounttype')"
          min-width="100"
          :formatter="formatterAccountType" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountindesp')"
          min-width="110"
          :formatter="formatterAccountTindesp">
        </el-table-column>


      </el-table>
    </el-col>


    <el-col :span="9" v-show="showMgrForm.extensionShowUnMgrForm">
      <el-table
        :data="unMgrExtensionList"
        border
        ref="unMgrExtension"
        @selection-change="checkUnMgrExtensionAll"
        style="width: 100%;"
        max-height="320"
        min-height="320"
      >
        <el-table-column
          fixed
          type="selection"
          width="55">
        </el-table-column>

        <el-table-column
          prop="name"
          :label="$t('m.accountname')"
          min-width="110" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountalias')"
          min-width="100" >
        </el-table-column>
        <el-table-column
          prop="property"
          :label="$t('m.accounttype')"
          min-width="100"
          :formatter="formatterAccountType" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountindesp')"
          min-width="110"
          :formatter="formatterAccountTindesp">
        </el-table-column>

      </el-table>
    </el-col>
    <el-col :span="2"  v-show="showMgrForm.extensionShowMgrBtn">
      <div class="opSetting">
        <div  @click="handleExtensionBtnRightAll">
          <el-button icon="el-icon-d-arrow-right" :size="buttonSize" type="primary" >
          </el-button>
        </div>
        <div class="spacing" @click="handleExtensionBtnRightSelect" >
          <el-button icon="el-icon-arrow-right"  :size="buttonSize" type="primary" >
          </el-button>
        </div>
        <div  class="spacing" @click="handleExtensionBtnLeftAll">
          <el-button icon="el-icon-d-arrow-left" :size="buttonSize" type="primary">
          </el-button>
        </div>
        <div class="spacing"  @click="handleExtensionLeftSelect">
          <el-button icon="el-icon-arrow-left"  :size="buttonSize" type="primary">
          </el-button>
        </div>
      </div>
    </el-col>

    <el-col :span="9"  v-show="showMgrForm.extensionShowUnMgrForm">
      <el-table
        :data="mgrExtensionList"
        border
        ref="mgrExtension"
        @selection-change="checkMgrExtensionAll"
        style="width: 100%;"
        max-height="320">
        <el-table-column
          fixed
          type="selection"
          width="55">
        </el-table-column>

        <el-table-column
          prop="name"
          :label="$t('m.accountname')"
          min-width="110" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountalias')"
          min-width="100" >
        </el-table-column>
        <el-table-column
          prop="property"
          :label="$t('m.accounttype')"
          min-width="100"
          :formatter="formatterAccountType" >
        </el-table-column>
        <el-table-column
          prop="alias"
          :label="$t('m.accountindesp')"
          min-width="110"
          :formatter="formatterAccountTindesp">
        </el-table-column>
      </el-table>
    </el-col>
    <el-col style="margin-top: 10px">
      <el-button type="primary" icon="el-icon-search" @click="saveMgrAccount">保存</el-button>
      <el-button type="warning"  icon="el-icon-search" plain @click="returnForm">返回</el-button>
    </el-col>
  </div>
</template>

<script>
  import BreadcrumbComponent from '../../components/common/BreadcrumbComponent'
  import {departmentTree,getAccountByIds,getMgr,listProperty,saveMgr} from '../../api/account';
  export default {
    components:{
      BreadcrumbComponent
    },
    data() {
      return {

        selectArr:[],  // 右边列表
        buttonSize: 'large',
        options: [],
        value: '',
        nowSelectData: [],
        nowSelectRightData: [],
        activeName: 'accountagentmgr',
        treeList:[],
        extDepartment : [],
        accountId:"",
        showMgrForm:{
          agentShowUnMgrForm:true,
          agentShowMgrBtn:true,
          agentShowAlreadyMgrForm: true,
          extensionShowUnMgrForm:false,
          extensionShowMgrBtn:false,
          extensionShowAlreadyMgrForm: false,

        },
        unMgrAgentList:[],//待管理工号
        mgrAgentList:[],//已管理工号
        unMgrExtensionList:[],//待管理分机
        mgrExtensionList:[],//已管理分机

        //选中分机工号
        nowCheckUnMgrAgentAll:[],
        nowCheckMgrAgentAll:[],
        nowCheckUnMgrExtensionAll:[],
        nowCheckMgrExtensionAll:[],


      };
    },
    methods: {
      ////////agent///////
      //管理选中部门所有分机工号
      handleAgentBtnRightAll(){
        let data = this.unMgrAgentList;
        if (data && data.length > 0) {
          let newObject = JSON.parse(JSON.stringify(data));
          this.mgrAgentList = this.handleConcatArr(newObject,this.mgrAgentList);
        }
        this.handleRemoveListSameElement(this.mgrAgentList);

      },
      //管理选中部门指定分机工号
      handleAgentBtnRightSelect(){
        let data = this.nowCheckUnMgrAgentAll;
        if (data && data.length > 0) {
          let newObject = JSON.parse(JSON.stringify(data));
          this.mgrAgentList = this.handleConcatArr(newObject, this.mgrAgentList);
          console.log(this.mgrAgentList)
        }
        this.handleRemoveListSameElement(this.mgrAgentList);
        this.$refs.unMgrAgent.clearSelection();
        this.nowCheckUnMgrAgentAll = [];
      },
      //移除所有被管理分机
      handleAgentBtnLeftAll(){
        this.mgrAgentList = [];
      },


      //移除单个被管理分机
      handleAgentLeftSelect() {
        let data = this.nowCheckMgrAgentAll;
        if (data && data.length > 0) {
          let newObject = JSON.parse(JSON.stringify(data));
          this.handleRemoveTabList(newObject,this.mgrAgentList);
        }

        this.nowCheckMgrAgentAll = [];
      },

      /////////////Extension////////////////
      handleExtensionBtnRightAll(){
        let data = this.unMgrExtensionList;
        if (data && data.length > 0) {
          let newObject = JSON.parse(JSON.stringify(data));
          this.mgrExtensionList = this.handleConcatArr(newObject,this.mgrExtensionList);
        }
        this.handleRemoveListSameElement(this.mgrExtensionList);

        //this.selectArr = new Set(this.selectArr);
      },
      //管理选中部门指定分机工号
      handleExtensionBtnRightSelect(){
        let data = this.nowCheckUnMgrExtensionAll;
        if (data && data.length > 0) {
          let newObject = JSON.parse(JSON.stringify(data));
          this.mgrExtensionList = this.handleConcatArr(newObject, this.mgrExtensionList)
        }
        this.handleRemoveListSameElement(this.mgrExtensionList);
        this.$refs.unMgrExtension.clearSelection();
        this.nowCheckUnMgrExtensionAll = [];
      },
      //移除所有被管理分机
      handleExtensionBtnLeftAll(){
        this.mgrExtensionList = [];
      },
      //移除单个被管理分机
      handleExtensionLeftSelect() {
        let data = this.nowCheckMgrExtensionAll;
        if (data && data.length > 0) {
          let newObject = JSON.parse(JSON.stringify(data));
          this.handleRemoveTabList(newObject,this.mgrExtensionList);
        }
        this.nowCheckMgrExtensionAll = [];
      },

      checkUnMgrAgentAll(val){
        this.nowCheckUnMgrAgentAll = val;
      },
      checkMgrAgent(val){
      this.nowCheckMgrAgentAll = val;
      },
      checkUnMgrExtensionAll(val){
        this.nowCheckUnMgrExtensionAll =val;
      },
      checkMgrExtensionAll(val){
        this.nowCheckMgrExtensionAll = val;
      },

      handleClick(tab, event) {
        if(tab.name === "accountagentmgr"){
          this.showMgrForm ={
            agentShowUnMgrForm:true,
            agentShowMgrBtn:true,
            agentShowAlreadyMgrForm: true,
            extensionShowUnMgrForm:false,
            extensionShowMgrBtn:false,
            extensionShowAlreadyMgrForm: false,
          };
        }
        if(tab.name === "accountextmgr"){
          this.showMgrForm ={
            agentShowUnMgrForm:false,
            agentShowMgrBtn:false,
            agentShowAlreadyMgrForm: false,
            extensionShowUnMgrForm:true,
            extensionShowMgrBtn:true,
            extensionShowAlreadyMgrForm: true,

          };
        }
      },

      handleConcatArr(selectArr, nowSelectData) {
        let arr = [];
        arr = arr.concat(selectArr, nowSelectData);
        return arr;
      },
      handleRemoveTabList(isNeedArr,  originalArr) {
        if(isNeedArr.length && originalArr.length) {
          for(let i=0; i<isNeedArr.length; i++) {
            for(let k=0; k<originalArr.length; k++) {
              if(isNeedArr[i]["id"] === originalArr[k]["id"]) {
                originalArr.splice(k, 1);
              }
            }
          }
        }
      },
      handleRemoveListSameElement(originalArr){
        for (let i = 0; i < originalArr.length; i++)  //外循环是循环的次数
        {
          for (let j = originalArr.length - 1 ; j > i; j--)  //内循环是 外循环一次比较的次数
          {
            if (originalArr[i]["id"] === originalArr[j]["id"])
            {
              originalArr.splice(j, 1);
            }

          }
        }
      },
      saveMgrAccount(){
        //获取到所有的id数据进行操作
        let mgrIds = [];
        for (let i = 0; i < this.mgrAgentList.length; i++)  //外循环是循环的次数
        {
          mgrIds.push(this.mgrAgentList[i]["id"]);
        }
        for (let i = 0; i < this.mgrExtensionList.length; i++)  //外循环是循环的次数
        {
          mgrIds.push(this.mgrExtensionList[i]["id"]);
        }

        console.log(this.accountId+":"+mgrIds);
        saveMgr(this.accountId,mgrIds).then((resp) => {
          let code = resp.data.code;
          if (code === 200) {
            this.$message({
              type: 'success',
              message: '添加管理账户成功!'
            });
            this.returnForm();
          } else {
            this.$message.error(resp.data.message);
          }
        });
      },
      departmentTree(){
        departmentTree().then(response => {
          this.treeList = response.data;
        }).catch(error => {
        })
      },
      returnForm(){
        let path = this.$route.query.redirect;
        this.$router.replace({ path: path === '/account' || path === undefined ? '/account' : path })
      },
      accountTreeSelect(){
       let treeIds =  this.$refs.accountTree.getCheckedKeys();
        listProperty( treeIds,4).then((resp) => {
          this.unMgrAgentList = resp.data;
        });
        listProperty( treeIds,8).then((resp) => {
          this.unMgrExtensionList = resp.data;
        });
      },
      //格式化管理部门
      formatterAccountTindesp(row){
        let result = "";
        if(row.departments  && row.departments.length > 0){
          for(let i = 0; i < row.departments.length;i++){
            result += row.departments[i].name+",";
          }
          result = result.substring(0,result.length-1);
        }
        return result;

      },
      //格式管理部门
      formatterAccountManageDesp(row){
        let result = "";
        if(row.mgrDepartments  && row.mgrDepartments.length > 0){
          for(let i = 0; i < row.mgrDepartments.length;i++){
            result += row.departments[i].name+",";
          }
          result = result.substring(0,result.length-1);
        }
        return result;
      },
      formatterAccountType(row){
        let value = row.property;
        if (value === 1) {
          return this.$t('m.accountadmin');
        } else if (value === 2) {
          return this.$t('m.accountmonitor');
        } else if (value === 4) {
          return  this.$t('m.accountmonitor');
        } else if (value === 8) {
          return this.$t('m.accountext');
        } else if (value === 16) {
          return this.$t('m.accountdesp');
        }
      },

    },
    computed:{
      appData:function () {
        return {title:this.$t('m.accountmodifyac')};
      }
    },
    mounted() {
      this.departmentTree();
      this.accountId = this.$route.params.id;
      let selectAccountDepartment = "";
      if(this.accountId  != null && this.accountId !== ""){
        getAccountByIds(this.accountId).then((resp) => {
          let code = resp.data.code;
          if (code === 200) {
            let accountData = resp.data.data;
            if(accountData.departments  && accountData.departments.length > 0) {
              for (let i = 0; i < accountData.departments.length; i++) {
                this.extDepartment.push(accountData.departments[i].id);
              }
            }
            this.$refs.accountTree.setCheckedKeys(this.extDepartment);
             selectAccountDepartment = this.extDepartment.join(",");
            console.log("测试"+selectAccountDepartment)
            listProperty( this.extDepartment,4).then((resp) => {
              this.unMgrAgentList = resp.data;
            });
            listProperty( this.extDepartment,8).then((resp) => {
              this.unMgrExtensionList = resp.data;
            });

          }
        });
        //console.log("账户id:"+this.extDepartment);

        getMgr(this.accountId,4).then((resp) => {
          this.mgrAgentList = resp.data;
        });
        getMgr(this.accountId,8).then((resp) => {
          this.mgrExtensionList = resp.data;
        });

      }

    }

  };
</script>

<style>
  .opSetting{
    text-align: center;
    margin-top:30px;
  }
  .spacing{
    margin-top:10px;
  }
</style>
