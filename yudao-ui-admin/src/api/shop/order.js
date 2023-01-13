import request from '@/utils/request'

// 创建门店订单
export function createOrder(data) {
  return request({
    url: '/shop/order/create',
    method: 'post',
    data: data
  })
}

// 更新门店订单
export function updateOrder(data) {
  return request({
    url: '/shop/order/update',
    method: 'put',
    data: data
  })
}

// 删除门店订单
export function deleteOrder(id) {
  return request({
    url: '/shop/order/delete?id=' + id,
    method: 'delete'
  })
}

// 获得门店订单
export function getOrder(id) {
  return request({
    url: '/shop/order/get?id=' + id,
    method: 'get'
  })
}

// 获得门店订单分页
export function getOrderPage(query) {
  return request({
    url: '/shop/order/page',
    method: 'get',
    params: query
  })
}

// 导出门店订单 Excel
export function exportOrderExcel(query) {
  return request({
    url: '/shop/order/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 支付订单
export function payOrder(data) {
  return request({
    url: '/shop/order/pay',
    method: 'put',
    data: data
  })
}

// 取消订单
export function cancelOrder(id) {
  return request({
    url: '/shop/order/cancel?id=' + id,
    method: 'put',
  })
}

// 更换订单类型
export function changeOrder(id) {
  return request({
    url: '/shop/order/change?id=' + id,
    method: 'put',
  })
}
