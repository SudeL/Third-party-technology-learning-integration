<template>
  <div>

    <div style="margin-bottom: 15px;margin-top: 15px;">
      <span class="text-sapn-select">用户组名字：</span>
      <el-input class="input-select" placeholder="请输入组名字" v-model="input_group_id" clearable>
      </el-input>
      <el-button type="warning">查询</el-button>
      <br >
      <el-button type="primary" style="margin-top: 15px;">新增</el-button>
    </div>

    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="id" label="ID">
        </el-table-column>
        <el-table-column prop="group_id" label="用户组名字">
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
export default {
  data() {
    return {
      input_group_id: ''
    }
  }
}
</script>