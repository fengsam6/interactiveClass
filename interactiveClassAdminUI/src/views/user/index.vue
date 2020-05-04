<template>
  <div class="app-container">
    <div class="btn_group">
      <el-button type="primary" plain size="small" @click="addForm">添加</el-button>
      <el-button type="danger" size="small" @click="delUsesByIds">批量删除</el-button>
    </div>
    <el-table
      ref="multipleTable"
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      stripe
      fit
      size="mini"
      highlight-current-row
      @row-click="rowClick"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column type="index" width="80" align="center" />
      <!--      <el-table-column label="用户id" prop="id" width="280px" />-->
      <el-table-column label="用户名称" prop="name" />
      <el-table-column label="学号" align="center" prop="userNum" />
      <el-table-column label="性别" prop="sexType" min-width="100px" />
      <el-table-column label="角色名称" align="center" prop="roleType" />
      <el-table-column class-name="status-col" label="登录ip" align="center" prop="loginIp" />
      <el-table-column align="center" label="登录时间" prop="loginTime" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            plain
            size="mini"
            @click="editForm(scope.row.id)"
          >编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDeleteRow(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <form-dialog ref="formDialogCom" @refreshDataList="refreshDataList" />
  </div>
</template>

<script>
import { listPage, deleteUserById } from '@/api/user'
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
      try {
        const data = await listPage()
        this.pageData = data.data
        this.list = this.pageData.list
        this.listLoading = false
      } catch (e) {
        this.listLoading = false
      }
    },
    rowClick(row, column, event) {
      const refsElTable = this.$refs.multipleTable // 获取表格对象
      refsElTable.toggleRowSelection(row) // 调用选中行方法
    },
    refreshDataList() {
      this.listPageData()
    },
    editForm(id) {
      this.$refs.formDialogCom.editForm(id)
    },
    addForm() {
      this.$refs.formDialogCom.addForm()
    },
    getRecordId(records) {
      const recordIds = []
      for (let i = 0; i < records.length; i++) {
        recordIds.push(records[i].id)
      }
      return recordIds
    },
    delUsesByIds() {
      const records = this.$refs.multipleTable.selection
      if (records.length === 0) {
        this.$message.error('请选择要删除的用户!')
        return
      }
      this.$confirm('此操作将删除用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const recordIds = this.getRecordId(records)
        deleteUserById({ ids: recordIds.join(',') }).then((resp) => {
          this.$message.success(resp.data)
          this.refreshDataList()
        })
      })
    },
    handleDeleteRow(recordIds) {
      this.$confirm('此操作将删除用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUserById({ ids: recordIds }).then((resp) => {
          this.$message.success(resp.data)
          this.refreshDataList()
        })
      })
    }
  }
}
</script>
<style scoped>
  .btn_group{
    float: right;
  }
</style>
