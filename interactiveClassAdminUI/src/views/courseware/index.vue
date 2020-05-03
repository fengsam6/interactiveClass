<template>
  <div class="app-container">
    <div class="btn_group">
      <el-button type="primary" plain size="small" @click="addForm">添加</el-button>
      <el-button size="small" type="danger">批量删除</el-button>
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
      <el-table-column type="index" width="80" align="center" />
      <!--      <el-table-column label="用户id" prop="id" width="280px" />-->
      <el-table-column label="课程名称" prop="courseName" />
      <el-table-column label="班级名称" align="center" prop="className" />
      <el-table-column label="上课人数" align="center" prop="classNum" />
      <el-table-column label="上课时间" align="center" prop="beginTime" />
      <el-table-column align="center" label="下课时间" prop="endTime" />
      <el-table-column align="center" label="教学ppt" prop="endTime" />
      <el-table-column align="center" label="教学视频" prop="endTime" />
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
            @click="handleDelete(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <form-dialog ref="formDialogCom" @refreshDataList="refreshDataList" />
  </div>
</template>

<script>
import { listPage } from '@/api/course'
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
      const data = await listPage()
      this.pageData = data.data
      debugger
      this.list = this.pageData.list
      this.listLoading = false
    },
    refreshDataList() {
      this.listPageData()
    },
    editForm(id) {
      debugger
      this.$refs.formDialogCom.editForm(id)
    },
    addForm(id) {
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
