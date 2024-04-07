<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="title" v-if="!registerMode">用户登录</div>
      <div class="title" v-if="registerMode">用户注册</div>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- 登录表单 -->
        <template v-if="!registerMode">
          <el-form-item label="用户名" prop="user_name">
            <el-input v-model="form.user_name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="user_password">
            <el-input type="password" v-model="form.user_password" autocomplete="off"></el-input>
          </el-form-item>
        </template>

        <!-- 注册表单 -->
        <template v-if="registerMode">
          <el-form-item label="用户名" prop="user_name">
            <el-input v-model="form.user_name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="user_password">
            <el-input type="password" v-model="form.user_password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input type="password" v-model="form.confirmPassword" autocomplete="off"></el-input>
          </el-form-item>
        </template>

        <el-form-item>
          <el-button type="primary" @click="submitForm">登录</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="default" @click="switchForm">切换</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="default" @click="PhoneLong()">验证码登录</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="default" @click="FaceLong()">人脸登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import request from '../util/request.js';
export default {
  data() {
    return {
      form: {
        user_name: '',
        user_password: '',
        confirmPassword: '',
        register: false // 用于区分是登录表单还是注册表单
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
          { validator: this.confirmPasswordValidate, trigger: 'blur' }
        ]
      }
    };
  },
  computed: {
    registerMode() {
      return this.form.register;
    }
  },
  methods: {
    FaceLong() {
      this.$router.push('/FaceLoginView')
    },
    PhoneLong() {
      this.$router.push('/PhoneLoginView')
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          console.log('表单提交', this.form);
          // 这里可以添加表单提交逻辑

          if (this.form.register) {
            // 注册逻辑
            request.post("/user/reg", this.form).then(
              res => {
                if (res.code === '0') {
                  // 调用成功         
                  this.$message({
                    message: '注册成功',
                    type: 'success'
                  });
                  console.log(res)
                } else {
                  //调用失败
                }
              }
            )
          } else {
            // 登录逻辑  
            request.post("/user/login", this.form).then(res => {
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

        } else {
          console.log('表单验证失败');
        }
      });
    },
    switchForm() {
      this.form.register = !this.form.register;
      // 重置表单数据      
      this.form.user_name = '';
      this.form.user_password = '';
      this.form.confirmPassword = '';
    },
    confirmPasswordValidate(rule, value, callback) {
      if (value !== this.form.user_password) {
        callback(new Error('两次输入的密码不一致，请重新输入'));
      } else {
        callback();
      }
    }
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(100vh - 100px);
  /* 页面中心偏上的位置 */
  background-color: #f5f5f5;
  /* 灰色调的浅色背景 */
}

.login-card {
  width: 400px;
  margin-top: 20px;
  /* 根据需要调整 */
}

.title {
  text-align: center;
  margin-bottom: 20px;
  font-size: 20px;
  font-weight: bold;
}

.el-form-item {
  margin-bottom: 15px;
  /* 根据需要调整 */
}

.el-button {
  width: 100%;
  /* 按钮宽度 */
}
</style>