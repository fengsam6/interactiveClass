<template>
  <div class="app-container">
    <div class="btn_group">

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
      <el-table-column type="index" width="80"label="序号" align="center" />
      <!--      <el-table-column label="用户id" prop="id" width="280px" />-->
      <el-table-column label="课程名称" align="center" prop="courseName" />
      <el-table-column label="班级名称" align="center" prop="className" />
      <el-table-column label="试卷名称" align="center" prop="paperName" />
      <el-table-column label="试卷总时长" align="center" prop="paperTime" />
      <el-table-column label="试卷总分" align="center" prop="paperScore" />
      <el-table-column label="操作" min-width="200px" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            plain
            size="mini"
            @click=" addRequestForm(scope.row.id)"
          >添加试题</el-button>
          <el-button
            type="primary"
            plain
            size="mini"
            @click="questionList(scope.row.id)"
          >预览</el-button>

          <el-button
            size="mini"
            type="danger"
            @click="handleDeleteRow(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <form-dialog ref="formDialogCom" @refreshDataList="refreshDataList" />
    <add-form-dialog ref="addFormDialogCom" @refreshDataList="refreshDataList" />
    <question-list ref="questionListDialogCom" @refreshDataList="refreshDataList" />
  </div>
</template>

<script>
import { listPage,delPaper} from '@/api/paper'
import addFormDialog from './addFormDialog'
import questionList from './questionList'
import formDialog from './formDialog'
export default {
  components: {
    formDialog,
    addFormDialog,
    questionList
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
      this.list = this.pageData.list
      this.listLoading = false
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
      this.$confirm('此操作将删除试题, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delPaper({ paperId: recordIds }).then((resp) => {
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
    },
    addRequestForm(id) {
      this.$refs.addFormDialogCom.addRequestForm(id)
    },
    questionList(id){
      this.$refs.questionListDialogCom.questionList(id)
    }
  }
}
</script>
<style scoped>
  .btn_group{
    float: right;
  }
</style>
