<template>
<div>

</div>
</template>
<script>
import request from '../util/request.js';
export default {
    data() {
        return {
            user_id:''
        }
    },
    created() {
        this.user_id = sessionStorage.getItem('user_id') || '';
        this.select();
        
    },
    methods: {
        select() {
            // 通过user_id获取数据
            request.post("/user/getUserByUserID", this.user_id).then(res => {
                if (res.code === '0') {
                    // 调用成功  
                    console.log(res);
                    // 存储数据到 sessionStorage  
                    sessionStorage.setItem('userData', JSON.stringify(res.data));
                    this.$router.push('/HomeView');
                } else {
                    // 调用失败  
                    alert(res.msg)
                    console.log(res);
                }
            });
        }
    }
}
</script>