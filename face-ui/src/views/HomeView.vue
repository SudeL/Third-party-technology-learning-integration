<template>
    <el-container>
        <el-header><h1>用户管理系统</h1></el-header>
        <el-container>
            <!-- 左边导航栏 -->
            <el-aside width="250px" style="background-color: aliceblue; overflow: hidden;">
                <Aside />
            </el-aside>

            <el-main>
                <router-view />
            </el-main>
        </el-container>

    </el-container>

</template>

<script>
//  注册左边导航栏
import Aside from '@/components/Home/Aside.vue';
export default {
    mounted() {
        const userData = JSON.parse(sessionStorage.getItem('userData'));
        if (userData) {
            console.log(userData[0]);
        }
    },
    components: {
        Aside
    }
}

</script>

<style>
/* 设置顶部Header的样式，颜色略深 */
.el-header {
<<<<<<< HEAD
  background-color: #4a4a4a; /* 选择一个略深的颜色 */
  color: white; /* 文字颜色为白色 */
  text-align: center;
  width: 100%;
=======
    background-color: #4a4a4a;
    /* 选择一个略深的颜色 */
    color: white;
    /* 文字颜色为白色 */
>>>>>>> 3220692c5ca00df9e737f6c320524df8d18b39fc
}


/* 设置main部分的样式 */
.el-main {
<<<<<<< HEAD
  background-color: #fff; /* 背景颜色为白色 */
  padding: 20px;
  height: calc(100vh - 80px); /* 减去Header的高度，使得Main占满剩余空间 */
  overflow-y: auto; /* 添加垂直滚动条 */
=======
    background-color: #fff;
    /* 背景颜色为白色 */
    padding: 20px;
    height: calc(100vh - 60px);
    /* 减去Header的高度，使得Main占满剩余空间 */
    overflow-y: auto;
    /* 添加垂直滚动条 */
>>>>>>> 3220692c5ca00df9e737f6c320524df8d18b39fc
}
</style>
