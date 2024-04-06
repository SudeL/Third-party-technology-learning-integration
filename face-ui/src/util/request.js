import axios from 'axios';

// 创建一个axios对象
const request = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 5000
});

// 请求拦截器
// 可以在请求发送前对请求进行处理
// 比如统一加token，封装请求统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    // config.headers['token'] = user.token; // 设置请求头
    return config;
}, error => {
    // 对请求错误做些什么，然后reject
    return Promise.reject(error);
});

// 响应拦截器
// 可以在接口响应后统一处理响应
request.interceptors.response.use(
    response => {
        // response.data为后端返回的数据
        let res = response.data;
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            try {
                res = JSON.parse(res);
            } catch (e) {
                // 如果解析出错，直接返回原始数据
                console.error('Parsing response data error:', e);
                return res;
            }
        }
        return res;
    },
    error => {
        // 对响应错误做些什么
        console.error('Response error:', error);
        return Promise.reject(error);
    }
);

export default request;