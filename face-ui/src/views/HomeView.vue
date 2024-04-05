<template>
    <el-container>
        <el-header>Header</el-header>
        <el-container>
            <!-- 左边导航栏 -->
            <el-aside width="250px" style="background-color: aliceblue; overflow: hidden;">
            <Aside />
            </el-aside>

            <el-main>
                <router-view/>
            </el-main>
        </el-container>

    </el-container>

</template>

<script>
//  注册左边导航栏
 import Aside from '@/components/Home/Aside.vue';
export default{
    components:{
        Aside
    }
}

</script>

<style>
/* 设置el-header的背景颜色为深灰色 */
.el-header {
  background-color: dimgray;
  /* 可以添加其他样式，如内边距等 */
  padding: 10px;
}

/* 设置el-aside的背景颜色为浅蓝色 */
.el-aside {
  background-color: #f0f8ff; /* 这是一个比aliceblue稍深一点的颜色 */
  /* 确保el-aside不会超出视口高度 */
  height: 100%;
}

/* 设置el-main的样式 */
.el-main {
  /* background-color: bisque; */
  /* 可以添加其他样式，如内边距等 */
  padding: 20px;
}

/* 设置el-container的高度自适应浏览器的高度 */
.el-container {
  height: 100%;
}

/* 确保整个应用的高度也是自适应的 */
html, body {
  height: 100%;
  margin: 0;
}
</style>