import request from '@/utils/request'

// 创建用户钱包
export function createUserWallet(data) {
  return request({
    url: '/blockchain/user-wallet/create',
    method: 'post',
    data: data
  })
}

// 导入用户钱包
export function importUserWallet(data) {
  return request({
    url: '/blockchain/user-wallet/import',
    method: 'post',
    data: data
  })
}

// 更新用户钱包
export function updateUserWallet(data) {
  return request({
    url: '/blockchain/user-wallet/update',
    method: 'put',
    data: data
  })
}

// 删除用户钱包
export function deleteUserWallet(id) {
  return request({
    url: '/blockchain/user-wallet/delete?id=' + id,
    method: 'delete'
  })
}

// 获得用户钱包
export function getUserWallet(id) {
  return request({
    url: '/blockchain/user-wallet/get?id=' + id,
    method: 'get'
  })
}

// 获得用户钱包分页
export function getUserWalletPage(query) {
  return request({
    url: '/blockchain/user-wallet/page',
    method: 'get',
    params: query
  })
}

// 导出用户钱包 Excel
export function exportUserWalletExcel(query) {
  return request({
    url: '/blockchain/user-wallet/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
