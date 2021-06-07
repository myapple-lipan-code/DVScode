import Vue from 'vue'
import Vuex from 'vuex'
import * as common from '../api/index'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: sessionStorage.getItem("token"),
        user: JSON.parse(sessionStorage.getItem("user")),
        appConfigList:{},
        departmentList:{},
    },
    mutations: {
        // set
        SET_TOKENN: (state, token) => {
            state.token = token
            sessionStorage.setItem("token", token)
        },
        SET_USER: (state, user) => {
            state.user = user
            sessionStorage.setItem("user", JSON.stringify(user))
        },
        REMOVE_INFO : (state) => {
            state.token = ''
            state.user = {}
            sessionStorage.setItem("token", '')
            sessionStorage.setItem("user", JSON.stringify(''))
        },
      updateAppConfig (state, payload) {
        state.appConfigList = payload;
      },
      updateDepartment(state,payload){
        state.departmentList = payload;
      }
    },
    getters: {

    },
    actions: {
      getAllAppConfig(context) {
        common.initialize().then((response) => {
            context.commit('updateAppConfig', response.data);
        });
      },
      getAllDepartment(context){
        common.initializeDepartment().then((response) =>{
          context.commit('updateDepartment',response.data);
        });
      }
    },
    modules: {
    }
})
