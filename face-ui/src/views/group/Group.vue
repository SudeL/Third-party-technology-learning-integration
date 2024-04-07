<template>
  <div>
    <div style="margin-bottom: 15px; margin-top: 15px">
      <span class="text-sapn-select">用户组名字：</span>
      <el-input
        class="input-select"
        placeholder="请输入组名字"
        v-model="params.group_id"
        clearable
      >
      </el-input>
      <el-button type="warning" @click="selectGroupList()">查询</el-button>
      <el-button type="warning" @click="reset()">清空</el-button>
      <el-button type="primary" @click="addGroupList()">新增</el-button>
      <br />
      <!-- Form -->
      <el-dialog
        title="用户组操作"
        :visible.sync="dialogFormVisible"
        width="30%"
      >
        <el-form :model="form">
          <el-form-item label="请输入组名">
            <el-input v-model="form.group_id" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="id" label="ID"> </el-table-column>
        <el-table-column prop="group_id" label="用户组名字"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="editGroupList(scope.row)"
              >编辑</el-button
            >
            <el-popconfirm
              title="确定删除吗？"
              @confirm="delGroupList(scope.row.id)"
            >
              <el-button type="danger" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style="margin-top: 10px">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="params.pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="params.pageSize"
        layout="total,sizes, prev, pager, next"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
import request from "../../util/request";

export default {
  data() {
    return {
      input_group_id: "",
      dialogFormVisible: false,
      form: {},
      params: {
        group_id: "",
        pageNum: 1,
        pageSize: 5,
      },
      tableData: [],
      total: 0,
    };
  },
  //初始化数据
  created() {
    this.selectGroupList();
  },
  methods: {
    // 查询所有的用户组信息
    selectGroupList() {
      request.get("/group/getGroupList", { params: this.params }).then((res) => {
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
        group_id: "",
        pageNum: 1,
        pageSize: 5,
      };
      this.selectGroupList();
    },

    //显示多少条
    handleSizeChange(pageSize) {
      this.params.pageSize = pageSize;
      this.selectGroupList();
    },
    //显示当前页
    handleCurrentChange(pageNUm) {
      this.params.pageNum = pageNUm;
      this.selectGroupList();
    },

    //添加数据库用户组
    addGroupList() {
      this.form = {};
      this.dialogFormVisible = true;
    },

    //修改数据库用户组
    editGroupList(obj) {
      (this.form = obj), (this.dialogFormVisible = true);
    },

    //删除数据库用户组
    delGroupList(id) {
      console.log(111);
      request.post("/group/deleteGroupList/" + id).then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.$message({
            type: "success",
            message: "删除成功",
          });
          this.dialogFormVisible = false;
          this.selectGroupList();
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
      request.post("/group/changeGroupList", this.form).then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.$message({
            type: "success",
            message: "操作成功",
          });
          this.dialogFormVisible = false;
          this.selectGroupList();
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
