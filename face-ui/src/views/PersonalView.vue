<template>
  <div>
    <!-- 显示用户信息 -->
    <div v-if="user">
      <p>用户ID: {{ user.id }}</p>
      <p>
        用户头像路径:
        <img :src="user.user_face_path ? user.user_face_path : '无'" style="max-width: 200px" />
      </p>
      <p>用户组ID: {{ user.user_group_id }}</p>
      <p>用户登录ID: {{ user.user_id }}</p>
      <p>是否管理员: {{ user.user_isadmin }}</p>
      <p>用户名: {{ user.user_name }}</p>
      <p>用户密码: {{ user.user_password }}</p>
      <p>用户电话: {{ user.user_phone }}</p>
      <el-button type="primary" @click="editUser()">修改</el-button>
    </div>

    <!-- 修改用户信息的弹出卡片 -->
    <el-dialog title="基本信息" :visible.sync="dialogVisible">
      <p>用户ID: {{ user.id }}</p>
      <p>
        用户头像路径:
        <el-upload class="avatar-uploader" action="/upload" :show-file-list="false" :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="user.user_face_path" :src="user.user_face_path" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </p>
      <p>
        用户组ID:
        <el-select v-model="user.user_group_id" placeholder="请选择">
          <el-option v-for="group in userGroups" :key="group.id" :label="group.name" :value="group.id"></el-option>
        </el-select>
      </p>
      <p>用户登录ID: <el-input v-model="user.user_id" disabled></el-input></p>
      <p>
        是否管理员:
        <el-select v-model="user.user_isadmin" placeholder="请选择">
          <el-option label="是" :value="0"></el-option>
          <el-option label="否" :value="1"></el-option>
        </el-select>
      </p>
      <p>用户名: <el-input v-model="user.user_name"></el-input></p>
      <p>用户密码: <el-input v-model="user.user_password"></el-input></p>
      <p>用户电话: <el-input v-model="user.user_phone"></el-input></p>
      <el-button @click="dialogVisible = false">取消</el-button>&nbsp;&nbsp;
      <el-button @click="saveUser()">保存</el-button>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        id: "", // 用户ID
        user_face_path: "", // 用户头像路径保存在user_face_path属性中
        user_group_id: "", // 用户组ID
        user_id: "", // 用户登录ID
        user_isadmin: "", // 是否管理员
        user_name: "", // 用户名
        user_password: "", // 用户密码
        user_phone: "", // 用户电话
      },
      dialogVisible: false, // 控制弹出卡片的显示/隐藏
      userGroups: [], // 存储用户组数据的数组
    };
  },
  mounted() {
    const userData = JSON.parse(sessionStorage.getItem("userData"));
    if (userData) {
      this.user = userData[0];
    }

    // 获取用户组数据
    this.getUserGroupsFromBackend();
  },
  methods: {
    editUser() {
      this.dialogVisible = true;
    },
    saveUser() {
      // 向后台发送修改后的用户数据（this.user）
      this.saveUserToBackend(this.user);
      this.dialogVisible = false;
    },
    getUserGroupsFromBackend() {
      // 模拟从后台获取用户组数据的逻辑
      // 假设从后台获取的用户组数据是一个数组，每个元素包含 id 和 name
      const userGroupsData = [
        { id: 1, name: "用户组1" },
        { id: 2, name: "用户组2" },
        { id: 3, name: "用户组3" },
      ];
      this.userGroups = userGroupsData;
    },
    handleAvatarSuccess(res, file) {
      this.user.user_face_path = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isPNG = file.type === "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG && !isPNG) {
        this.$message.error("上传头像图片只能是 JPG/PNG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }

      return (isJPG || isPNG) && isLt2M;
    },
  },
};
</script>

<style>
.avatar-uploader {
  display: inline-block;
  text-align: center;
  font-size: 12px;
  color: #8c939d;
  width: 78px;
  height: 78px;
  line-height: 78px;
  border: 1px dashed #d9d9d9;
  border-radius: 4px;
  cursor: pointer;
  overflow: hidden;
}

.avatar {
  width: 78px;
  height: 78px;
  display: block;
}
</style>
