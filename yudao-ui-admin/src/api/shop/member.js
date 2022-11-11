import request from '@/utils/request'

// 创建会员
export function createMember(data) {
  return request({
    url: '/shop/member/create',
    method: 'post',
    data: data
  })
}

// 更新会员
export function updateMember(data) {
  return request({
    url: '/shop/member/update',
    method: 'put',
    data: data
  })
}

// 删除会员
export function deleteMember(id) {
  return request({
    url: '/shop/member/delete?id=' + id,
    method: 'delete'
  })
}

// 获得会员
export function getMember(id) {
  return request({
    url: '/shop/member/get?id=' + id,
    method: 'get'
  })
}

// 获得会员分页
export function getMemberPage(query) {
  return request({
    url: '/shop/member/page',
    method: 'get',
    params: query
  })
}

// 导出会员 Excel
export function exportMemberExcel(query) {
  return request({
    url: '/shop/member/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
