<template>
  <div>
    <div style="margin-bottom: 15px; margin-top: 15px">
      <!-- 通过userId查 -->
      <span class="text-sapn-select">用户id：</span>
      <el-input
        class="input-select"
        placeholder="请输入用户id"
        v-model="params.user_id"
        clearable
      >
      </el-input>
      <!-- 通过username查 -->
      <span class="text-sapn-select">用户账号：</span>
      <el-input
        class="input-select"
        placeholder="请输入用户账号"
        v-model="params.user_name"
        clearable
      >
      </el-input>
      <!-- 通过userUserid查 -->
      <span class="text-sapn-select">用户组名字：</span>
      <el-input
        class="input-select"
        placeholder="请输入组名字"
        v-model="params.user_group_id"
        clearable
      >
      </el-input>
      <!-- 通过userphone查 -->
      <span class="text-sapn-select">用户手机号：</span>
      <el-input
        class="input-select"
        placeholder="请输入用户手机号"
        v-model="params.user_phone"
        clearable
      >
      </el-input>
      <br /><br />
      <el-button type="warning" @click="selectUserList()">查询</el-button>
      <el-button type="warning" @click="reset()">清空</el-button>
      <el-button type="primary" @click="addUserList()">新增</el-button>
      <br /><br />
      <!-- Form -->
      <el-dialog title="个人信息" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <el-form-item label="用户ID" :label-width="formLabelWidth">
            <el-input v-model="form.id" autocomplete="off" readonly></el-input>
          </el-form-item>

          <el-form-item label="用户人脸" :label-width="formLabelWidth">
            <el-upload
              class="upload-demo"
              drag
              action="http://localhost:8080/files/upload"
              :on-success="successUpdata"
              multiple
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或<em>点击上传</em>
              </div>
              <div class="el-upload__tip" slot="tip">
                只能上传jpg/png文件，且不超过500kb
              </div>
            </el-upload>
          </el-form-item>

          <el-form-item label="用户组" :label-width="formLabelWidth">
            <el-select v-model="form.user_group_id" placeholder="请选择">
              <el-option v-for="g in groupIdList" :key="g" :value="g">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="用户名" :label-width="formLabelWidth">
            <el-input v-model="form.user_name" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="用户密码" :label-width="formLabelWidth">
            <el-input
              v-model="form.user_password"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-input v-model="form.user_phone" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="submit()">保存</el-button>
        </div>
      </el-dialog>
    </div>

    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="id" label="编号"> </el-table-column>

        <el-table-column prop="user_id" label="用户id"> </el-table-column>

        <el-table-column prop="user_name" label="用户账号"> </el-table-column>

        <el-table-column prop="user_password" label="用户密码">
        </el-table-column>

        <el-table-column prop="user_group_id" label="用户组名字">
        </el-table-column>

        <el-table-column prop="user_face_path" label="用户人脸">
          <template slot-scope="scope">
            <img
              :src="scope.row.user_face_path ? 'http://localhost:8080/files/' + scope.row.user_face_path.split('~')[0] : '未人脸认证'"
              style="width: 50px; height: 50px;" />
          </template>
        </el-table-column>

        <el-table-column prop="user_isadmin" label="用户权限">
        </el-table-column>

        <el-table-column prop="user_phone" label="用户手机号">
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="editUserList(scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="delUserList(scope.row.id)">
              <el-button type="danger" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style="margin-top: 10px">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="params.pageNum" :page-sizes="[5, 10, 15, 20]" :page-size="params.pageSize"
        layout="total,sizes, prev, pager, next" :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import request from "../../util/request";

export default {
  data() {
    return {
      dialogFormVisible: false,
      form: {},
      params: {
        user_id: "",
        user_name: "",
        user_Group_id: "",
        user_phone: "",
        pageNum: 1,
        pageSize: 5,
      },
      tableData: [],
      groupIdList: [],
      formLabelWidth: "120px",
      total: 0,
    };
  },
  created() {
    this.selectUserList();
    this.selectGroupList();
  },
  methods: {
    selectGroupList() {
      // 获取用户组
      request
        .post("/group/getGroupID")
        .then((res) => {
          if (res.code === "0") {
            // 调用成功
            let resultObject = JSON.parse(res.data);
            let groupIdList = resultObject.result.group_id_list;
            this.groupIdList = groupIdList; // 将获取到的数据赋值给组件的data属性
            console.log(groupIdList);
          } else {
            // 调用失败
            console.error("获取用户组失败", res);
          }
        })
        .catch((error) => {
          // 处理请求错误
          console.error("请求错误", error);
        });
    },
    // 查询所有用户信息
    selectUserList() {
      request.get("/user/getUserList", { params: this.params }).then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {
          //调用失败
        }
      });
    },

    reset() {
      this.params = {
        user_id: "",
        user_name: "",
        user_User_id: "",
        user_phone: "",
        pageNum: 1,
        pageSize: 5,
      };
      this.selectUserList();
    },

    //显示多少条
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.selectUserList();
    },
    //显示当前页
    handleCurrentChange(pageNUm) {
      this.params.pageNum = pageNUm;
      this.selectUserList();
    },

    //添加数据库用户
    addUserList() {
      this.form = {};
      this.dialogFormVisible = true;
    },

    //修改数据库用户
    editUserList(obj) {
      (this.form = obj), (this.dialogFormVisible = true);
    },
    // 文件上传回调函数
    successUpdata(res) {
      this.form.user_face_path = res.data;
    },

    //删除数据库用户
    delUserList(id) {
      request.post("/user/deleteUserList/" + id).then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.$message({
            type: "success",
            message: "删除成功",
          });
          this.dialogFormVisible = false;
          this.selectUserList();
        } else {
          //调用失败
          this.$message({
            type: "error",
            message: "删除失败",
          });
        }
      });
    },

    //添加表单
    submit() {
      console.log(this.form);
      request.post("/user/changeUserList", this.form).then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.$message({
            type: "success",
            message: "操作成功",
          });
          this.dialogFormVisible = false;
          this.selectUserList();
        } else {
          //调用失败
          this.$message({
            type: "error",
            message: "操作失败",
          });
        }
      });
    },
  },
};
</script>
