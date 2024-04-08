<template>
  <div>
    <div class="videodome">
      <div>
        <video id="videoCamera" width="250" height="250" autoplay
          style="border-radius:180px;box-shadow: darkgrey 0 0  30px 5px ;"></video>
      </div>
      <canvas style="display:none; " id="canvasCamera" width="250" height="250"></canvas>
    </div>
    <!-- <div class="img_bg_camera"> -->
      <!-- <p style="color: #000000;">图片显示</p> -->
      <!-- <img :src="imgSrc" alt="" class="tx_img" style="border-radius:360px;box-shadow: darkgrey 0 0  30px 5px ;"
        v-if="imgif"> -->
    <!-- </div> -->
    <div class="bommen">
      <el-select v-model="formData.group_id" placeholder="请选择">
        <el-option v-for="item in groupIdList" :key="item" :label="item" :value="item">
        </el-option>
      </el-select>
      <el-button type="success" @click="getCompetence()">打开摄像头</el-button>
      <el-button type="success" @click="setImage()">拍照</el-button>
      <el-button type="primary" @click="openFullScreen1" v-loading.fullscreen.lock="fullscreenLoading">登录</el-button>

    </div>
  </div>
</template>
<script>
import request from '../util/request.js'
export default {
  data() {
    return {
      imgif: false,
      videoWidth: 250,
      videoHeight: 250,
      imgSrc: '',
      thisCancas: null,
      thisContext: null,
      thisVideo: null,
      imga: '',
      formData: {
        base64: '',
        group_id: ''
      },
      groupIdList: []
    }

  },
  created() {
    this.selectGroupList();
  },
  methods: {
    selectGroupList() {
      // 获取用户组
      // 获取用户组  
      request.post("/group/getGroupID").then((res) => {
        if (res.code === "0") {
          // 调用成功  
          let resultObject = JSON.parse(res.data);
          let groupIdList = resultObject.result.group_id_list;
          this.groupIdList = groupIdList; // 将获取到的数据赋值给组件的data属性  
          console.log(groupIdList);
        } else {
          // 调用失败  
          console.error('获取用户组失败', res);
        }
      }).catch((error) => {
        // 处理请求错误  
        console.error('请求错误', error);
      });
    },
    // 提取图片转base64码并传递后台进行识别
    getCompetence() {
      var _this = this
      this.thisCancas = document.getElementById('canvasCamera')
      this.thisContext = this.thisCancas.getContext('2d')
      this.thisVideo = document.getElementById('videoCamera')
      // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {}
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function (constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator
            .getUserMedia
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          if (!getUserMedia) {
            return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function (resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject)
          })
        }
      }
      var constraints = {
        audio: false,
        video: {
          width: this.videoWidth,
          height: this.videoHeight,
          transform: 'scaleX(-1)'
        }
      }
      navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
        // 旧的浏览器可能没有srcObject
        if ('srcObject' in _this.thisVideo) {
          _this.thisVideo.srcObject = stream

        } else {
          // 避免在新的浏览器中使用它，因为它正在被弃用。
          _this.thisVideo.src = window.URL.createObjectURL(stream)
        }
        _this.thisVideo.onloadedmetadata = function (e) {
          _this.thisVideo.play()
        }

      }).catch(err => {
        console.log(err)
      })



    },
    //  绘制图片（拍照功能）

    setImage() {
      this.imgif = true
      var _this = this
      // 点击，canvas画图
      _this.thisContext.drawImage(_this.thisVideo, 0, 0, 250, 250)
      console.log(_this.thisContext);

      // 获取图片base64链接
      var image = this.thisCancas.toDataURL('image/png')
      _this.imgSrc = image
      window.sessionStorage.setItem("img", _this.imgSrc)
      console.log(this.imgSrc);
    },
    //关闭摄像头并上传数据
    openFullScreen1() {
      this.thisVideo.srcObject.getTracks()[0].stop(),//关闭摄像头

        setTimeout(() => {
          // var file = this.imga.substr(22)
          this.imga = window.sessionStorage.getItem("img")
          var file = this.imga.split(",")[1]
          console.log(file);
          this.formData.base64 = file;
          // this.formData.group_id = "dome1";
          //自己配置axios。
          request.post("/group/FaceLogin", this.formData).then(
            (res) => {
              if (res.code === '0') {
                try {
                  // 解析字符串为对象  
                  const dataObj = JSON.parse(res.data);
                  // 检查result是否为空  
                  if (!dataObj.result) {
                    this.$message({
                            showClose: true,
                            message:未选择用户组,
                            type: 'error'
                        });
                    return;
                  }
                  // 获取user_list数组  
                  const userList = dataObj.result.user_list;
                  // 遍历user_list数组并处理每个用户的信息  
                  userList.forEach(user => {
                    const user_id = user.user_id;
                    const score = user.score;
                    // 这里开始处理
                    if (score > 90) {
                      // 登录成功
                      // 存储数据到 sessionStorage  
                      // 跳转到数据处理页面
                      sessionStorage.setItem('user_id', user_id);
                      this.$router.push('/FiliceView');
                    } else {
                      console.log("登录失败");
                    }
                  });
                } catch (error) {
                  // 处理JSON解析错误  
                  alert('解析数据出错: ' + error.message);
                }
              }
            }
          )
        }, 2000);
    },
  }
}
</script>
<style>
body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  /* 使用视口高度确保页面至少占据整个屏幕 */
  margin: 0;
  /* 移除默认的margin */
  font-family: Arial, sans-serif;
  /* 设置一个默认的字体 */
}

/* 设置视频和画布容器的样式 */
.videodome {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 300px;
  /* 根据您的需要调整容器宽度 */
  height: 300px;
  /* 根据您的需要调整容器高度 */
  margin: 0 auto;
  /* 自动边距和居中 */
  position: relative;
  /* 相对定位，为子元素定位做准备 */
  /* box-shadow: darkgrey 0 0 30px 5px; */
  /* 保留原有的阴影效果 */
  border-radius: 180px;
  /* 保留原有的边框圆角 */
}

/* 设置图片背景容器的样式 */
.img_bg_camera {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 300px;
  /* 根据您的需要调整宽度 */
  height: 300px;
  /* 根据您的需要调整高度 */
  margin: 20px 0;
  /* 根据您的需要调整上下边距 */
  position: relative;
  /* 相对定位，为子元素定位做准备 */
  box-shadow: darkgrey 0 0 30px 5px;
  /* 保留原有的阴影效果 */
  border-radius: 360px;
  /* 保留原有的边框圆角 */
}

/* 设置按钮组的样式 */
.bommen {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  /* 根据您的需要调整上边距 */
}

/* 为按钮添加一些样式 */
.el-button {
  margin: 0 10px;
  /* 按钮之间的间距 */
  padding: 10px 20px;
  /* 按钮的内边距 */
  font-size: 16px;
  /* 按钮的字体大小 */
}
</style>