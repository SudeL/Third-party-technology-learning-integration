import request from '@/utils/request'

// 查询用户管理列表
export function listUser_info(query) {
  return request({
    url: '/ruoyi-facedom/user_info/list',
    method: 'get',
    params: query
  })
}

// 查询用户管理详细
export function getUser_info(id) {
  return request({
    url: '/ruoyi-facedom/user_info/' + id,
    method: 'get'
  })
}

// 新增用户管理
export function addUser_info(data) {
  return request({
    url: '/ruoyi-facedom/user_info',
    method: 'post',
    data: data
  })
}

// 修改用户管理
export function updateUser_info(data) {
  return request({
    url: '/ruoyi-facedom/user_info',
    method: 'put',
    data: data
  })
}

// 删除用户管理
export function delUser_info(id) {
  return request({
    url: '/ruoyi-facedom/user_info/' + id,
    method: 'delete'
  })
}
