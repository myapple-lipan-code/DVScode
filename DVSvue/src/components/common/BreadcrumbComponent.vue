<template>
  <div class="panel-heading">
    <el-card class="box-card" shadow="hover">
    <h3>{{dataList.title}}</h3>
<!--    <el-divider  content-position="left"><h3>{{dataList.title}}</h3></el-divider>-->
    </el-card>
  </div>

</template>

<script>
    export default {
        name: "Breadcrumb",
        props: {
          dataList: {
            type: [Object],
          },
        },
    }
</script>

<style scoped>
  .panel-heading {
    padding: 10px 15px;
    text-align: left;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    color: #31708f;
    background-color: transparent;
    border-color: #bce8f1;

  }

</style>
