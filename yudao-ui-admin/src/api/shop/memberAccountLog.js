import request from '@/utils/request'

// 创建会员账户流水
export function createMemberAccountLog(data) {
  return request({
    url: '/shop/member-account-log/create',
    method: 'post',
    data: data
  })
}

// 更新会员账户流水
export function updateMemberAccountLog(data) {
  return request({
    url: '/shop/member-account-log/update',
    method: 'put',
    data: data
  })
}

// 删除会员账户流水
export function deleteMemberAccountLog(id) {
  return request({
    url: '/shop/member-account-log/delete?id=' + id,
    method: 'delete'
  })
}

// 获得会员账户流水
export function getMemberAccountLog(id) {
  return request({
    url: '/shop/member-account-log/get?id=' + id,
    method: 'get'
  })
}

// 获得会员账户流水分页
export function getMemberAccountLogPage(query) {
  return request({
    url: '/shop/member-account-log/page',
    method: 'get',
    params: query
  })
}

// 导出会员账户流水 Excel
export function exportMemberAccountLogExcel(query) {
  return request({
    url: '/shop/member-account-log/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
