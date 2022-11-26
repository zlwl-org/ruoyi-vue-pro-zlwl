import request from '@/utils/request'

// 创建门店订单明细
export function createOrderItem(data) {
  return request({
    url: '/shop/order-item/create',
    method: 'post',
    data: data
  })
}

// 更新门店订单明细
export function updateOrderItem(data) {
  return request({
    url: '/shop/order-item/update',
    method: 'put',
    data: data
  })
}

// 删除门店订单明细
export function deleteOrderItem(id) {
  return request({
    url: '/shop/order-item/delete?id=' + id,
    method: 'delete'
  })
}

// 获得门店订单明细
export function getOrderItem(id) {
  return request({
    url: '/shop/order-item/get?id=' + id,
    method: 'get'
  })
}

// 获得门店订单明细分页
export function getOrderItemPage(query) {
  return request({
    url: '/shop/order-item/page',
    method: 'get',
    params: query
  })
}

// 导出门店订单明细 Excel
export function exportOrderItemExcel(query) {
  return request({
    url: '/shop/order-item/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
