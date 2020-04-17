<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      stripe
      fit
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
    </el-table>
  </div>
</template>

<script>
import { listPage } from '@/api/user'

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
    }
  }
}
</script>
