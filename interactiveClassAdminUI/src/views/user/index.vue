<template>
  <div class="app-container">
    <div class="btn_group">
      <el-button type="primary" plain size="small" @click="addForm">添加</el-button>
      <el-button type="danger" size="small">批量删除</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      stripe
      fit
      size="mini"
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="用户id" prop="id" width="280px" />
      <el-table-column label="用户名称" prop="name" />
      <el-table-column label="学号" align="center" prop="userNum" />
      <el-table-column label="角色名称" align="center" prop="roleId" />
      <el-table-column class-name="status-col" label="登录ip" align="center" prop="loginIp" />
      <el-table-column align="center" label="登录时间" prop="loginTime" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="editForm(scope.row.id)"
          >编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <form-dialog ref="formDialogCom" />
  </div>
</template>

<script>
import { listPage } from '@/api/user'
import formDialog from './formDialog'
export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  components: {
    formDialog
  },
  data() {
    return {
      list: null,
      pageData: {},
      listLoading: true
    }
  },
  created() {
    this.listPageData()
  },
  methods: {
    async listPageData() {
      const data = await listPage()
      this.pageData = data.data
      debugger
      this.list = this.pageData.list
      this.listLoading = false
    },
    editForm(id) {
      this.$refs.formDialogCom.getUserById(id)
    },
    addForm() {
      this.$refs.formDialogCom.addForm(id)
    },
    handleDelete(id) {

    }
  }
}
</script>
<style scoped>
  .btn_group{
    float: right;
  }
</style>
