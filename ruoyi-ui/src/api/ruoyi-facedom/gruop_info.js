import request from '@/utils/request'

// 查询管理用户组列表
export function listGruop_info(query) {
  return request({
    url: '/ruoyi-facedom/gruop_info/list',
    method: 'get',
    params: query
  })
}

// 查询管理用户组详细
export function getGruop_info(id) {
  return request({
    url: '/ruoyi-facedom/gruop_info/' + id,
    method: 'get'
  })
}

// 新增管理用户组
export function addGruop_info(data) {
  return request({
    url: '/ruoyi-facedom/gruop_info',
    method: 'post',
    data: data
  })
}

// 修改管理用户组
export function updateGruop_info(data) {
  return request({
    url: '/ruoyi-facedom/gruop_info',
    method: 'put',
    data: data
  })
}

// 删除管理用户组
export function delGruop_info(id) {
  return request({
    url: '/ruoyi-facedom/gruop_info/' + id,
    method: 'delete'
  })
}
