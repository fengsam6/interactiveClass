<template>
  <div class="app-container">
    <div class="btn_group">
      <el-button type="primary" plain size="small" @click="addForm">添加</el-button>
      <el-button size="small" type="danger" @click="delCourseByIds">批量删除</el-button>
    </div>
    <el-table
      ref="multipleTable"
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      height="calc(100vh - 150px)"
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
      <el-table-column type="index" width="80" align="center" label="序号" />
      <!--      <el-table-column label="用户id" prop="id" width="280px" />-->
      <el-table-column label="作业名称" prop="workTitle" />
      <el-table-column label="学生名称" prop="studentName" />
      <el-table-column label="作业简介" align="center" prop="workDes" />
      <el-table-column label="课后作业名称" align="center" prop="courseName" />
      <el-table-column label="教师名称" prop="teacherName" />
      <el-table-column label="附件名称" align="center" min-width="120px">
        <template slot-scope="scope">
          {{ scope.row.attachPath | attachName }}
        </template>
      </el-table-column>
      <el-table-column label="上传时间" align="center" prop="submitTime" min-width="130px" />
      <el-table-column label="操作" min-width="190px" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            plain
            size="mini"
            @click="editForm(scope.row.id)"
          >编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDeleteRow(scope.row.id)"
          >撤回作业
          </el-button>
          <el-button
            size="mini"
            type="primary"
            plain
            @click="downLoadFile(scope.row.attachPath)"
          >下载
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <form-dialog ref="formDialogCom" @refreshDataList="refreshDataList" />
    <pagination :total-size="totalSize" @updateDataList="refreshDataList" />
  </div>
</template>

<script>
import { listPage, deleteHomeworkByIds } from '@/api/homework'
import formDialog from './formDialog'
import { downFile } from '@/utils/fileRequest'
import Pagination from '@/components/Pagnation/Pagination'

export default {
  components: {
    formDialog,
    Pagination
  },
  filters: {
    attachName(filePath) {
      if (filePath != null) {
        const index = filePath.indexOf('_')
        if (index > -1) {
          return filePath.substr(index + 1)
        }
      } else {
        return ''
      }
    }
  },
  data() {
    return {
      list: null,
      pageData: {},
      totalSize: 0,
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
        this.totalSize = this.pageData.total
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
    getRecordId(records) {
      const recordIds = []
      for (let i = 0; i < records.length; i++) {
        recordIds.push(records[i].id)
      }
      return recordIds
    },
    delCourseByIds() {
      const records = this.$refs.multipleTable.selection
      if (records.length === 0) {
        this.$message.error('请选择要删除的课后作业!')
        return
      }
      this.$confirm('此操作将删除课后作业, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const recordIds = this.getRecordId(records)
        deleteHomeworkByIds({ ids: recordIds.join(',') }).then((resp) => {
          this.$message.success(resp.data)
          this.refreshDataList()
        })
      })
    },
    handleDeleteRow(recordIds) {
      this.$confirm('此操作将删除课后作业, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteHomeworkByIds({ ids: recordIds }).then((resp) => {
          this.$message.success(resp.data)
          this.refreshDataList()
        })
      })
    },
    refreshDataList() {
      this.listPageData()
    },
    downLoadFile(filePath) {
      downFile(filePath)
    },
    editForm(id) {
      this.$refs.formDialogCom.editForm(id)
    },
    addForm(id) {
      this.$refs.formDialogCom.addForm(id)
    }

  }
}
</script>
<style scoped>
  .btn_group {
    float: right;
  }
</style>
