import request from '@/utils/request'

// 查询结算账户列表
export function listSettlement(query) {
  return request({
    url: '/data/settlement/list',
    method: 'get',
    params: query
  })
}

// 查询结算账户详细
export function getSettlement(id) {
  return request({
    url: '/data/settlement/' + id,
    method: 'get'
  })
}

// 新增结算账户
export function addSettlement(data) {
  return request({
    url: '/data/settlement',
    method: 'post',
    data: data
  })
}

// 修改结算账户
export function updateSettlement(data) {
  return request({
    url: '/data/settlement',
    method: 'put',
    data: data
  })
}

// 删除结算账户
export function delSettlement(id) {
  return request({
    url: '/data/settlement/' + id,
    method: 'delete'
  })
}
