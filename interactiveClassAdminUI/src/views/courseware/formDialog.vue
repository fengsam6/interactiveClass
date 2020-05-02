<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle" width="60%">
    <el-form ref="form" :model="form" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="课程名称">
        <el-input v-model="form.courseName" class="formItem" />
      </el-form-item>
      <el-form-item label="班级名称">
        <el-select v-model="form.className" placeholder="please select your zone" class="formItem">
          <el-option label="Zone one" value="shanghai" />
          <el-option label="Zone two" value="beijing" />
        </el-select>

      </el-form-item>
      <el-form-item label="课程学分">
        <el-input v-model="form.courseScore" class="formItem" />
      </el-form-item>
      <el-form-item label="上课时间">
        <el-date-picker
          v-model="form.beginTime"
          class="formItem"
          type="datetime"
          :format="dateFormat"
          :value-format="dateFormat"
          placeholder="选择上课时间"
          align="right"
        />
      </el-form-item>
      <el-form-item label="下课时间">
        <el-date-picker
          v-model="form.endTime"
          class="formItem"
          :format="dateFormat"
          :value-format="dateFormat"
          type="datetime"
          placeholder="选择下课时间"
          align="right"
        />
      </el-form-item>
      <el-form-item label="附件一">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
        >
          <el-button size="small" type="primary">上传教学PPT</el-button>
          <!--          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
        </el-upload>
      </el-form-item>
      <el-form-item label="附件二">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
        >
          <el-button size="small" type="primary">上传教学视频</el-button>
          <!--          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
        </el-upload>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <div class="btn_opt">
        <el-button @click="onCancel">取 消</el-button>
        <el-button type="primary" @click="doSave">确 定</el-button>
      </div>
    </div>
  </el-dialog>
</template>

<script>
import { getUserInfoById } from '@/api/user'

export default {
  data() {
    return {
      formTitle: '编辑用户',
      dialogVisible: false,
      labelPosition: 'right',
      fileList: [],
      dateFormat: 'yyyy-MM-dd hh:mm:ss',
      form: {
        name: '',
        region: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    showDialog() {
      this.dialogVisible = true
    },
    async editForm(userId) {
      this.formTitle = '修改课程'
      // const data = await getUserInfoById(userId)
      // this.form = data.data
      debugger
      this.dialogVisible = true
    },
    addForm(userId) {
      this.formTitle = '添加课程'
      this.dialogVisible = true
    },
    doSave() {

    },
    onCancel() {
      this.dialogVisible = false
    }
  }
}
</script>

<style scoped>
  .formItem{
    width: 200px;
  }
.line{
  text-align: center;
}
</style>

