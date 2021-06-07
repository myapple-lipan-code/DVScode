<template>
  <div class="main-conent main-conent-screen main-conent-bgFFF main-conent-borderradius">
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <div class="screen-header border-none "  >
      <el-row >
        <el-col :span="6">
          <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>

            <el-button icon="el-icon-delete"
                       size="mini"
                       type="danger" @click="handelDeletes">
              删除<!--<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
            </el-button>


        </el-col>
        <el-col :span="18" class="text-right">
          <el-select v-model="searchForm.accountProperty" placeholder="请选择" >
            <el-option value="0" selected="selected" :label="$t('m.accountall')"></el-option>
            <el-option value="1" :label="$t('m.accountadmin')"></el-option>
            <el-option value="2" :label="$t('m.accountmonitor')"></el-option>
            <el-option value="4" :label="$t('m.accountagent')"></el-option>
            <el-option value="8" :label="$t('m.accountext')"></el-option>
            <el-option value="16" :label="$t('m.accountdesp')"></el-option>
          </el-select>
          <el-input
            class="margin-l10"
            style="width:200px;"
            placeholder="请输入搜索内容"
            v-model="searchForm.accountName">
            <i slot="suffix" class="el-input__icon el-icon-search cursor-pointer"></i>
          </el-input>
          <el-button  class="margin-l10" @click="searchAccount">搜索</el-button>
        </el-col>
      </el-row>
    </div>

    <screen-table
      class="screen-conent"
      table-class="custom-table"
      header-row-class-name="custom-table-header"
      :data="accountList"  :header-cell-style="{textAlign: 'center'}"
      :cell-style="{ textAlign: 'center' }"  :row-style="{height: '20px'}"
      @selection-change="handleSelectionChange">
      <el-table-column
        fixed
        type="selection"
        width="55">
      </el-table-column>

      <el-table-column
        prop="name"
        :label="$t('m.accountname')"
        min-width="120" sortable>
      </el-table-column>
      <el-table-column
        prop="alias"
        :label="$t('m.accountalias')"
        min-width="120" sortable>
      </el-table-column>
      <el-table-column
        prop="property"
        :label="$t('m.accounttype')"
        min-width="120"
       :formatter="formatterAccountType" sortable>
      </el-table-column>
      <el-table-column
        prop="alias"
        :label="$t('m.accountindesp')"
        min-width="150"
       :formatter="formatterAccountTindesp">
      </el-table-column>
      <el-table-column
        prop="alias"
        :label="$t('m.accountmanagedesp')"
        min-width="120"
      :formatter="formatterAccountManageDesp">
      </el-table-column>
      <el-table-column
        fixed="right"
        :label="$t('m.selectcommand')"
        width="200">
        <template slot-scope="scope">

          <el-button @click="handleEditAccount(scope.row)" icon="el-icon-edit" circle ></el-button>
          <el-button @click="handelDelete(scope.row)" icon="el-icon-delete" circle></el-button>
          <el-button @click="handleMgrAccount(scope.row)"  icon="el-icon-s-custom" circle  v-if="scope.row.property === 1 || scope.row.property === 2"></el-button>
<!--          <el-button @click="handleClick(scope.row)" type="info" round>管理</el-button>-->

        </template>
      </el-table-column>
    </screen-table>


    <!-- 分页 -->
    <div class="pagination">
      <el-pagination
        background
        :current-page="pagination.current"
        :page-sizes="[5, 10, 20, 40]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>



  </div>
</template>
<script>
  import ScreenTable from '../../components/ScreenTable';
  import {GetAccountList,deleteAccount,deleteAccounts} from '../../api/account';
  import PageHeader from '../../components/PageHeader';
  import BreadcrumbComponent from '../../components/common/BreadcrumbComponent'
  export default {
    components: {
      ScreenTable,
      PageHeader,
      BreadcrumbComponent
    },
    data () {
      return {
        searchForm:{
          accountProperty: "0",
          accountName:"",
        },
        pagination:{
          current:1,
          size:20,
        },
        total: 0,
        accountList:[],
        accountProperties:[
          {
            label:this.$t('m.accountall'),
            value: 0
          },
          {
            label:this.$t('m.accountadmin'),
            value: 1
          },
          {
            label:this.$t('m.accountmonitor'),
            value: 2
          },
          {
            label:this.$t('m.accountagent'),
            value: 4
          },
          {
            label:this.$t('m.accountext'),
            value: 8
          },
          {
            label:this.$t('m.accountdesp'),
            value: 16
          },
        ],
        multipleSelection:[],
      };
    },
    methods: {

      //改变分页的每页的页数
      handleSizeChange(size) {
        this.pagination.size = size;
        this.getAccountPage();
      },
      // 改变分页的当前页面
      handleCurrentChange(currentPage) {
        this.pagination.current = currentPage;
        this.getAccountPage();
      },
      handleSelectionChange(val) {
        this.multipleSelection = [];
        val.forEach((element) => {
          this.multipleSelection.push(element.id);
        });
        console.log(this.multipleSelection);

      },
      getAccountPage(){
        let params = {
          "property":this.searchForm.accountProperty,
          "current":this.pagination.current,
          "size":this.pagination.size,
          "name":this.searchForm.accountName
        };
        GetAccountList(params).then(response => {
          let code = response.data.code;
          if (code === 200) {
            this.accountList = response.data.data.dataList;
            this.total = response.data.data.total;
          } else {
            this.$confirm(response.message)
          }
        }).catch(error => {
        })
      },

      searchAccount(){
        let params = {
          "property":this.searchForm.accountProperty,
          "current":this.pagination.current,
          "size":this.pagination.size,
          "name":this.searchForm.accountName
        };
        GetAccountList(params).then(response => {
          let code = response.data.code;
          if (code === 200) {
            this.accountList = response.data.data.dataList;
            this.total = response.data.data.total;
          } else {
            this.$confirm(response.message)
          }
        }).catch(error => {
        });
      },
      //格式化账户类型
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
      handleClick(row) {
        console.log(row);
      },
      tableEdit(row){
        console.log(row);
      },
      tableDel(row){
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(row);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleEditAccount(row){
        this.$router.push({
          name: 'accountDetail',
          params: {
            id: row.id
          }
        });
      },
      handleMgrAccount(row){
        this.$router.push({
          name: 'accountMgr',
          params: {
            id: row.id
          }
        });
      },
      handelDelete(row){
        let  that = this;
        this.$confirm('此操作将永久删除账户'+row.name+', 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteAccount(row.id).then((resp) => {
            let code = resp.data.code;
            if (code === 200) {
              this.$message({
                type: 'success',
                message: '删除账户'+row.name+'成功!'
              });
              that.getAccountPage();
            } else {
              this.$message.error(resp.data.message);
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handelDeletes(){
        console.log(this.multipleSelection);
        let  that = this;
        if( this.multipleSelection !==undefined && this.multipleSelection.length > 0 ) {

          this.$confirm('此操作将永久删除账户, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            deleteAccounts(this.multipleSelection).then((resp) => {
              let code = resp.data.code;
              if (code === 200) {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                that.getAccountPage();
              } else {
                this.$message.error(resp.data.message);
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }
      },
      handleAdd(){
        this.$router.push({
          name: 'accountDetail',
        });
      }

    },
    computed:{
      appData:function () {
        return {title:this.$t('m.accounttitle')};
      }
    },

    mounted() {
      this.getAccountPage();

    }
  };
</script>
