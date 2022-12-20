import request from '@/utils/request'

// 创建网络代理
export function createProxy(data) {
  return request({
    url: '/infra/proxy/create',
    method: 'post',
    data: data
  })
}

// 更新网络代理
export function updateProxy(data) {
  return request({
    url: '/infra/proxy/update',
    method: 'put',
    data: data
  })
}

// 删除网络代理
export function deleteProxy(id) {
  return request({
    url: '/infra/proxy/delete?id=' + id,
    method: 'delete'
  })
}

// 获得网络代理
export function getProxy(id) {
  return request({
    url: '/infra/proxy/get?id=' + id,
    method: 'get'
  })
}

// 获得网络代理分页
export function getProxyPage(query) {
  return request({
    url: '/infra/proxy/page',
    method: 'get',
    params: query
  })
}

// 导出网络代理 Excel
export function exportProxyExcel(query) {
  return request({
    url: '/infra/proxy/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
