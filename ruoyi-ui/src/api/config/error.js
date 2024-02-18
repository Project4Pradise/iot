import request from '@/utils/request'

// 查询错误管理列表
export function listError(query) {
  return request({
    url: '/config/error/list',
    method: 'get',
    params: query
  })
}

// 查询错误管理详细
export function getError(id) {
  return request({
    url: '/config/error/' + id,
    method: 'get'
  })
}

// 新增错误管理
export function addError(data) {
  return request({
    url: '/config/error',
    method: 'post',
    data: data
  })
}

// 修改错误管理
export function updateError(data) {
  return request({
    url: '/config/error',
    method: 'put',
    data: data
  })
}

// 删除错误管理
export function delError(id) {
  return request({
    url: '/config/error/' + id,
    method: 'delete'
  })
}
