import request from '@/utils/request'

// 查询采购入库列表
export function listReceipt(query) {
  return request({
    url: '/eims/receipt/list',
    method: 'get',
    params: query
  })
}

// 查询采购入库详细
export function getReceipt(id) {
  return request({
    url: '/eims/receipt/' + id,
    method: 'get'
  })
}

// 新增采购入库
export function addReceipt(data) {
  return request({
    url: '/eims/receipt',
    method: 'post',
    data: data
  })
}

// 修改采购入库
export function updateReceipt(data) {
  return request({
    url: '/eims/receipt',
    method: 'put',
    data: data
  })
}

// 删除采购入库
export function delReceipt(id) {
  return request({
    url: '/eims/receipt/' + id,
    method: 'delete'
  })
}
