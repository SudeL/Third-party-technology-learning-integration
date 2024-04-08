<template>
  <div>
    <div>
      <!-- 显示用户信息 -->
      <el-descriptions title="用户信息" v-if="form" :column="2" size="200px">
        <el-descriptions-item label="用户ID">{{
          form.id
        }}</el-descriptions-item>
        <el-descriptions-item label="头像">
          <img
            :src="form.user_face_path ? form.user_face_path : '无'"
            style="max-width: 200px"
        /></el-descriptions-item>
        <el-descriptions-item label="用户名">{{
          form.user_name
        }}</el-descriptions-item>
        <el-descriptions-item label="用户密码">{{
          form.user_password
        }}</el-descriptions-item>

        <el-descriptions-item label="用户组ID">{{
          form.user_group_id
        }}</el-descriptions-item>
        <el-descriptions-item label="用户登录ID">{{
          form.user_id
        }}</el-descriptions-item>
        <el-descriptions-item label="手机号">{{
          form.user_phone
        }}</el-descriptions-item>
      </el-descriptions>

      <el-button type="primary" @click="editUser()">修改</el-button>
    </div>

    <!-- Form -->
    <el-dialog title="个人信息" :visible.sync="dialogFormVisible" width="35%">
      <el-form :model="form">
        <el-form-item label="用户ID" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off" readonly></el-input>
        </el-form-item>
        <el-form-item label="用户头像" :label-width="formLabelWidth">
          <el-upload
            class="avatar-uploader"
            action="/files/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img
              v-if="form.user_face_path"
              :src="form.user_face_path"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.user_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth">
          <el-input v-model="form.user_password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="form.user_phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button type="primary" @click="saveUser()">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "../util/request";

export default {
  name: "PersonalView",
  data() {
    return {
      form: {
        id: "", // 用户ID
        user_face_path: "", // 用户头像路径保存在user_face_path属性中
        user_group_id: "", // 用户组ID
        user_id: "", // 用户登录ID
        user_isadmin: "0", // 是否管理员
        user_name: "", // 用户名
        user_password: "", // 用户密码
        user_phone: "", // 用户电话
      },
      dialogFormVisible: false, // 控制弹出卡片的显示/隐藏
      userGroups: [], // 存储用户组数据的数组
      formLabelWidth: "120px",
    };
  },
  mounted() {
    this.selectPersonList();
    // 获取用户组数据
    this.getUserGroupsFromBackend();
  },
  methods: {
    editUser() {
      this.dialogFormVisible = true;
    },
    saveUser() {
      // 向后台发送修改后的用户数据（this.user）
      this.saveUserToBackend();
      this.form = {};
      this.dialogFormVisible = false;
    },
    cancel() {
      this.selectPersonList();
      this.dialogFormVisible = false;
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
      this.form.user_face_path = URL.createObjectURL(file.raw);
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

    saveUserToBackend() {
      request.post("/user/changeUserList", this.form).then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.$message({
            type: "success",
            message: "修改成功",
          });
          this.selectPersonList();
        } else {
          //调用失败
          this.$message({
            type: "error",
            message: "修改失败",
          });
        }
      });
    },

    // 查询个人信息
    selectPersonList() {
      const userData = JSON.parse(sessionStorage.getItem("userData"));
      if (userData) {
        this.form = userData;
      }
      console.log(this.form);
      request.get("/user/getPersonList/" + this.form.user_name).then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.form = res.data;
        } else {
          //调用失败
        }
      });
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
