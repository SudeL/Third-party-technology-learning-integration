<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="mini" label-width="100px" label-position="left">
            <el-form-item label="手机号" prop="user_phone">
                <el-input v-model="formData.user_phone" placeholder="请输入手机号" :maxlength="11" show-word-limit clearable
                    prefix-icon='el-icon-mobile' :style="{ width: '100%' }"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
                <el-input v-model="formData.code" placeholder="请输入验证码" clearable :style="{ width: '100%' }"></el-input>
            </el-form-item>
            <el-button :disabled="isDisabled" @click="startCountdown">
                {{ buttonText }}
            </el-button>
            <el-form-item size="large">
                <el-button type="primary" @click="submitForm">登录</el-button>
                <el-button @click="resetForm">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import request from '../util/request.js';
export default {
    components: {},
    props: [],
    data() {
        return {
            isDisabled: false,
            countdown: 60,
            buttonText: '获取验证码',
            formData: {
                user_phone: '',
                code: '',
                s: '',
                userList:[]

            },
            old: {
                user_phone: '',
                code: '',
                s: '',
            },
            rules: {
                user_pssword: [{
                    required: true,
                    message: '请输入手机号',
                    trigger: 'blur'
                }, {
                    pattern: /^1(3|4|5|7|8|9)\d{9}$/,
                    message: '手机号格式错误',
                    trigger: 'blur'
                }],
                code: [{
                    required: true,
                    message: '请输入验证码',
                    trigger: 'blur'
                }],
            },
        }
    },
    computed: {},
    watch: {},
    created() { },
    mounted() { },
    methods: {
        startCountdown() {
            if (this.isDisabled) {
                return;
            }
            // 后台发送验证码
            request.post("/user/getPhoneCode", this.formData).then(
                res => {
                    if (res.code === '0') {
                        this.old = res.data
                        console.log(this.old);
                        sessionStorage.setItem('userData', JSON.stringify(res.data.userList));
                    } else {
                        this.$message({
                            showClose: true,
                            message: res.msg,
                            type: 'error'
                        });
                    }
                }
            )
            this.isDisabled = true;
            this.countdown = 60;
            this.buttonText = `${this.countdown} 秒后可用`; const countdownInterval = setInterval(() => {
                this.countdown--; this.buttonText = `${this.countdown} 秒后可用`;
                if (this.countdown <= 0) {
                    clearInterval(countdownInterval);
                    this.isDisabled = false;
                    this.buttonText = '获取验证码';
                }
            }, 1000);

        },
        submitForm() {
            this.$refs['elForm'].validate(valid => {
                if (!valid) return
                console.log(this.old);
                console.log(this.formData);

                if (this.old.code === this.formData.code && this.old.user_phone === this.formData.user_phone) {
                    this.$router.push('/HomeView')
                } else {
                    alert("登录失败")
                }
            })
        },
        resetForm() {
            this.$router.push('/')
        },
    }
}

</script>
<style>
/* 全局样式重置 */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* 表单容器样式 */
.el-form {
    width: 300px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    font-family: 'Arial', sans-serif;
}

/* 表单项样式 */
.el-form-item {
    margin-bottom: 15px;
}

/* 输入框样式 */
.el-input__inner {
    border-radius: 4px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
    transition: box-shadow 0.3s;
}

.el-input__inner:hover,
.el-input__inner:focus {
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

/* 按钮样式 */
.el-button {
    border-radius: 4px;
    transition: background-color 0.3s, box-shadow 0.3s;
}

.el-button:hover {
    background-color: #409EFF;
    color: #fff;
    box-shadow: 0 2px 8px rgba(64, 158, 255, 0.5);
}

.el-button--primary {
    background-color: #409EFF;
    color: #fff;
}

.el-button--primary:hover {
    background-color: #66b1ff;
    box-shadow: 0 2px 8px rgba(64, 158, 255, 0.5);
}

/* 清除el-icon-search的默认样式 */
.el-button .el-icon-search {
    color: #fff;
}

/* 重置按钮样式 */
.el-button--text {
    color: #606266;
}

.el-button--text:hover {
    color: #409EFF;
}
</style>