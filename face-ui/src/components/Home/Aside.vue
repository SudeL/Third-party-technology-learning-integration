<template>
    <el-menu :default-active="$route.path" router class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
        background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">

        <el-menu-item index="/views/PersonalView">
            <span slot="title">个人信息</span>
        </el-menu-item>

        <el-submenu index="2">
            <template slot="title">
                <span>信息管理</span>
            </template>
            <el-menu-item-group>
                <el-menu-item index="/group">用户组管理</el-menu-item>
                <el-menu-item index="/user">用户管理</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>
</template>

<style>
/* 设置整个页面的高度为100% */
html,
body {
    height: 100%;
    margin: 0;
    padding: 0;
    overflow: hidden;
    /* 防止页面内容溢出 */
}

/* 设置el-menu的容器高度为100% */
.el-menu-vertical-demo {
    height: 100%;
    overflow-y: auto;
    /* 如果内容超出，显示滚动条 */
}

/* 可能需要为el-menu的父元素设置高度 */
/* 例如，如果你有一个包裹整个应用的容器，你可以这样设置 */
/* .app-container {
  height: 100%;
} */

/* 如果你的el-menu是在某个特定组件内部，确保该组件的高度也是100% */
/* 例如，如果你有一个名为MenuContainer的组件，你可以这样设置 */
/* .menu-container {
  height: 100%;
} */
</style>