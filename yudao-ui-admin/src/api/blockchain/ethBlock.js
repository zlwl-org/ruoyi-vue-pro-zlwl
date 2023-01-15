import request from '@/utils/request'

// 创建Eth区块数据
export function createEthBlock(data) {
  return request({
    url: '/blockchain/eth-block/create',
    method: 'post',
    data: data
  })
}

// 更新Eth区块数据
export function updateEthBlock(data) {
  return request({
    url: '/blockchain/eth-block/update',
    method: 'put',
    data: data
  })
}

// 删除Eth区块数据
export function deleteEthBlock(id) {
  return request({
    url: '/blockchain/eth-block/delete?id=' + id,
    method: 'delete'
  })
}

// 获得Eth区块数据
export function getEthBlock(id) {
  return request({
    url: '/blockchain/eth-block/get?id=' + id,
    method: 'get'
  })
}

// 获得Eth区块数据分页
export function getEthBlockPage(query) {
  return request({
    url: '/blockchain/eth-block/page',
    method: 'get',
    params: query
  })
}

// 导出Eth区块数据 Excel
export function exportEthBlockExcel(query) {
  return request({
    url: '/blockchain/eth-block/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
