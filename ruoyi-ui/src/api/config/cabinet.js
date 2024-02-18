import request from '@/utils/request'

// 查询柜管理列表
export function listCabinet(query) {
  return request({
    url: '/config/cabinet/list',
    method: 'get',
    params: query
  })
}

// 查询柜管理详细
export function getCabinet(id) {
  return request({
    url: '/config/cabinet/' + id,
    method: 'get'
  })
}

// 新增柜管理
export function addCabinet(data) {
  return request({
    url: '/config/cabinet',
    method: 'post',
    data: data
  })
}

// 修改柜管理
export function updateCabinet(data) {
  return request({
    url: '/config/cabinet',
    method: 'put',
    data: data
  })
}

// 删除柜管理
export function delCabinet(id) {
  return request({
    url: '/config/cabinet/' + id,
    method: 'delete'
  })
}

export function selectDetailByControlId(id){
  return request({
    url:'/config/barrier/getByControCabinetId/'+id,
    methods:'get',

  })
}

export function selectDetailByCommunicationId(id){
  return request({
    url:'/config/barrier/getByCommunicationCabinetId/'+id,
    methods:'get',

  })
}
export function selectErrorsByCabinetId(id){
  return request({
    url:'/maintenance/log/selectErrorsByCabinetId/'+id,
    method:'get'

  })
}
