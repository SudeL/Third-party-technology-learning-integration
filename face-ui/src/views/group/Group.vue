<template>
  <div>
    <div style="margin-bottom: 15px; margin-top: 15px">
      <span class="text-sapn-select">用户组名字：</span>
      <el-input
        class="input-select"
        placeholder="请输入组名字"
        v-model="input_group_id"
        clearable
      >
      </el-input>
      <el-button type="warning">查询</el-button>
      <br />
      <!-- Form -->
      <el-button type="primary" @click="dialogFormVisible = true"
        >新增</el-button
      >
      <el-dialog title="添加用户组" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form" >
          <el-form-item label="请输入组名" >
            <el-input v-model="form.group_id" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addGroupList"
            >确 定</el-button
          >
        </div>
      </el-dialog>
    </div>

    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="id" label="ID"> </el-table-column>
        <el-table-column prop="group_id" label="用户组名字"> </el-table-column>
        <el-table-column label="操作">
          <el-button type="primary">编辑</el-button>
          <el-button type="danger">删除</el-button>
        </el-table-column>
      </el-table>
    </div>

    <!-- <div>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="currentPage1" :page-size="100" layout="total, prev, pager, next" :total="1000">
      </el-pagination>
    </div> -->
  </div>
</template>
<script>
import request from "../../util/request";

export default {
  data() {
    return {
      input_group_id: "",
      dialogFormVisible:false,
      form:{
        group_id:""
      },
      tableData: [],
    };
  },
  created() {
    this.selectGroupList();
  },
  methods: {
    // 查询所有的用户组信息
    selectGroupList() {
      request.get("/group/getGroupList").then((res) => {
        if (res.code === "0") {
          // 调用成功
          this.tableData = res.data;
        } else {
          //调用失败
        }
      });
    },

    addGroupList(){
      this.dialogFormVisible=false,
      request.post("/group/addGroupList",this.form).then((res) => {
        if (res.code === "0") {
          location.reload()
          // 调用成功
          this.$message({
            type:"success",
            message:"添加成功",
          })
        } else {
          //调用失败
          this.$message({
            type:"info",
            message:"添加失败",
          })
        }
      });
    },
  },
};
</script>
