<template>
    <!-- 页面模板开始 -->
    <div class="video-container">
        <!-- 视频容器 -->
        <div class="video-wrapper">
            <!-- 视频包装器 -->
            <video ref="video" class="video-element" autoplay playsinline></video>
            <!-- 视频元素 -->
            <canvas ref="canvas" class="canvas-element"></canvas>
            <!-- 画布元素 -->
        </div>
        <div class="button-wrapper">
            <!-- 按钮包装器 -->
            选择用户组:
            <!-- 选择用户组标签 -->
            <el-select v-model="group_id" placeholder="请选择">
                <!-- Element UI 下拉选择器 -->
                <el-option v-for="(item, index) in group_id_arr" :key="index" :label="item" :value="item">
                </el-option>
            </el-select>
            <!-- Element UI 选项 -->
            <el-button @click="startCamera">开启摄像头</el-button>
            <!-- Element UI 按钮，点击触发 startCamera 方法 -->
            <el-button @click="stopCamera">停止摄像头</el-button>
            <!-- Element UI 按钮，点击触发 stopCamera 方法 -->
        </div>
    </div>
</template>

<script>
import request from '../util/request'
export default {
    data() {
        return {
            par: {
                base64: '',
                group_id: ''
            },
            index: 0,
            stream: null,
            intervalId: null,
            group_id_arr: [],//后台来的用户组数据
            group_id: ''//前台选中的数据
        };
    },
    created() {
        this.getGroupID();
    },
    methods: {
        //后台获取用户组
        getGroupID() {
            request.post("/group/getGroupID").then((res) => {
                if (res.code === "0") {
                    // 调用成功
                    this.group_id_arr = JSON.parse(res.data);
                    // 将返回数据的用户组赋值给group_id_arr
                    this.group_id_arr = this.group_id_arr.result.group_id_list;
                    console.log(this.group_id_arr);
                } else {
                    //调用失败
                }
            });
        },
        async startCamera() {
            try {
                const stream = await navigator.mediaDevices.getUserMedia({ video: true });
                this.stream = stream;
                this.$refs.video.srcObject = stream;

                // 等待视频元数据加载完成后再设置canvas大小  
                this.$refs.video.addEventListener('loadedmetadata', () => {
                    this.setCanvasSize();
                    this.startCapturing();
                });
            } catch (error) {
                console.error("无法访问摄像头：", error);
            }
        },
        startCapturing() {
            this.intervalId = setInterval(this.capturePhoto, 1000);
        },
        capturePhoto() {
            console.log(this.index);
            if (this.index > 5) {
                console.log("登录超时");
                if (this.stream) {
                    this.stream.getTracks().forEach((track) => track.stop());
                    this.stream = null;
                }
                if (this.intervalId) {
                    clearInterval(this.intervalId);
                    this.intervalId = null;
                }
                this.index = 0;
            }
            this.index = this.index + 1;

            if (this.group_id === '') {
                console.log('未选择分组');
                if (this.stream) {
                    this.stream.getTracks().forEach((track) => track.stop());
                    this.stream = null;
                }
                if (this.intervalId) {
                    clearInterval(this.intervalId);
                    this.intervalId = null;
                }
                this.index = 0;
            } else {

                const video = this.$refs.video;
                const canvas = this.$refs.canvas;
                const context = canvas.getContext("2d");

                // 绘制视频帧到canvas上  
                context.drawImage(video, 0, 0, canvas.width, canvas.height);

                // 将canvas转换为Base64  
                const base64 = canvas.toDataURL("image/jpeg");

                // 在这里你可以添加发送Base64到后台的代码 
                this.par.base64 = base64
                this.par.group_id = this.group_id
                request.post("/group/FaceLogin", this.par).then((res) => {
                    if (res.code === "0") {
                        // 调用成功
                        console.log(res);
                        let jsonData = JSON.parse(res.data);
                        // 访问user_id和score  
                        if (jsonData.result && jsonData.result.user_list && jsonData.result.user_list.length > 0) {
                            let userId = jsonData.result.user_list[0].user_id;
                            let score = jsonData.result.user_list[0].score;
                        } else {
                            console.log("No user_list found in the JSON data.");
                        }
                        if (score > 90) {
                            this.$router.push('/HomeView')
                        }
                    } else {
                        //调用失败
                    }
                });
            }

        },
        setCanvasSize() {
            const video = this.$refs.video;
            const canvas = this.$refs.canvas;
            // 设置canvas的大小与视频流一致  
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
        },
        stopCamera() {
            if (this.stream) {
                this.stream.getTracks().forEach((track) => track.stop());
                this.stream = null;
            }
            if (this.intervalId) {
                clearInterval(this.intervalId);
                this.intervalId = null;
            }
            this.index = 0;
        },
    },

    beforeDestroy() {
        this.stopCamera();
    }
};
</script>

<style scoped>
.video-container {
    display: flex;
    flex-direction: column;
    height: 100vh;
    /* 使容器占据整个视口高度 */
}

.video-wrapper {
    flex: 1;
    /* 占据剩余空间 */
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
}

.video-element {
    max-width: 25vw;
    /* 设置视频元素最大宽度为视口宽度的50% */
    max-height: 25vh;
    /* 设置视频元素最大高度为视口高度的50% */
    object-fit: cover;
    /* 保持视频的宽高比，同时填充整个元素框 */
}

.canvas-element {
    display: none;
    /* 隐藏canvas，因为我们只用它来捕获图片 */
}

.button-wrapper {
    display: flex;
    justify-content: center;
    padding: 10px;
    background-color: #f5f5f5;
    /* 可选：为按钮区域添加背景色 */
}
</style>
