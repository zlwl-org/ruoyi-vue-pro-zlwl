import request from '@/utils/request'

// 创建事件
export function createEvent(data) {
  return request({
    url: '/blockchain/event/create',
    method: 'post',
    data: data
  })
}

// 更新事件
export function updateEvent(data) {
  return request({
    url: '/blockchain/event/update',
    method: 'put',
    data: data
  })
}

// 删除事件
export function deleteEvent(id) {
  return request({
    url: '/blockchain/event/delete?id=' + id,
    method: 'delete'
  })
}

// 获得事件
export function getEvent(id) {
  return request({
    url: '/blockchain/event/get?id=' + id,
    method: 'get'
  })
}

// 获得事件分页
export function getEventPage(query) {
  return request({
    url: '/blockchain/event/page',
    method: 'get',
    params: query
  })
}

// 导出事件 Excel
export function exportEventExcel(query) {
  return request({
    url: '/blockchain/event/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
