import request from '@/utils/request'

// 创建门店出入库
export function createBranchStock(data) {
  return request({
    url: '/shop/branch-stock/create',
    method: 'post',
    data: data
  })
}

// 更新门店出入库
export function updateBranchStock(data) {
  return request({
    url: '/shop/branch-stock/update',
    method: 'put',
    data: data
  })
}

// 删除门店出入库
export function deleteBranchStock(id) {
  return request({
    url: '/shop/branch-stock/delete?id=' + id,
    method: 'delete'
  })
}

// 获得门店出入库
export function getBranchStock(id) {
  return request({
    url: '/shop/branch-stock/get?id=' + id,
    method: 'get'
  })
}

// 获得门店出入库分页
export function getBranchStockPage(query) {
  return request({
    url: '/shop/branch-stock/page',
    method: 'get',
    params: query
  })
}

// 导出门店出入库 Excel
export function exportBranchStockExcel(query) {
  return request({
    url: '/shop/branch-stock/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
