import request from '@/utils/request'

// 查询付快但列表
export function listDoc(query) {
  return request({
    url: '/eims/doc/list',
    method: 'get',
    params: query
  })
}

// 查询付快但详细
export function getDoc(id) {
  return request({
    url: '/eims/doc/' + id,
    method: 'get'
  })
}

// 新增付快但
export function addDoc(data) {
  return request({
    url: '/eims/doc',
    method: 'post',
    data: data
  })
}

// 修改付快但
export function updateDoc(data) {
  return request({
    url: '/eims/doc',
    method: 'put',
    data: data
  })
}

// 删除付快但
export function delDoc(id) {
  return request({
    url: '/eims/doc/' + id,
    method: 'delete'
  })
}
