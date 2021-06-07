import request from '@/utils/request'

export function userLogin(data) {
    return request({
        url: '/account/login',
        method: 'post',
        data
    })
}
export  function GetAccountList(data) {
  return request({
    url: '/account/list',
    method: 'post',
    data
  })
}
export  function accountTree(data) {
  return request({
    url:"/right/accountTree",
    method:'post',
    data
  })
}
export  function accountAdd(data) {
  return request({
    url: '/account/add',
    method: 'post',
    data
  })
}
export  function getAccountByIds(data) {
  return request({
    url:'/account/getById?id='+data,
    method:'post',
  })

}
export function accountUpdate(data) {
  return request({
    url: '/account/update',
    method: 'post',
    data
  })
}
export  function  deleteAccount(data) {
  return request({
    url: '/account/delete?id='+data,
    method: 'post',
  })
}
export  function  deleteAccounts(data) {
  return request({
    url: '/account/deletes?ids='+data,
    method: 'post',
  })
}
export  function departmentTree() {
  return request({
    url: '/account/departmentTree',
    method: 'post',
  })
}
export  function getMgr(id,accountProperty) {
  return request({
    url: '/account/getMgr?id='+id+"&accountProperty="+accountProperty,
    method: 'post',
  })
}
export  function listProperty(ids,accountProperty) {
  return request({
    url: '/account/getAgentAndExtByDepartments?ids='+ids+'&accountProperty='+accountProperty,
    method: 'post',
  })
}
export  function saveMgr(managerId,agentIds) {
  return request({
    url: '/account/saveMgr?id='+managerId+'&agentIds='+agentIds,
    method: 'post',
  })
}

