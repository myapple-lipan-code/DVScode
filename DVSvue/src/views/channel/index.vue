<template>
  <section>
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <el-col :span="24" class="toolbar" style="padding-bottom: 0;
    height: 55px;background-color: transparent;" align="left">
      <el-form :inline="true" >
        <el-form-item>
          <el-button type="primary" @click="addChannel">添加</el-button>
        </el-form-item>
        <el-form-item>
          <el-button  type="primary" @click="beatchDel">删除</el-button>
        </el-form-item>
        <el-form-item>
          <el-button  type="primary">实时生效</el-button>
        </el-form-item>
        <el-form-item>
          <template  slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"/>
          </template>
        </el-form-item>
        <!--        <el-form-item>-->
        <!--          <el-select v-model="value" placeholder="请选择"  @change="currentSel">-->
        <!--            <el-option-->
        <!--              v-for="item in options" :key="item.id" :label="item.label" :value="item">-->
        <!--            </el-option>    -->
        <!--          </el-select>    -->
        <!--        </el-form-item>-->
      </el-form>
    </el-col>
    <!--列表-->
    <template>
      <el-table :data="channelList.slice((currentPage-1)*pageSize,currentPage*pageSize)
    .filter(data => !search || data.extension.toLowerCase().includes(search.toLowerCase()))" highlight-current-row  style="width: 100%;"
                :row-style="{height:'8px'}"
                :cell-style="{padding:'0px'}"
                :height="tableHeight" ref="table"
                border @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="40">
        </el-table-column>
        <el-table-column
          align="center" min-width="110" label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="channelEdit(scope.row)"><i class="el-icon-edit"></i></el-button>
            <el-button
              size="mini"
              type="danger"
              @click="channelDel(scope.row)"><i class="el-icon-delete"></i></el-button>
          </template>
        </el-table-column>

        <el-table-column prop="extension" label="分机号" width="145" sortable>
        </el-table-column>
        <el-table-column prop="ctiAppIPAddr" label="CTI程序IP" width="145"  sortable>
        </el-table-column>
        <el-table-column prop="computer" label="录音客户端IP" width="160" sortable>
        </el-table-column>
        <el-table-column prop="channelType" label="通道类型" width="160" sortable >
        </el-table-column>
        <el-table-column prop="enable" label="是否启用" min-width="120" sortable :formatter="enableFormatter">
        </el-table-column>
        <el-table-column prop="cardType" label="录音方案" min-width="160" sortable>
        </el-table-column>
        <el-table-column prop="chNoInCard" label="通道号" min-width="120" sortable >
        </el-table-column>
        <el-table-column prop="channelIPAddr" label="分机IP" min-width="120" sortable >
        </el-table-column>
        <el-table-column prop="channelVirtualExt" label="虚拟分机" min-width="120" sortable >
        </el-table-column>
      </el-table>
      <el-col :span="24" class="toolbar">
        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page.sync="currentPage" :page-sizes="[5, 10, 20, 40]" :page-size="pageSize"
                       layout="prev, pager, next" :total="channelList.length" align="left">
        </el-pagination>
      </el-col>
    </template>
  </section>
</template>
<script>
  import { channelList ,deleteChannel,deleteChannels} from "@/api/channel";
  import BreadcrumbComponent from '../../components/common/BreadcrumbComponent'
  export default {
    components:{
      BreadcrumbComponent
    },
    data() {
      return {
        appData:{
          title:"录音参数"
        },
        loading: false,
        search:"",
        channelList: [],
        currentPage: 1,
        pageSize: 20,
        //测试
        options: [
          {
            value: '所有分机',
            label: '',
            id:""
          },
          {
          value: '选项1',
          label: '227',
           id:"227"
        },
          {
          value: '选项2',
          label: '233',
            id:"233"
          }, {
          value: '选项3',
          label: '234',
            id:"234"
        }, {
          value: '选项4',
          label: '235',
            id:"235"
        }, {
          value: '选项5',
          label: '236',
            id:"236"
        }],
        value: '',
        tableHeight: 50,
        multipleSelection:[]
      }
    },
    methods: {
      getChannelList: function () {
       let that = this;
        channelList().then((resp) => {
          let code = resp.data.code;
          if (code === 200) {
            that.channelList = resp.data.data;
          } else {
            that.$message.error(resp.data.msg);
          }
        });
      },
      handleSizeChange: function(size) {
        this.pageSize = size
      },
      handleCurrentChange: function(currentPage) {
        this.currentPage = currentPage
      },
      handleSelectionChange(val) {
        this.multipleSelection = [];
       val.forEach((element) => {
          this.multipleSelection.push(element.id);
       });
   //    console.log(this.multipleSelection);
      },
      enableFormatter(row){
        switch (row.enable) {
          case 0:
            return this.$t('m.channelEnable');
          case 1:
            return this.$t('m.channelDisEnable');

        }

      },
      currentSel(selVal) {
              this.search = selVal.id;
              console.log("search:"+this.search+"  search:"+selVal)
             },
      channelEdit(row){
        this.$router.push({
          name: 'channelDetail',
          params: {
            id: row.id
          }
        });
      },
      channelDel(row){
        let  that = this;
        deleteChannel(row.id).then((resp) => {
          let code = resp.data.code;
          console.log(resp.data);
          if (code === 200) {
            that.$message.success(resp.data.data);
            that.getChannelList();
          } else {
            that.$message.error(resp.data.data);
          }
      })
      },

      addChannel(){
        this.$router.push({
          name: 'channelDetail',
          params: {
            id: ''
          }
        });
      },
      beatchDel(){
        console.log(this.multipleSelection);
        let  that = this;
        if( this.multipleSelection !==undefined && this.multipleSelection.length > 0 ){

          deleteChannel(this.multipleSelection).then((resp) => {
            let code = resp.data.code;
            console.log(resp.data);
            if (code === 200) {
              that.$message.success(resp.data.data);
              that.getChannelList();
            } else {
              that.$message.error(resp.data.data);
            }
          })
        }else{
          this.$notify.warning({
            title: 'warning',
            message: '请选择要删除分机',
            showClose: false
          });
        }

      }


    },
    computed:{
      domainList () {
        return this.$store.state.appConfigList; // 获取 state 中的 domainList
      }
    },
    mounted() {
      setTimeout(() => {
        this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop;
      },100)
      //此处需要通过延迟方法来设置值，不然会出现值已更新，但页面没更新的问题
      //this.$refs.table.$el.offsetTop：表格距离浏览器的高度
      this.getChannelList();
      this.$store.dispatch('getAllAppConfig'); // 直接调用 store/index.JS 中的方法
    }
  };

</script>

<style scoped>

</style>
