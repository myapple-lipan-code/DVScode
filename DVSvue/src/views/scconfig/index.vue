<template>
  <div  class="main-conent main-conent-screen main-conent-bgFFF main-conent-borderradius">
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <div class="screen-header"  >
      <el-row >
        <el-col :span="8" class="text-left">
          <el-button round style="height: 30px;padding: 5px 23px;background-image: linear-gradient(#8e9eab,#eef2f3 );color: white">批量修改</el-button>
          <el-button round style="height: 30px;padding: 5px 23px;background-image: linear-gradient(#2F80ED,#56CCF2 );color: white" >刷新</el-button>
          <el-button round style="height: 30px;padding: 5px 23px;background-image: linear-gradient(#8e9eab,#eef2f3 );color: white">实时生效</el-button>
        </el-col>
        <el-col :span="16" class="text-right">
          <el-input
            class="margin-l10"
            style="width:200px;"
            placeholder="请输入搜索内容"
            v-model="searchForm.ext">
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
        prop="ext"
        :label="$t('m.scconfigextno')"
        min-width="100" sortable>
      </el-table-column>
      <el-table-column
        prop="agentid"
        :label="$t('m.scconfigagentid')"
        min-width="100" sortable>
      </el-table-column>
      <el-table-column
        prop="ip"
        :label="$t('m.scconfigip')"
        min-width="100"
         sortable>
      </el-table-column>
      <el-table-column
        prop="cancap"
        :label="$t('m.scconfigcancap')"
        min-width="100"
        >
      </el-table-column>
      <el-table-column
        prop="captype"
        :label="$t('m.scconfigcaptype')"
        min-width="100"
       >
      </el-table-column>
      <el-table-column
        prop="fileport"
        :label="$t('m.scconfigfileport')"
        min-width="120"
      >
      </el-table-column>
      <el-table-column
        prop="maxsctime"
        :label="$t('m.scconfigmaxsctime')"
        min-width="120"
      >
      </el-table-column>
      <el-table-column
        prop="showform"
        :label="$t('m.scconfigshowform')"
        min-width="120"
      >
      </el-table-column>
      <el-table-column
        prop="windowsuser"
        :label="$t('m.scconfigwindowsuser')"
        min-width="120"
      >
      </el-table-column>
      <el-table-column
        fixed="right"
        :label="$t('m.selectcommand')"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleEditAccount(scope.row)" icon="el-icon-edit" circle ></el-button>
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
  import {scconfigList} from '../../api/sconconfig';
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
          ext:"",
        },
        pagination:{
          current:1,
          size:20,
        },
        total: 0,
        accountList:[],

        multipleSelection:[],
      };
    },
    methods: {

      //改变分页的每页的页数
      handleSizeChange(size) {
        this.pagination.size = size;
        this.getScconfigPage();
      },
      // 改变分页的当前页面
      handleCurrentChange(currentPage) {
        this.pagination.current = currentPage;
        this.getScconfigPage();
      },
      handleSelectionChange(val) {
        this.multipleSelection = [];
        val.forEach((element) => {
          this.multipleSelection.push(element.id);
        });
        console.log(this.multipleSelection);

      },
      getScconfigPage(){
        let params = {
          "current":this.pagination.current,
          "size":this.pagination.size,
          "ext":this.searchForm.accountName
        };
        scconfigList(params).then(response => {
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
          "current":this.pagination.current,
          "size":this.pagination.size,
          "ext":this.searchForm.ext
        };
        scconfigList(params).then(response => {
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

    },
    computed:{
      appData:function () {
        return {title:this.$t('m.scconfigtitle')};
      }
    },

    mounted() {
      this.getScconfigPage();

    }
  };
</script>
