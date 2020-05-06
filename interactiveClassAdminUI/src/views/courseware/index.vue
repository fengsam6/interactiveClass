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
      <el-table-column label="课程名称" prop="courseName" />
      <el-table-column label="班级名称" align="center" prop="className" />
      <el-table-column label="上课人数" align="center" prop="classNum" />
      <el-table-column label="上课时间" align="center" prop="beginTime" />
      <el-table-column align="center" label="下课时间" prop="endTime" />
      <el-table-column
        align="center"
        label="教学ppt"
      >
        <template slot-scope="scope">
          <span v-if="scope.row.pptResources.length>0">{{ scope.row.pptResources[0].courseResourceName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="教学视频"
      >
        <template slot-scope="scope">
          <span v-if="scope.row.videoResources.length>0">{{ scope.row.videoResources[0].courseResourceName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="100px" align="center">
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
import { listPage, deleteCourseByIds } from '@/api/course'
import formDialog from './formDialog'
export default {
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
        this.$message.error('请选择要删除的课程!')
        return
      }
      this.$confirm('此操作将删除课程, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const recordIds = this.getRecordId(records)
        deleteCourseByIds({ ids: recordIds.join(',') }).then((resp) => {
          this.$message.success(resp.data)
          this.refreshDataList()
        })
      })
    },
    handleDeleteRow(recordIds) {
      this.$confirm('此操作将删除课程, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCourseByIds({ ids: recordIds }).then((resp) => {
          this.$message.success(resp.data)
          this.refreshDataList()
        })
      })
    },
    refreshDataList() {
      this.listPageData()
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
  .btn_group{
    float: right;
  }
</style>
