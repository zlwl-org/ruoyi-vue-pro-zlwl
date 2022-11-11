import request from '@/utils/request'

// 创建会员充值套餐
export function createRecharge(data) {
  return request({
    url: '/shop/recharge/create',
    method: 'post',
    data: data
  })
}

// 更新会员充值套餐
export function updateRecharge(data) {
  return request({
    url: '/shop/recharge/update',
    method: 'put',
    data: data
  })
}

// 删除会员充值套餐
export function deleteRecharge(id) {
  return request({
    url: '/shop/recharge/delete?id=' + id,
    method: 'delete'
  })
}

// 获得会员充值套餐
export function getRecharge(id) {
  return request({
    url: '/shop/recharge/get?id=' + id,
    method: 'get'
  })
}

// 获得会员充值套餐分页
export function getRechargePage(query) {
  return request({
    url: '/shop/recharge/page',
    method: 'get',
    params: query
  })
}

// 导出会员充值套餐 Excel
export function exportRechargeExcel(query) {
  return request({
    url: '/shop/recharge/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
