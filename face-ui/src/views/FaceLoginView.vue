<template>
    <div class="video-container">
        <div class="video-wrapper">
            <video ref="video" class="video-element" autoplay playsinline></video>
            <canvas ref="canvas" class="canvas-element"></canvas>
        </div>
        <div class="button-wrapper">
            <el-button @click="startCamera">开启摄像头</el-button>
            <el-button @click="stopCamera">停止摄像头</el-button>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            stream: null,
            intervalId: null,
        };
    },
    methods: {
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
            this.intervalId = setInterval(this.capturePhoto, 5000);
        },
        capturePhoto() {
            const video = this.$refs.video;
            const canvas = this.$refs.canvas;
            const context = canvas.getContext("2d");

            // 绘制视频帧到canvas上  
            context.drawImage(video, 0, 0, canvas.width, canvas.height);

            // 将canvas转换为Base64  
            const base64 = canvas.toDataURL("image/jpeg");

            // 在这里你可以添加发送Base64到后台的代码  
            console.log("捕获的图片Base64：", base64);
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
        },
    },
    beforeDestroy() {
        this.stopCamera();
    },
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
    max-width: 50vw;
    /* 设置视频元素最大宽度为视口宽度的50% */
    max-height: 50vh;
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