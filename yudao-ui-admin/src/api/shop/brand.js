import request from '@/utils/request'

// 创建商品品牌
export function createBrand(data) {
  return request({
    url: '/shop/brand/create',
    method: 'post',
    data: data
  })
}

// 更新商品品牌
export function updateBrand(data) {
  return request({
    url: '/shop/brand/update',
    method: 'put',
    data: data
  })
}

// 删除商品品牌
export function deleteBrand(id) {
  return request({
    url: '/shop/brand/delete?id=' + id,
    method: 'delete'
  })
}

// 获得商品品牌
export function getBrand(id) {
  return request({
    url: '/shop/brand/get?id=' + id,
    method: 'get'
  })
}

// 获得商品品牌分页
export function getBrandPage(query) {
  return request({
    url: '/shop/brand/page',
    method: 'get',
    params: query
  })
}

// 导出商品品牌 Excel
export function exportBrandExcel(query) {
  return request({
    url: '/shop/brand/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
