import request from '@/utils/request'

// 创建充值订单
export function createRechargeOrder(data) {
  return request({
    url: '/shop/recharge-order/create',
    method: 'post',
    data: data
  })
}

// 更新充值订单
export function updateRechargeOrder(data) {
  return request({
    url: '/shop/recharge-order/update',
    method: 'put',
    data: data
  })
}

// 删除充值订单
export function deleteRechargeOrder(id) {
  return request({
    url: '/shop/recharge-order/delete?id=' + id,
    method: 'delete'
  })
}

// 获得充值订单
export function getRechargeOrder(id) {
  return request({
    url: '/shop/recharge-order/get?id=' + id,
    method: 'get'
  })
}

// 获得充值订单分页
export function getRechargeOrderPage(query) {
  return request({
    url: '/shop/recharge-order/page',
    method: 'get',
    params: query
  })
}

// 导出充值订单 Excel
export function exportRechargeOrderExcel(query) {
  return request({
    url: '/shop/recharge-order/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
