<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle" width="60%">
    <el-form ref="form" :model="form" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="课程名称">
        <el-input v-model="form.courseName" class="formItem" />
      </el-form-item>
      <el-form-item label="班级名称">
        <el-select v-model="form.classNameList" multiple placeholder="请选择班级" class="formItem">
          <el-option v-for="(className,index) in classNames" :label="className" :value="className" :key="index"/>
        </el-select>
      </el-form-item>
      <el-form-item label="课程学分">
        <el-input v-model="form.courseScore" class="formItem" />
      </el-form-item>
      <el-form-item label="上课人数">
        <el-input v-model="form.classNum" class="formItem" />
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
      <div>
        <el-form-item label="附件一">
          <el-upload
            class="upload-demo"
            :action="uploadUrl"
            multiple
            on-success="uploadPPTSuccess"
            :limit="3"
            :file-list="fileList"
          >
            <el-button size="small" type="primary">上传教学PPT</el-button>
            <!--          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
          </el-upload>
        </el-form-item>
        <el-form-item label="附件二">
          <el-upload
            class="upload-demo"
            :action="uploadUrl"
            multiple
            on-success="uploadVideoSuccess"
            :limit="3"
            :file-list="fileList"
          >
            <el-button size="small" type="primary">上传教学视频</el-button>
            <!--          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
          </el-upload>
        </el-form-item>
      </div>
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
import { addCourse } from '@/api/course'
import { listClassByUserId } from '@/api/class'

import fileRequest from '@/utils/fileRequest'
export default {
  data() {
    return {
      formTitle: '编辑用户',
      dialogVisible: false,
      labelPosition: 'right',
      isAddOPt: true,
      classNames: [],
      resourceNum: 0,
      fileList: [],
      uploadUrl: fileRequest.fileUploadUrl,
      dateFormat: 'yyyy-MM-dd hh:mm:ss',
      form: {
        name: '',
        region: '',
        desc: ''
      }
    }
  },
  mounted() {
    this.listClassesByUserId()
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    showDialog() {
      this.dialogVisible = true
    },
    async  listClassesByUserId() {
      const data = await listClassByUserId()
      const classes = data.data
      for (let i = 0; i < classes.length; i++) {
        this.classNames.push(classes[i].className)
      }
    },
    uploadPPTSuccess(response, file, fileList) {
      const data = response.data
      this.form.resourcePath[this.resourceNum] = data
      this.resourceNum++
    },
    uploadVideoSuccess(response, file, fileList) {
      const data = response.data
      this.form.resourcePath[this.resourceNum] = data
      this.resourceNum++
    },
    async editForm(userId) {
      this.formTitle = '修改课程'
      // const data = await getUserInfoById(userId)
      // this.form = data.data
      this.isAddOPt = false
      this.dialogVisible = true
    },
    addForm() {
      this.formTitle = '添加课程'
      this.isAddOPt = true
      this.dialogVisible = true
    },
    async doSave() {
      if (this.isAddOPt) {
        const data = await addCourse(this.form)
      }
      this.dialogVisible = false
      this.$emit('refreshDataList')
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

