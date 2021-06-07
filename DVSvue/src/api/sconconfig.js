import request from '@/utils/request'

export  function scconfigList(data) {
  return request({
    url: '/scconfig/list',
    method: 'post',
    data
  })
}
