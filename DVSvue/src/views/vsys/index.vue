<template>
  <div class="sound">
    <div class="sound-top">
      <div class="f-row">
        <div class="changeTime">
        <span style="line-height: 30px">时间查询方式：</span>
        <el-select v-model="value" placeholder="请选择" style="width: 22%;" @change="queryTime">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            >
          </el-option>
        </el-select>
          <el-date-picker
            v-if="showTime"
            v-model="value2"
            type="daterange"
            align="right"
            unlink-panels
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :picker-options="pickerOptions"
            style="width: 57%">
          </el-date-picker>
            <div class="second-input" v-if="showTimes">
              <el-input placeholder="时长大于/s" ></el-input>
              <span style="line-height: 30px">至</span>
              <el-input placeholder="时长小于/s" ></el-input>
            </div>
        </div>
        <div class="select-style">
        <span>呼叫类型：</span>
        <el-select v-model="value" placeholder="请选择" style="width: 50%">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        </div>
        <div class="select-style">
        <span>部门：</span>
        <el-select v-model="value" placeholder="请选择" style="width: 50%">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        </div>
      </div>
      <div class="f-row">
        <div class="select1" style="margin-left: 40px">
          <span>话务员姓名：</span>
          <el-select v-model="value" filterable placeholder="请选择" style="width: 60%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="select1">
          <span>话务员工号：</span>
          <el-select v-model="value" filterable placeholder="请选择" style="width: 60%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="select1" style="margin-left: 60px">
          <span>分机号：</span>
          <el-select v-model="value" filterable placeholder="请选择" style="width: 60%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="button1" style="margin-left: 55px" v-show="buttonHide">
          <el-button round style="height: 30px;padding: 5px 23px;background-image: linear-gradient(rgb(7 151 143), rgb(138, 236, 230));color: white" @click="more">更多条件...</el-button>
        </div>
      </div>
      <div class="checkHide"  v-show="checks">
          <div class="f-row">
            <div class="input1">
              <span style="line-height: 30px;">主叫号码：</span>
              <el-input style="width: 190px"></el-input>
              <span style="line-height: 30px;">模糊查询</span>
              <el-switch v-model="vague" style="line-height: 30px;align-content: center"></el-switch>
            </div>
            <div style="margin-left: 80px">
              <span style="line-height: 30px;">被叫号码：</span>
              <el-input style="width: 190px"></el-input>
              <span style="line-height: 30px">模糊查询</span>
              <el-switch v-model="vague" style="line-height: 30px;align-content: center"></el-switch>
            </div>
            <div style="margin-left: 80px">
              <span>Grade：</span>
              <el-select v-model="value" filterable placeholder="请选择" style="width: 70%">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </div>
          </div>
          <div class="f-row">
            <div style="margin-left: 19px">
              <span style="line-height: 30px">Business identify：</span>
              <el-input style="width: 190px"></el-input>
            </div>
            <div style="margin-left: 190px">
              <span style="line-height: 30px">ACD：</span>
              <el-input style="width: 190px"></el-input>
            </div>
            <div style="margin-left: 170px">
              <span>lfback：</span>
              <el-select v-model="value" filterable placeholder="请选择" style="width: 190px">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </div>
          </div>
        <div class="f-row">
          <div style="margin-left: 65px">
            <span style="line-height: 30px;">Meeting：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 190px">
            <span style="line-height: 30px">Preid：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 130px">
            <span>ColumnsOne：</span>
            <el-input style="width: 190px"></el-input>
          </div>
        </div>
        <div class="f-row">
          <div style="margin-left: 40px">
            <span>ColumnsTwo：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 140px">
            <span>ColumnsThree：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 125px">
            <span>ColumnsFour：</span>
            <el-input style="width: 190px"></el-input>
          </div>
        </div>
        <div class="f-row">
          <div style="margin-left: 40px">
            <span>ColumnsFive：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 155px">
            <span>ColumnsSix：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 115px">
            <span>ColumnsSeven：</span>
            <el-input style="width: 190px"></el-input>
          </div>
        </div>
        <div class="f-row">
          <div style="margin-left: 35px">
            <span>ColumnsEight：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 148px">
            <span>ColumnsNine：</span>
            <el-input style="width: 190px"></el-input>
          </div>
          <div style="margin-left: 130px">
            <span>ColumnsTen：</span>
            <el-input style="width: 190px"></el-input>
          </div>
        </div>
      </div>
      <div style="display: inline-flex;width: 100%">
      <div class="button-row">
        <el-button round style="height: 30px;padding: 5px 23px;background-image: linear-gradient(#8e9eab,#eef2f3 );color: white">重置</el-button>
        <el-button round style="height: 30px;padding: 5px 23px;background-image: linear-gradient(#2F80ED,#56CCF2 );color: white" @click="selectByInfo">查询</el-button>
      </div>
      <div style="float: right" v-show="checks">
        <el-button round style="height: 30px;padding: 5px 23px;
        background-image: linear-gradient(#d852ed,#afd4f2 );
        color: white" icon="el-icon-arrow-up" @click="putAway">收起</el-button>
      </div>
      </div>
    </div>
    <div class="sound-table">
      <template>
        <el-table
          :data="tableData"
          style="width: 100%"
          :default-sort = "{prop: 'date', order: 'descending'}"
        >
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="date"
            label="日期"
            sortable
            width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名"
            sortable
            width="180">
          </el-table-column>
          <el-table-column
            prop="address"
            label="地址"
            :formatter="formatter">
          </el-table-column>
        </el-table>
      </template>
    </div>
    <div class="sound-page">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="100"
        layout="total, sizes, prev, pager, next, jumper"
        :total="400">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
        options: [{
          value: '1',
          label: '时间'
        }, {
          value: '2',
          label: '时间段'
        }],
        showTime:true,
        showTimes:false,
        checks:false,
        buttonHide:true,
        value: '1',
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value1: '',
        value2: '',
        currentPage4: 4,
        vague:'',
      };
    },
    methods: {
      formatter(row, column) {
        return row.address;
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      queryTime(val){
        if (val==='1'){
          this.showTime=true;
          this.showTimes=false;
        }else{
         this.showTime=false;
         this.showTimes=true;
        }
      },
      more(val){
        console.log('----------点击了按钮-----------')
        this.checks=true;
        this.buttonHide=false
      },
      selectByInfo(){
        this.checks=false;
        this.buttonHide=true
      },
      putAway(val){
        this.checks=false;
        this.buttonHide=true
      }
    }
  };
</script>
<style>
  .sound{
    background-color: #F0F0F0;
  }
  .sound-top{
    margin-top: 20px;
    background-color: white;
    margin-bottom: 10px;
  }
  .sound-top .el-input__inner{
    height: 30px;
    line-height: 30px;
  }
  .sound-top .el-input__icon{
    line-height: 26px;
  }
  .second-input{
    display: inline-flex;
    width: 57%;
  }
  .f-row{
    display: inline-flex;
    margin-bottom: 15px;
  }
  .select-style{
    width: 23%;
    display: inline-flex;
    line-height: 30px;
    margin-left: 30px;
  }
  .changeTime{
    display: inline-flex;
    line-height: 30px;
    margin-left: 30px;
    margin-right: 30px;
    width: 45%;
  }
  .changeTime .el-range-separator{
    line-height: 25px;
  }
  .select1{
    display: inline-flex;
    width: 88%;
    line-height: 30px;
    margin-left: 30px;
  }
  .button1{
    width: 75%;
    margin-left: 30px;
  }
  .button-row{
    width: 100%;
    text-align: center;
    height: 40px;
  }
  .input-check{
    display: inline-flex;
    height: 30px;
  }
  .input1{
    margin-left: 60px;
  }
  .input2{
    margin-left: 142px;
  }
  .input3{
    margin-left: 120px;
  }
</style>
