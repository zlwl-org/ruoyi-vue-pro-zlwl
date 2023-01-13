import request from '@/utils/request'

// 创建促销活动
export function createPromotion(data) {
  return request({
    url: '/shop/promotion/create',
    method: 'post',
    data: data
  })
}

// 更新促销活动
export function updatePromotion(data) {
  return request({
    url: '/shop/promotion/update',
    method: 'put',
    data: data
  })
}

// 删除促销活动
export function deletePromotion(id) {
  return request({
    url: '/shop/promotion/delete?id=' + id,
    method: 'delete'
  })
}

// 获得促销活动
export function getPromotion(id) {
  return request({
    url: '/shop/promotion/get?id=' + id,
    method: 'get'
  })
}

// 获得促销活动分页
export function getPromotionPage(query) {
  return request({
    url: '/shop/promotion/page',
    method: 'get',
    params: query
  })
}

// 导出促销活动 Excel
export function exportPromotionExcel(query) {
  return request({
    url: '/shop/promotion/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
