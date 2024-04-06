<template>
    <el-menu :default-active="$route.path" router class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
        background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">

        <el-menu-item index="/PersonalView">
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
/* 设置html、body的高度为100%，以便于子元素可以基于此进行全屏布局 */
html, body {
  height: 100%;
  margin: 0; /* 移除默认的margin */
}

/* 设置el-container的高度为100%，确保它能占满整个屏幕 */
.el-container {
  height: 100%;
}

/* 设置el-menu的高度为100%，确保菜单栏也能占满屏幕的垂直空间 */
.el-menu-vertical-demo {
  height: 100%;
}

/* 设置el-submenu的子菜单项高度自适应，以便它们可以根据内容自动扩展 */
.el-submenu__title {
  display: flex;
  align-items: center;
}

/* 设置el-menu-item的高度自适应，以便它们可以根据内容自动扩展 */
.el-menu-item {
  display: flex;
  align-items: center;
  height: auto; /* 允许菜单项根据内容自适应高度 */
}

/* 确保el-menu内部的滚动条只在必要时出现 */
.el-menu-vertical-demo::-webkit-scrollbar {
  display: none; /* 隐藏滚动条 */
}

/* 设置el-menu的滚动行为为auto，以便在内容超出时显示滚动条 */
.el-menu-vertical-demo {
  overflow-x: auto;
  overflow-y: auto;
}

/* 其他样式调整，确保布局的一致性和美观性 */
.el-menu-vertical-demo {
  background-color: #545c64; /* 菜单背景色 */
  color: #fff; /* 菜单文字颜色 */
}

.el-menu-item, .el-submenu__title {
  padding: 0 20px; /* 菜单项的左右内边距 */
}

.el-menu-item span[slot="title"] {
  color: #fff; /* 菜单项标题文字颜色 */
}

.el-menu-item.is-active {
  background-color: #ffd04b; /* 激活状态的菜单项背景色 */
}
</style>