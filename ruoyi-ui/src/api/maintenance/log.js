import request from '@/utils/request'

// 查询错误日志列表
export function listLog(query) {
  return request({
    url: '/maintenance/log/list',
    method: 'get',
    params: query
  })
}

// 查询错误日志详细
export function getLog(id) {
  return request({
    url: '/maintenance/log/' + id,
    method: 'get'
  })
}

// 新增错误日志
export function addLog(data) {
  return request({
    url: '/maintenance/log',
    method: 'post',
    data: data
  })
}

// 修改错误日志
export function updateLog(data) {
  return request({
    url: '/maintenance/log',
    method: 'put',
    data: data
  })
}

// 删除错误日志
export function delLog(id) {
  return request({
    url: '/maintenance/log/' + id,
    method: 'delete'
  })
}
export function getErrorList(data){
  return request({
    url:'/config/error/listAll',
    method:'get',
    data:data
  })
}
