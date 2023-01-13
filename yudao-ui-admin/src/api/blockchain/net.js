import request from '@/utils/request'

// 创建网络
export function createNet(data) {
  return request({
    url: '/blockchain/net/create',
    method: 'post',
    data: data
  })
}

// 更新网络
export function updateNet(data) {
  return request({
    url: '/blockchain/net/update',
    method: 'put',
    data: data
  })
}

// 删除网络
export function deleteNet(id) {
  return request({
    url: '/blockchain/net/delete?id=' + id,
    method: 'delete'
  })
}

// 获得网络
export function getNet(id) {
  return request({
    url: '/blockchain/net/get?id=' + id,
    method: 'get'
  })
}

// 获得网络分页
export function getNetPage(query) {
  return request({
    url: '/blockchain/net/page',
    method: 'get',
    params: query
  })
}

// 导出网络 Excel
export function exportNetExcel(query) {
  return request({
    url: '/blockchain/net/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 获得网络分页
export function getNetSimple() {
  return request({
    url: '/blockchain/net/list-simple',
    method: 'get',
  })
}
