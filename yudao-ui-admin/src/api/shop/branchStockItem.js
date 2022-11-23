import request from '@/utils/request'

// 创建门店出入库明细
export function createBranchStockItem(data) {
  return request({
    url: '/shop/branch-stock-item/create',
    method: 'post',
    data: data
  })
}

// 更新门店出入库明细
export function updateBranchStockItem(data) {
  return request({
    url: '/shop/branch-stock-item/update',
    method: 'put',
    data: data
  })
}

// 删除门店出入库明细
export function deleteBranchStockItem(id) {
  return request({
    url: '/shop/branch-stock-item/delete?id=' + id,
    method: 'delete'
  })
}

// 获得门店出入库明细
export function getBranchStockItem(id) {
  return request({
    url: '/shop/branch-stock-item/get?id=' + id,
    method: 'get'
  })
}

// 获得门店出入库明细分页
export function getBranchStockItemPage(query) {
  return request({
    url: '/shop/branch-stock-item/page',
    method: 'get',
    params: query
  })
}

// 导出门店出入库明细 Excel
export function exportBranchStockItemExcel(query) {
  return request({
    url: '/shop/branch-stock-item/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
