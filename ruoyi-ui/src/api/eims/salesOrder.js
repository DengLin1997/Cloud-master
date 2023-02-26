import request from '@/utils/request'

// 查询销售订单列表
export function listSalesOrder(query) {
  return request({
    url: '/eims/SalesOrder/list',
    method: 'get',
    params: query
  })
}

// 查询销售订单详细
export function getSalesOrder(id) {
  return request({
    url: '/eims/SalesOrder/' + id,
    method: 'get'
  })
}

// 新增销售订单
export function addSalesOrder(data) {
  return request({
    url: '/eims/SalesOrder',
    method: 'post',
    data: data
  })
}

// 修改销售订单
export function updateSalesOrder(data) {
  return request({
    url: '/eims/SalesOrder',
    method: 'put',
    data: data
  })
}

// 删除销售订单
export function delSalesOrder(id) {
  return request({
    url: '/eims/SalesOrder/' + id,
    method: 'delete'
  })
}
