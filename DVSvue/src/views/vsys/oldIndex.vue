<template>
  <div class="main-conent main-conent-screen main-conent-bgFFF main-conent-borderradius">

    <el-card shadow="never" class="border-none">
      <div slot="header">
        <el-form label-width="100px">
          <el-row type="flex" justify="end" class="flex-wrap-wrap">
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
              <el-form-item label="活动名称：">
                <el-input v-model="svalue1"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
              <el-form-item label="省市区：">
              <el-select v-model="svalue2" clearable placeholder="请选择" style="width:100%;">
                <el-option label="浙江" value="1"></el-option>
                <el-option label="安徽" value="2"></el-option>
                <el-option label="江苏" value="3"></el-option>
                <el-option label="北京" value="4"></el-option>
              </el-select>
            </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
              <el-form-item label="活动名称：">
                <el-input v-model="svalue1"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
              <el-form-item label="省市区：">
                <el-select v-model="svalue2" clearable placeholder="请选择" style="width:100%;">
                  <el-option label="浙江" value="1"></el-option>
                  <el-option label="安徽" value="2"></el-option>
                  <el-option label="江苏" value="3"></el-option>
                  <el-option label="北京" value="4"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
              <el-form-item label="活动名称：">
                <el-input v-model="svalue1"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
              <el-form-item label="省市区：">
                <el-select v-model="svalue2" clearable placeholder="请选择" style="width:100%;">
                  <el-option label="浙江" value="1"></el-option>
                  <el-option label="安徽" value="2"></el-option>
                  <el-option label="江苏" value="3"></el-option>
                  <el-option label="北京" value="4"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" v-if="searchOpen">
              <el-form-item label="更新日期：">
                <el-date-picker
                  v-model="svalue3"
                  type="date"
                  placeholder="选择日期"
                  style="width:100%;">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" v-if="searchOpen">
              <el-form-item label="活动名称：">
                <el-input v-model="svalue1"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" v-if="searchOpen">
              <el-form-item label="活动名称：">
                <el-input v-model="svalue1"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" v-if="searchOpen">
              <el-form-item label="活动名称：">
                <el-input v-model="svalue1"></el-input>
              </el-form-item>
            </el-col>

            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" class="text-right">
              <el-button type="primary">查询</el-button>
              <el-button type="text" v-if="!searchOpen" @click="handleSearchOpen(true)">展开<i class="el-icon-arrow-down el-icon--right"></i></el-button>
              <el-button type="text" v-else @click="handleSearchOpen(false)">收起<i class="el-icon-arrow-up el-icon--right"></i></el-button>
            </el-col>
          </el-row>
        </el-form>

      </div>
    </el-card>
    <div class="screen-header" >
      <el-row >
        <el-col :span="6">
          <el-button-group>
          <el-button type="success" round>成功按钮</el-button>
         <el-button type="info" round>信息按钮</el-button>
          </el-button-group>
          <el-dropdown class="margin-l10">
            <el-button type="primary" icon="el-icon-plus">
              更多操作<i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>批量删除</el-dropdown-item>
              <el-dropdown-item>批量审批</el-dropdown-item>
              <el-dropdown-item>批量编辑</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
        <el-col :span="18" class="text-right">
          <el-input
            class="margin-l10"
            style="width:200px;"
            placeholder="请输入搜索内容"
            v-model="input1">
            <i slot="suffix" class="el-input__icon el-icon-search cursor-pointer"></i>
          </el-input>
          <el-button icon="el-icon-circle-check" class="margin-l10" @click="searchDrawer = true">高级搜索</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table
      class="screen-conent"
      table-class="custom-table"
      header-row-class-name="custom-table-header"
      :data="tableData"
      ref="table"
      @header-dragend="handleHeaderDragend">
      <el-table-column
        fixed
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        fixed
        prop="date"
        label="日期"
        width="150">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        min-width="120">
      </el-table-column>
      <el-table-column
        prop="province"
        label="省份"
        min-width="120">
      </el-table-column>
      <el-table-column
        prop="city"
        label="市区"
        min-width="120">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        min-width="300">
      </el-table-column>
      <el-table-column
        prop="zip"
        label="邮编"
        min-width="120">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button  @click="toogleExpand(scope.row)" type="text" round>查看</el-button>
          <el-dropdown class="margin-l5">
                                <span class="cursor-pointer">
                                    <el-button type="text" >更多<i class="el-icon-arrow-down el-icon--right"></i></el-button>
                                </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="tableEdit(scope.row)">编辑</el-dropdown-item>
              <el-dropdown-item @click.native="tableDel(scope.row)">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
      <el-table-column  fixed="right" width="100">
        <template slot-scope="scope">
        <i class="el-icon-plus"  @click="toogleExpand(scope.row)"></i>
        </template>
      </el-table-column>
      <el-table-column type="expand" width="1">
        <template slot-scope="props">
          <el-form label-position="left"  class="demo-table-expand">
            <el-form-item label="日期">
              <span>{{ props.row.date }}</span>
            </el-form-item>
            <el-form-item label="姓名">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="省份">
              <span>{{ props.row.province }}</span>
            </el-form-item>
            <el-form-item label="日期">
              <span>{{ props.row.date }}</span>
            </el-form-item>
            <el-form-item label="姓名">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="地址">
              <span>{{ props.row.address }}</span>
            </el-form-item>

          </el-form>
        </template>
      </el-table-column>


    </el-table>


    <div class="screen-footer text-right">
      <el-pagination
        background
        layout="sizes, prev, pager, next, total"
        :page-sizes="[10, 20, 50, 100]"
        :total="1000">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import ScreenTable from '../../components/ScreenTable';
  export default {
    components: {
      ScreenTable
    },
    data () {
      return {
        input1: '',
        svalue1: '',
        svalue2: '',
        svalue3: '',
        searchOpen: false,
        searchDrawer: false,
        expands: [],
        tableData: [
          {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '我的月亮我的心',
            zip: 200333
          },
          {
            date: '2016-05-04',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1517 弄',
            zip: 200333
          },
          {
            date: '2016-05-01',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1519 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          },
          {
            date: '2016-05-04',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1517 弄',
            zip: 200333
          },
          {
            date: '2016-05-01',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1519 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-02',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          },
          {
            date: '2016-05-04',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1517 弄',
            zip: 200333
          }
        ],
        tableHeight: 350,
        resizeHandler: null
      };
    },
    methods: {
      toogleExpand(row) {
        let $table = this.$refs.table;
        $table.toggleRowExpansion(row)
      },

      handleSearchOpen(v) {
        this.searchOpen = v;
      },
      handleHeaderDragend(newWidth, oldWidth, column, event) {
        this.$emit('header-dragend', newWidth, oldWidth, column, event);
      },
      handleClick(row) {
        const $table = this.$refs.table

        $table.toggleRowExpansion(row)
        $table.toggleRowSelection(row)
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
      }
    },
    mounted() {

    }
  };

</script>
<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>

