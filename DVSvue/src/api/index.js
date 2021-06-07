import request from '@/utils/request'

export  function initialize() {
  return request({
    url:'/init/initialize',
    method:'POST'
  })

}
export  function initializeDepartment() {
  return request({
    url:'/init/initializeDepartment',
    method:'POST'
  })
}
export  function saveAppConfig(data) {
  return request({
    url:'/config/saveAppConfig',
    method:'POST',
    data
  })
}

//操作日志
export function getOperatePage(data) {

  return request({
    url:'/operaterlog/getByPage',
    method:'POST',
    data
  })

}


