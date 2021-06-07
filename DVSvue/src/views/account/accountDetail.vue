<template>
  <div class="main-conent main-conent-minheight" >
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <el-card shadow="never" class="border-none margin-t24">
      <el-row>

        <el-col :xs="24" :sm="20"  :md="16" :lg="14" :xl="14">

          <el-form :model="accountForm"  ref="accountForm" label-width="130px" align="left">
            <el-form-item :label="$t('m.accounttype')"  v-show="showForm.showAccountType" >
              <el-radio-group v-model="accountForm.accountType" @change="accountTypeChange">
                <el-radio :label="1"  >{{$t('m.accountadmin')}}</el-radio>
                <el-radio :label="2"  >{{$t('m.accountmonitor')}}</el-radio>
                <el-radio :label="4"  >{{$t('m.accountagent')}}</el-radio>
                <el-radio :label="8"  >{{$t('m.accountext')}}</el-radio>
                <el-radio :label="16" >{{$t('m.accountdesp')}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item :label="$t('m.accountbatchadd')"  v-show="showForm.showAccountbatchadd" >
              <el-switch v-model="accountForm.accountbatchadd" @change="accountBatchAddChange"></el-switch>
            </el-form-item>
            <el-form-item :label="$t('m.accountprefix')" v-show="showForm.showAccountprefix">
              <el-input v-model="accountForm.accountprefix"></el-input>
            </el-form-item>
            <el-form-item :label="$t('m.accountname')" v-show="showForm.showAccountname">
              <el-input v-model="accountForm.accountname"></el-input>
            </el-form-item>
            <el-form-item :label="$t('m.accountsaccount')" v-show="showForm.showAccountsaccount">
              <el-input v-model="accountForm.accountsaccount"></el-input>
            </el-form-item>
            <el-form-item :label="$t('m.accounteaccount')" v-show="showForm.showAccounteaccount">
              <el-input v-model="accountForm.accounteaccount"></el-input>
            </el-form-item>

            <el-form-item :label="$t('m.accountalias')" v-show="showForm.showAccountalias">
              <el-input v-model="accountForm.accountalias"></el-input>
            </el-form-item>
            <el-form-item :label="$t('m.accountpass')" v-show="showForm.showAccountpass">
              <el-input v-model="accountForm.accountpass" type="password"  autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item :label="$t('m.accountconfirmpass')" v-show="showForm.showAccountconfirmpass">
              <el-input v-model="accountForm.accountconfirmpass" type="password"  autocomplete="off"></el-input>
            </el-form-item>


            <el-form-item  :label="$t('m.accountdesp')" prop="region" v-show="showForm.showAccountdesp">
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

            <el-form-item align="center">
              <el-button type="primary" @click="submitForm()">保存</el-button>
              <el-button @click="resetForm()">返回</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :xs="0" :sm="2"  :md="4" :lg="4" :xl="4" class="border-solid-transparent"></el-col>
        <el-col :xs="24" :sm="20"  :md="16" :lg="6" :xl="6" >
          <div  v-show="showForm.showRightSetting">
            <div align="left"><strong>权限设置</strong></div>
            <el-tree
              :data="treeList"
              show-checkbox
              node-key="id"
              :default-expanded-keys="[0]"
              :default-checked-keys="[]"
              :props="defaultProps" align="center"
            ref="rightTree">
            </el-tree>
          </div>

        </el-col>
      </el-row>
    </el-card>
  </div>
</template>
<script>
  import PageHeader from '@/components/PageHeader';
  import BreadcrumbComponent from '../../components/common/BreadcrumbComponent'
  import {accountTree,accountAdd,getAccountByIds,accountUpdate} from '../../api/account';
  export default {
    components: {
      PageHeader,
      BreadcrumbComponent
    },
    data() {
      return {

        handlerForm:{
            batchAdd: false, // 是否批量添加
            property:8, // 帐号类型
            prefix:'', // 帐号前缀
            beginAccount:'',// 开始帐号
            endAccount:'', // 结束帐号
            password : "", // 帐号密码
            relPassword : "", // 重复密码
            alias: '', // 帐号别名
            departmentIds: '', // 所属部门
            rights:'' // 权限
        },
        showForm:{
          showAccountType: true,
          showAccountbatchadd: true,
          showAccountprefix:false,
          showAccountname:true,
          showAccountsaccount: false,
          showAccounteaccount: false,
          showAccountalias: true,
          showAccountpass: false,
          showAccountconfirmpass: false,
          showAccountdesp:true,
          showRightSetting: false

        },
        accountForm:{
          accountType: 8,
          accountbatchadd: false,
          accountname:"",
          accountprefix:"",
          accountsaccount: "",
          accounteaccount: "",
          accountalias: "",
          accountpass: "",
          accountconfirmpass: "",
          accountdesp:"",
        },
        treeList: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        extDepartment:[],
        accountId:"",
      };
    },
    methods: {
      submitForm() {
        if(this.accountId  != null && this.accountId !== ""){
          let departmentIdsStr ="";
          if( this.handlerForm.departmentIds !=null &&  this.handlerForm.departmentIds !== ""){
             departmentIdsStr = this.handlerForm.departmentIds.join(",");
          }else{
            departmentIdsStr = this.extDepartment.join(",");
          }
          let rightStr = this.$refs.rightTree.getCheckedKeys().join(",");
          let updateParams={
            id:this.accountId,
            batchAdd: this.accountForm.accountbatchadd, // 是否批量添加
            property:this.accountForm.accountType, // 帐号类型
            prefix:this.accountForm.accountprefix, // 帐号前缀
            beginAccount:this.accountForm.accountbatchadd ? this.accountForm.accountsaccount : this.accountForm.accountname,// 开始帐号
            endAccount:this.accountForm.accounteaccount, // 结束帐号
            password : this.accountForm.accountpass, // 帐号密码
            relPassword : this.accountForm.accountconfirmpass, // 重复密码
            alias: this.accountForm.accountalias, // 帐号别名
            departmentIds: departmentIdsStr, // 所属部门
            rights:rightStr // 权限
          };
          console.log("编辑:"+JSON.stringify(updateParams));
          accountUpdate(updateParams).then((resp) => {
            if (resp.data.result) {
              this.$message.success("修改账号成功:"+updateParams.id);
              this.resetForm();
            } else {
              this.$message.warning("添加账号失败");
            }
          });
        }else{
          let departmentIdsStr = this.handlerForm.departmentIds.join(",");
          let rightStr = this.$refs.rightTree.getCheckedKeys().join(",");
          let params={
            batchAdd: this.accountForm.accountbatchadd, // 是否批量添加
            property:this.accountForm.accountType, // 帐号类型
            prefix:this.accountForm.accountprefix, // 帐号前缀
            beginAccount:this.accountForm.accountbatchadd ? this.accountForm.accountsaccount : this.accountForm.accountname,// 开始帐号
            endAccount:this.accountForm.accounteaccount, // 结束帐号
            password : this.accountForm.accountpass, // 帐号密码
            relPassword : this.accountForm.accountconfirmpass, // 重复密码
            alias: this.accountForm.accountalias, // 帐号别名
            departmentIds: departmentIdsStr, // 所属部门
            rights:rightStr // 权限
          };

          console.log("修改参数:"+JSON.stringify(params));
          accountAdd(params).then((resp) => {
            if (resp.data.result) {
              this.$message.success("添加账号成功");
              this.resetForm();
            } else {
              this.$message.warning("添加账号失败");
            }
          });
        }

      },
      resetForm() {
        //console.log(this.accountId);
        //this.$refs[formName].resetFields();
        var path = this.$route.query.redirect;
        this.$router.replace({ path: path === '/account' || path === undefined ? '/account' : path })
      },
      getDepartmentIds(val){
        this.handlerForm.departmentIds = val;
      },
      //账号类型改变
      accountTypeChange(value){
        console.log(this.accountForm.accountType);
        let oldAccountType = this.accountForm.accountType;
        switch (oldAccountType) {
          case  1:
            break;
          case 2:
            break;
          case  4:
            break;
          case  8:
            break;
          case  16:
            break;
        }
        switch (value) {
          case  1:
            this.showForm =
              {
              showAccountType: true,
              showAccountbatchadd: false,
                showAccountname:true,
              showAccountprefix:false,
              showAccountsaccount: false,
              showAccounteaccount: false,
              showAccountalias: true,
              showAccountpass: true,
              showAccountconfirmpass: true,
              showAccountdesp:true,
              showRightSetting: true
          };

            break;
          case  2:
            this.showForm =
              {
                showAccountType: true,
                showAccountbatchadd: false,
                showAccountname:true,
                showAccountprefix:false,
                showAccountsaccount: false,
                showAccounteaccount: false,
                showAccountalias: true,
                showAccountpass: true,
                showAccountconfirmpass: true,
                showAccountdesp:true,
                showRightSetting: true

              };
            break;
          case  4:
            this.showForm =
              {
                showAccountType: true,
                showAccountbatchadd: true,
                showAccountprefix:false,
                showAccountname:true,
                showAccountsaccount: false,
                showAccounteaccount: false,
                showAccountalias: true,
                showAccountpass: false,
                showAccountconfirmpass: false,
                showAccountdesp:true,
                showRightSetting: false

              };
            break;
          case 8:
            this.showForm =
              {
                showAccountType: true,
                showAccountbatchadd: true,
                showAccountprefix:false,
                showAccountname:true,
                showAccountsaccount: false,
                showAccounteaccount: false,
                showAccountalias: true,
                showAccountpass: false,
                showAccountconfirmpass: false,
                showAccountdesp:true,
                showRightSetting: false

              };
            break;
          case  16:
            this.showForm =
              {
                showAccountType: true,
                showAccountbatchadd: false,
                showAccountprefix:false,
                showAccountname:true,
                showAccountsaccount: false,
                showAccounteaccount: false,
                showAccountalias: false,
                showAccountpass: false,
                showAccountconfirmpass: false,
                showAccountdesp:false,
                showRightSetting: false
              };
            break;
        }
      },
      accountBatchAddChange(callback){
       if(callback=== true){
         this.showForm =
           {
             showAccountType: true,
             showAccountbatchadd: true,
             showAccountprefix:true,
             showAccountname:false,
             showAccountsaccount: true,
             showAccounteaccount: true,
             showAccountalias: false,
             showAccountpass: false,
             showAccountconfirmpass: false,
             showAccountdesp:true,
             showRightSetting: false

           };
       }
        if(callback=== false){
          this.showForm = {
            showAccountType: true,
            showAccountbatchadd: true,
            showAccountprefix:false,
            showAccountname:true,
            showAccountsaccount: false,
            showAccounteaccount: false,
            showAccountalias: true,
            showAccountpass: false,
            showAccountconfirmpass: false,
            showAccountdesp:true,
            showRightSetting: false
          };
        }
      },
      GetAccountTree(){
        accountTree().then(response => {
         this.treeList = response.data;
        }).catch(error => {
        })
      },
      //编辑的时候选择框数据需要清空一下

    },
    created(){
      this.accountId = this.$route.params.id;
      if(this.accountId  != null && this.accountId !== ""){
        getAccountByIds(this.accountId).then((resp) => {
          let code = resp.data.code;
          if (code === 200) {
            let accountData = resp.data.data;
              this.accountForm.accountType = accountData.property;
              this.accountTypeChange(accountData.property);
              this.accountForm.accountname = accountData.name;
              this.accountForm.accountsaccount = accountData.name;
              this.accountForm.accountalias = accountData.alias;
              this.accountForm.accountpass = accountData.password;
              this.accountForm.accountconfirmpass =accountData.password;
              // this.extDepartment = accountData.departments;
              // console.log("自身部门:"+JSON.stringify(accountData.departments));
              // console.log("所有部门:"+JSON.stringify(this.departments));
              //this.changeSelect(data);   //触发此方法
              this.$refs.rightTree.setCheckedKeys(accountData.relRights);
            if(accountData.departments  && accountData.departments.length > 0) {
              for (let i = 0; i < accountData.departments.length; i++) {
                this.extDepartment.push(accountData.departments[i].id);
              }
            }
          }
        })
      }
    },
    computed:{
      departments(){
        let departments = this.$store.state.departmentList;
        return departments.departments;
      },

        appData:function () {
          return {title:this.$t('m.accountaddaccount')};
        }


    },
    mounted() {
      this.GetAccountTree();
      this.$store.dispatch('getAllDepartment');
    }
  };
</script>
