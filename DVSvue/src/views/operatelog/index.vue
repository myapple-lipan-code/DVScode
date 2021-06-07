<template>
  <div class="main-conent main-conent-screen main-conent-bgFFF main-conent-borderradius">
    <el-card shadow="never" class="border-none">
      <div slot="header">
        <el-form label-width="100px">
          <el-row type="flex" justify="end" class="flex-wrap-wrap">
            <el-col :xs="24" :sm="24"  :md="6" :lg="6" :xl="6">
              <el-form-item label="操作人：">
                <el-input v-model="operatelogForm.operator"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24"  :md="6" :lg="6" :xl="6">
              <el-form-item label="操作类型：">
                <el-select v-model="operatelogForm.operateType" clearable placeholder="请选择" style="width:100%;">
                  <el-option value="" selected="selected" :label="$t('m.selectAll')"></el-option>
                  <el-option value="130" :label="$t('m.operateloglogin')"></el-option>
                  <el-option value="144" :label="$t('m.operateloglogout')"></el-option>
                  <el-option value="64" :label="$t('m.operatelogselect')"></el-option>
                  <el-option value="3" :label="$t('m.operatelogplay')"></el-option>
                  <el-option value="96" :label="$t('m.operatelogdownload')"></el-option>
                  <el-option value="112" :label="$t('m.operatelogreport')"></el-option>
                  <el-option value="80" :label="$t('m.operatelogstatistics')"></el-option>
                  <el-option value="18" :label="$t('m.operatelogmonitor')"></el-option>
                  <el-option value="256" :label="$t('m.operatelogbupdate')"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6" v-if="searchOpen">
              <el-form-item label="开始时间：">
                <el-date-picker
                  v-model="operatelogForm.starttime"
                  type="datetime"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  style="width:100%;"  :picker-options="pickerOptions">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6" v-if="searchOpen">
              <el-form-item label="开始时间：">
                <el-date-picker
                  v-model="operatelogForm.endtime"
                  type="datetime"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  style="width:100%;"  :picker-options="pickerOptions">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" class="text-right">
              <el-button class="filter-item" size="mini" type="success" icon="el-icon-search" @click="searchOperate">搜索</el-button>
              <el-button type="text" v-if="!searchOpen" @click="handleSearchOpen(true)">展开<i class="el-icon-arrow-down el-icon--right"></i></el-button>
              <el-button type="text" v-else @click="handleSearchOpen(false)">收起<i class="el-icon-arrow-up el-icon--right"></i></el-button>
            </el-col>

          </el-row>
        </el-form>
      </div>

    <!--工具栏-->


    <screen-table
      class="screen-conent"
      table-class="custom-table"
      header-row-class-name="custom-table-header"
      :data="pagination.dataList"  :header-cell-style="{textAlign: 'center'}"
      :cell-style="{ textAlign: 'center' }">
      <el-table-column
        fixed
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        fixed
        prop="operator"
        label="操作人"
        width="150">
      </el-table-column>
      <el-table-column
        prop="operateStarttime"
        label="操作时间"
        min-width="120" :formatter="timestampToTime" >
      </el-table-column>
      <el-table-column
        prop="operateType"
        label="操作类型"
        min-width="80" :formatter="formatterOperateType">
      </el-table-column>
      <el-table-column
        prop="recordUniqueid"
        label="业务标识"
        min-width="160">
      </el-table-column>
      <el-table-column
        prop="extention"
        label="分机"
        min-width="60">
      </el-table-column>
      <el-table-column
        prop="callid"
        label="主叫号码"
        min-width="80">
      </el-table-column>
      <el-table-column
        prop="calledid"
        label="被叫号码"
        min-width="80">
      </el-table-column>
      <el-table-column
        prop="ortherInfo"
        label="其他信息"
        min-width="160">
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
        :total="pagination.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    </el-card>
  </div>
</template>
<script>
  import ScreenTable from '../../components/ScreenTable/index';
  import { getOperatePage } from "@/api/index";
  export default {
    components: {
      ScreenTable
    },
    data () {
      return {
        svalue1: '',
        svalue2: '',
        svalue3: '',
        searchOpen: false,
        pagination:{
          current:1,
          size:20,
          total:0,
        dataList:[]
        },
        operatelogForm:{
          starttime:'',
          endtime:'',
          operateType:'',
          operator:''
        },
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },

      };
    },
    methods: {

      handleSearchOpen(v) {
        this.searchOpen = v;
      },
      //改变分页的每页的页数
      handleSizeChange(size) {
        this.pagination.size = size;
        this.getOperatePage();
      },
      // 改变分页的当前页面
      handleCurrentChange(currentPage) {
        this.pagination.current = currentPage;
        this.getOperatePage();
      },
      // 查询

      // 获取用户列表
      getOperatePage() {
       let params = {
         "operatelogForm":{
           starttime:this.operatelogForm.starttime,
           endtime:this.operatelogForm.endtime,
           operateType:'',
           operator:''
         },
         "pageForm":{
           current:this.pagination.current,
           size:this.pagination.size
         }
       };
        getOperatePage(params).then(response => {
          let code = response.data.code;
          if (code === 200) {
            this.pagination.dataList = response.data.data.dataList;
            this.pagination.total = response.data.data.total;
          } else {
            this.$confirm(response.message)
          }
        }).catch(error => {
        })
      },
      timestampToTime(row) {
        var date = new Date(row.operateStarttime)
        let Y = date.getFullYear() + "-";
        let M =
          (date.getMonth() + 1 < 10
            ? "0" + (date.getMonth() + 1)
            : date.getMonth() + 1) + "-";
        let D = this.change(date.getDate()) + " ";
        let h = this.change(date.getHours()) + ":";
        let m = this.change(date.getMinutes()) + ":";
        let s = this.change(date.getSeconds());
        return Y + M + D + h + m + s;
      },
      change(t) {
        if (t < 10) {
          return "0" + t;
        } else {
          return t;
        }
      },
      formatterOperateType(row){
        let value = row.operateType;
        switch (value) {
          case 130:
            return this.$t('m.operateloglogin');
          case 144:
            return this.$t('m.operateloglogout');
          case 64:
            return this.$t('m.operatelogselect');
          case 3:
            return this.$t('m.operatelogplay');
          case 96:
            return this.$t('m.operatelogdownload');
          case 112:
            return this.$t('m.operatelogreport');
          case 80:
            return this.$t('m.operatelogstatistics');
          case 18:
            return this.$t('m.operatelogmonitor');
          case 256:
            return this.$t('m.operatelogbupdate');
          default:
            return "";
        }
      },
      GetStartDateStr(AddDayCount) {
        var dd = new Date();
        dd.setDate(dd.getDate()+AddDayCount);
        var y = dd.getFullYear();
        var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);
        var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();
        return y+"-"+m+"-"+d+' '+'00:00:00';
      },
      GetEndDateStr(AddDayCount) {
        var dd = new Date();
        dd.setDate(dd.getDate()+AddDayCount);
        var y = dd.getFullYear();
        var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);
        var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();
        return y+"-"+m+"-"+d+' '+'23:59:59';
      },
      searchOperate(){
        let params = {
          "operatelogForm":{
            starttime:this.operatelogForm.starttime,
            endtime:this.operatelogForm.endtime,
            operateType:this.operatelogForm.operateType,
            operator:this.operatelogForm.operator
          },
          "pageForm":{
            current:this.pagination.current,
            size:this.pagination.size
          }
        };
        getOperatePage(params).then(response => {
          let code = response.data.code;
          if (code === 200) {
            this.pagination.dataList = response.data.data.dataList;
            this.pagination.total = response.data.data.total;
          } else {
            this.$confirm(response.message)
          }
        }).catch(error => {
        })
      }



    },
    mounted() {
      this.operatelogForm.starttime = this.GetStartDateStr(0);
      this.operatelogForm.endtime = this.GetEndDateStr(0);
      this.getOperatePage();
    }
  };
</script>
