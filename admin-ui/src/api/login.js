import request from '@/utils/request'

const client_id = 'web'
const client_secret = '123456'
const scope = 'server'

// 登录
export function login(username, password, code, uuid) {
  return request({
    url: '/auth/login',
    method: 'post',
    data: {username, password, code, uuid}
  })
}

// 注册号
export function register(data) {
  console.info(data)
  return request({
    url: '/system/user/register',
    method: 'post',
    data: data
  })
}

// 刷新方法
export function refreshToken() {
  return request({
    url: '/auth/refresh',
    method: 'post'
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/system/user/getInfoByToken',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'delete'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/system/verify/getCodeChar',
    method: 'get'
  })
}

// 获取短信验证码
export function getSmsCode(phone) {
  console.info(phone)
  return request({
    url: '/system/verify/getRegisterSmsCode/' + phone,
    method: 'get'
  })
}
