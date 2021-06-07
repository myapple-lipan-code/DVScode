import request from '@/utils/request'


export function channelList(data) {
  return request({
    url: '/channel/getListByPage',
    method: 'post'
  })

}
export function getChannelById(data) {
  return request({
    url: '/channel/get?id='+data,
    method: 'post',
    data
  })
}
export  function addChannel(data) {
  return request({
    url:'/channel/add',
    method:'post',
    data
  })

}
export function updateChannel(data) {
  return request({
    url:'/channel/update',
    method :'post',
    data

  })
}
//分机的删除
export function deleteChannel(data) {
  return request({
    url:'/channel/delete?id='+data,
    method:'post',
  })

}
export function deleteChannels(data) {
  return request({
    url:'/channel/deletes?ids='+data,
    method:'post',
  })
}


