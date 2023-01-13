// 获取门店精简信息列表
import request from '@/utils/request'

export function getShopData() {
  return request({
    url: '/shop/data/get',
    method: 'get'
  })
}
