import axios from 'axios'

// 创建一个axios对象
const request = axios.create(
    {
        baseURL: 'http://localgost:8080',
        timeout: 5000
    }
)

// requert拦截器
// 可以自请求发送前对请求处理
// 比如统一加token，封装请求统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    // config.headers['token'] = user.token; // 设置请求头
    return config
}, error => {
    return Promise.reject(error)
}
);

// requert拦截器
// 课在接口响应后统一处理接口
request.interceptors.request.use(
    response => {
        // response.data为后端返回的result
        let res = response.data;
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res;
        }
        return res;
    },
    error => {
        console.log('err' + error);
        return Promise.reject(error)
    }
);

export default request