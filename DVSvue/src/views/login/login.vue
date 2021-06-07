<template>

    <body id="login-page">
      <div class="selectStyle">
        <el-select popper-class="selectFrom" v-model="value" placeholder="请选择"
                   :popper-append-to-body="false"
                   style="position: absolute;
                                    right: 0;
                                    top: 0;
                                    width: 15%;
                                    background-color: #ffffff9c !important;"
                   @change="languageChanged" >
          <el-option style="background-color: #889aa4"
                                                 v-for="item in language"
                                                 :key="item.label"
                                                 :label="item.txt"
                                                 :value="item.label">
          </el-option>
        </el-select>
      </div>
        <el-form class="login-container" label-position="left" label-width="0px">
            <h3 class="login-title">{{$t('m.navTitle')}}</h3>
            <el-form-item>
                <el-input type="text" v-model="loginForm.loginName" prefix-icon="el-icon-user" auto-complete="off" :placeholder="$t('m.logininputuser')">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-input type="password" v-model="loginForm.password" prefix-icon="el-icon-unlock" auto-complete="off"  :placeholder="$t('m.logininputpass')"></el-input>
            </el-form-item>
            <el-form-item style="width: 100%;margin-bottom: 0px">
                <el-button type="primary" style="width: 100%;  border: none" @click='login'>{{$t('m.loginlogin')}}</el-button>
            </el-form-item>
          <div class="smallButton">
            <span style="width: 80px;line-height: 40px;align-content: center;font-size: medium;margin-left: 5px">{{$t('m.loginautologin')}}</span>
              <el-switch v-model="rememberThePassword" style="width: 50px;line-height: 40px;align-content: center;margin-top: 10px"></el-switch>
            <span class="downloadPlug">{{$t('m.logindownload')}}</span>
          </div>
        </el-form>
    </body>
</template>

<script>
  import { userLogin } from "@/api/account";
  export default {
    name: 'Login',
    data() {
      return {
        language:[
          {
            txt: this.$t('m.loginchina'),
            label: 'zh-CN'
          },
          {
            txt: this.$t('m.loginenglish'),
            label: 'en-US'
          },
          {
            txt: this.$t('m.loginJapan'),
            label: 'ja-JP'
          },
        ],
        value:this.$t('m.loginchina'),
        rememberThePassword:true,
        loginForm: {
          loginName: '',
          password: ''
        },
        title: '',
        responseResult: []
      }
    },
    methods: {
      login() {
        var _this = this;
        userLogin({
          name: this.loginForm.loginName,
          password: this.loginForm.password,
        }).then((resp) => {
          let code = resp.data.code;
          if (code === 200) {
            let data = resp.data.data;
            let token = data.rights;
            let user = data;
            //存储token
            _this.$store.commit('SET_TOKENN', token);
            //存储user，优雅一点的做法是token和user分开获取
            _this.$store.commit('SET_USER', user);
            console.log(_this.$store.state.token)
            this.$parent.showNav = true;
            var path = this.$route.query.redirect
            this.$router.replace({ path: path === '/sys' || path === undefined ? '/sys' : path })
          } else {
            this.$message.error(resp.data.message);
          }
        });
      },
      languageChanged(value){
        let self_current_lang = value;
        if (self_current_lang) {
          // 切换系统语言
          this.$i18n.locale = self_current_lang
          // 将新语言保存到全局状态管理
          this.$store.dispatch('update_current_lang', self_current_lang)
          // 关闭语言询问框
          this.chooseLangDialogVisible = false
          // 成功提示
          this.$message.success('系统语言切换成功！')
        } else {
          this.$message.warning('没有你想要的语言吗，选一个吧！')
        }
        this.language = [
          {
            txt: this.$t('m.loginchina'),
            label: 'zh-CN'
          },
          {
            txt: this.$t('m.loginenglish'),
            label: 'en-US'
          },
          {
            txt: this.$t('m.loginJapan'),
            label: 'ja-JP'
          }
        ];
      }
    },
  };
</script>

<style scoped>
    #login-page {
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }

    body {
        margin: 0px;
      background-image:url(../../assets/wallhaven-5dyk37.jpg);
      background-repeat:no-repeat;
      background-size:100% 100%;
      -moz-background-size:100% 100%;
    }

    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 160px auto;
        width: 380px;
        padding: 35px 35px 15px 35px;
        background: #ffffff6b;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 5px #cac6c6;
    }

    .login-container>>>.el-input__inner{
      padding: 12px 5px 12px 30px;
    }

    .login-title {
        font-size: xx-large;
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }
    .smallButton{
      height: 40px;
      width: 100%;
      margin-bottom: 20px;
      display: inline-flex;
      margin-left: 10px;
    }
    .smallButton>>>.el-form-item__label{
      width: 70px !important;
    }
    .smallButton>>>.el-form-item{
      height: 30px;
    }
    .selectStyle>>>.el-input__inner{
      background-color: rgba(255, 255, 255, 0.61) !important;
    }
    .selectStyle {
    /deep/ input::-webkit-input-placeholder {
      color: #fff !important;
    }
    /deep/  input::-ms-input-placeholder {
      color: #fff !important;
      }
    }
.downloadPlug{
  height: 40px;
  text-align: center;
  line-height: 40px;
  font-size: 15px;
  margin-left: 80px;
}
</style>

<style lang="less" scoped>


  /deep/.selectFrom {
    min-width: 100px !important;
    right: 0 !important;
    //top: 80px !important;
    background-color: #ffffff26 !important;
    border: 1px solid #c8c5cc21 !important;

    .el-select-dropdown__list {
      text-align: center;
      background-color: #ffffff26 !important;
    }
    .el-select-dropdown__item {
      color: #000000;
      background: rgba(255, 255, 255, 0.61) !important;
    }
    .el-select-dropdown__item.hover,
    .el-select-dropdown__item:hover {
      background: rgba(255, 255, 255, 0.61) !important;
    }
  /deep/.el-select .el-input{
    border-color: #889aa4 !important;
  }
  .popper__arrow{
    left: 110px !important;
  }
  }
  </style>
