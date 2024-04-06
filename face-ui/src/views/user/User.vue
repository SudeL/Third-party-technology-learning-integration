<template>
  <div>

    <div style="margin-bottom: 15px;margin-top: 15px;">
      <!-- 通过userId查 -->
      <span class="text-sapn-select">用户id：</span>
      <el-input class="input-select" placeholder="请输入用户id" v-model="input_user_id" clearable>
      </el-input>
      <!-- 通过username查 -->
      <span class="text-sapn-select">用户账号：</span>
      <el-input class="input-select" placeholder="请输入用户账号" v-model="input_user_name" clearable>
      </el-input>
      <!-- 通过usergroupid查 -->
      <span class="text-sapn-select">用户组名字：</span>
      <el-input class="input-select" placeholder="请输入组名字" v-model="input_group_id" clearable>
      </el-input>
      <!-- 通过userphone查 -->
      <span class="text-sapn-select">用户手机号：</span>
      <el-input class="input-select" placeholder="请输入用户手机号" v-model="input_user_phone" clearable>
      </el-input>
      <el-button type="warning">查询</el-button>
      <br>
      <el-button type="primary" style="margin-top: 15px;">新增</el-button>
    </div>

    <div>
      <el-table :data="tableData" style="width: 100%">

        <el-table-column prop="id" label="编号">
        </el-table-column>

        <el-table-column prop="user_id" label="用户id">
        </el-table-column>

        <el-table-column prop="user_password" label="用户密码">
        </el-table-column>

        <el-table-column prop="user_group_id" label="用户组名字">
        </el-table-column>

        <el-table-column prop="user_face_path" label="用户人脸">
        </el-table-column>

        <el-table-column prop="user_isandmin" label="用户权限">
        </el-table-column>

        <el-table-column prop="user_phone" label="用户手机号">
        </el-table-column>

        <el-table-column label="操作">
          <el-button type="primary">编辑</el-button>
          <el-button type="danger">删除</el-button>
        </el-table-column>
      </el-table>
    </div>

    <div>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="currentPage1" :page-size="100" layout="total, prev, pager, next" :total="1000">
      </el-pagination>
    </div>

  </div>

</template>
<script>
import request from '../../util/request';

export default {
  data() {
    return {
      input_user_id: '',
      tableData: []
    }
  },
  created() {
    this.selectUserList();
  },
  methods: {
    // 查询所有用户信息
    selectUserList() {
      request.get("/user/getUserList").then(res => {
        if (res.code === '0') {
          // 调用成功
          this.tableData = res.data;
        } else {
          //调用失败
        }
      }
      )
    }
  }
}
</script>