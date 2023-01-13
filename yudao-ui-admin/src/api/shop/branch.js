import request from '@/utils/request'

// 创建门店
export function createBranch(data) {
  return request({
    url: '/shop/branch/create',
    method: 'post',
    data: data
  })
}

// 更新门店
export function updateBranch(data) {
  return request({
    url: '/shop/branch/update',
    method: 'put',
    data: data
  })
}

// 删除门店
export function deleteBranch(id) {
  return request({
    url: '/shop/branch/delete?id=' + id,
    method: 'delete'
  })
}

// 获得门店
export function getBranch(id) {
  return request({
    url: '/shop/branch/get?id=' + id,
    method: 'get'
  })
}

// 获得门店分页
export function getBranchPage(query) {
  return request({
    url: '/shop/branch/page',
    method: 'get',
    params: query
  })
}

// 导出门店 Excel
export function exportBranchExcel(query) {
  return request({
    url: '/shop/branch/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 获取门店精简信息列表
export function listSimpleBranches() {
  return request({
    url: '/shop/branch/list-all-simple',
    method: 'get'
  })
}
