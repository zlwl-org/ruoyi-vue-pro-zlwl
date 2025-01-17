import request from '@/utils/request'

// 创建商品分类
export function createCategory(data) {
  return request({
    url: '/shop/category/create',
    method: 'post',
    data: data
  })
}

// 更新商品分类
export function updateCategory(data) {
  return request({
    url: '/shop/category/update',
    method: 'put',
    data: data
  })
}

// 删除商品分类
export function deleteCategory(id) {
  return request({
    url: '/shop/category/delete?id=' + id,
    method: 'delete'
  })
}

// 获得商品分类
export function getCategory(id) {
  return request({
    url: '/shop/category/get?id=' + id,
    method: 'get'
  })
}

// 获得商品分类分页
export function getCategoryPage(query) {
  return request({
    url: '/shop/category/page',
    method: 'get',
    params: query
  })
}

// 导出商品分类 Excel
export function exportCategoryExcel(query) {
  return request({
    url: '/shop/category/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
