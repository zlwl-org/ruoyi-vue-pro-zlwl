import request from '@/utils/request'

// 创建门店商品
export function createBranchGoods(data) {
  return request({
    url: '/shop/branch-goods/create',
    method: 'post',
    data: data
  })
}

// 更新门店商品
export function updateBranchGoods(data) {
  return request({
    url: '/shop/branch-goods/update',
    method: 'put',
    data: data
  })
}

// 删除门店商品
export function deleteBranchGoods(id) {
  return request({
    url: '/shop/branch-goods/delete?id=' + id,
    method: 'delete'
  })
}

// 获得门店商品
export function getBranchGoods(id) {
  return request({
    url: '/shop/branch-goods/get?id=' + id,
    method: 'get'
  })
}

// 获得门店商品分页
export function getBranchGoodsPage(query) {
  return request({
    url: '/shop/branch-goods/page',
    method: 'get',
    params: query
  })
}

// 导出门店商品 Excel
export function exportBranchGoodsExcel(query) {
  return request({
    url: '/shop/branch-goods/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
