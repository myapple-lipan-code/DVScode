<template>
  <div class="main-conent main-conent-screen main-conent-bgFFF main-conent-borderradius">
    <BreadcrumbComponent :dataList="appData"></BreadcrumbComponent>
    <div class="screen-header" >
      <el-row >
        <el-col :span="6">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="addChannel">新增</el-button>

          <el-button   @click="beatchDel"
                     icon="el-icon-delete"
                     size="mini"
                     type="danger">批量删除</el-button>
        </el-col>
        <el-col :span="18" class="text-right">
          <el-input
            class="margin-l10"
            style="width:200px;"
            placeholder="请输入搜索内容"
            v-model="search">
            <i slot="suffix" class="el-input__icon el-icon-search cursor-pointer"></i>
          </el-input>
          <el-button icon="el-icon-circle-check" class="margin-l10" >高级搜索</el-button>
        </el-col>
      </el-row>
    </div>

    <screen-table
      class="screen-conent"
      table-class="custom-table"
      header-row-class-name="custom-table-header"
      :data="channelList.slice((currentPage-1)*pageSize,currentPage*pageSize)
    .filter(data => !search || data.extension.toLowerCase().includes(search.toLowerCase()))"   @selection-change="handleSelectionChange">
      <el-table-column
        fixed
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column prop="extension" label="分机号" width="90" sortable>
      </el-table-column>
      <el-table-column prop="ctiAppIPAddr" label="CTI程序IP" width="100"  sortable>
      </el-table-column>
      <el-table-column prop="computer" label="录音客户端IP" width="120" sortable>
      </el-table-column>
      <el-table-column prop="channelType" label="通道类型" width="100" sortable >
      </el-table-column>
      <el-table-column prop="enable" label="是否启用" min-width="120" sortable :formatter="enableFormatter">
      </el-table-column>
      <el-table-column prop="cardType" label="录音方案" min-width="120" sortable>
      </el-table-column>
      <el-table-column prop="chNoInCard" label="通道号" min-width="100" sortable >
      </el-table-column>
      <el-table-column prop="channelIPAddr" label="分机IP" min-width="100" sortable >
      </el-table-column>
      <el-table-column prop="channelVirtualExt" label="虚拟分机" min-width="100" sortable >
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="160">
        <template slot-scope="scope">
          <el-button  size="mini" type="primary" icon="el-icon-edit" @click.native="channelEdit(scope.row)"/>
          <el-button  type="danger" icon="el-icon-delete" size="mini" @click.native="channelDel(scope.row)" />
<!--          <el-dropdown class="margin-l5">-->
<!--                                <span class="cursor-pointer">-->
<!--                                    <el-button type="text" >更多<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></el-button>-->
<!--                                </span>-->
<!--            <el-dropdown-menu slot="dropdown">-->
<!--              <el-dropdown-item @click.native="channelEdit(scope.row)">编辑</el-dropdown-item>-->
<!--              <el-dropdown-item @click.native="channelDel(scope.row)">删除</el-dropdown-item>-->
<!--            </el-dropdown-menu>-->
<!--          </el-dropdown>-->
        </template>
      </el-table-column>
    </screen-table>
    <div class="screen-footer text-right">
      <el-pagination
        background
        layout="sizes, prev, pager, next, total"
        :page-sizes="[10, 20, 50, 100]"
        @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="currentPage" :page-size="pageSize"
        :total="channelList.length">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import ScreenTable from '../../components/ScreenTable';
  import { channelList ,deleteChannel,deleteChannels} from "@/api/channel";
  import BreadcrumbComponent from '../../components/common/BreadcrumbComponent'
  export default {
    components:{
      BreadcrumbComponent,
      ScreenTable
    },
    data() {
      return {

        loading: false,
        search:"",
        channelList: [],
        currentPage: 1,
        pageSize: 20,
        input1: '',
        svalue2: '',
        searchDrawer: false,
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
        multipleSelection:[],

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

          deleteChannels(this.multipleSelection).then((resp) => {
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

      },
      handleClick(row){

      }


    },
    computed:{
      domainList () {
        return this.$store.state.appConfigList; // 获取 state 中的 domainList
      },
      appData:function () {
        return {title:this.$t('m.navExt')};
      }
    },
    mounted() {
      // setTimeout(() => {
      //   this.tableHeight = window.innerHeight - this.$refs.table.$el.offsetTop;
      // },100)
      //此处需要通过延迟方法来设置值，不然会出现值已更新，但页面没更新的问题
      //this.$refs.table.$el.offsetTop：表格距离浏览器的高度
      this.getChannelList();
      this.$store.dispatch('getAllAppConfig'); // 直接调用 store/index.JS 中的方法
    }
  };

</script>


