import request from '@/utils/request'

// 创建以太坊主网地址
export function createEthMainNetAddress(data) {
  return request({
    url: '/blockchain/eth-main-net-address/create',
    method: 'post',
    data: data
  })
}

// 更新以太坊主网地址
export function updateEthMainNetAddress(data) {
  return request({
    url: '/blockchain/eth-main-net-address/update',
    method: 'put',
    data: data
  })
}

// 删除以太坊主网地址
export function deleteEthMainNetAddress(id) {
  return request({
    url: '/blockchain/eth-main-net-address/delete?id=' + id,
    method: 'delete'
  })
}

// 获得以太坊主网地址
export function getEthMainNetAddress(id) {
  return request({
    url: '/blockchain/eth-main-net-address/get?id=' + id,
    method: 'get'
  })
}

// 获得以太坊主网地址分页
export function getEthMainNetAddressPage(query) {
  return request({
    url: '/blockchain/eth-main-net-address/page',
    method: 'get',
    params: query
  })
}

// 导出以太坊主网地址 Excel
export function exportEthMainNetAddressExcel(query) {
  return request({
    url: '/blockchain/eth-main-net-address/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
