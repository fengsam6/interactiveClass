<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle" width="60%">
    <el-form ref="formCom" :model="form" :rules="rules" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="课程名称" prop="courseName">
        <el-input v-model="form.courseName" class="formItem" />
      </el-form-item>
      <el-form-item label="班级名称" prop="classNames">
        <el-select v-model="form.classNameList" multiple placeholder="请选择班级" class="formItem">
          <el-option v-for="(className,index) in classNames" :key="index" :label="className" :value="className" />
        </el-select>
      </el-form-item>
      <el-form-item label="课程学分" prop="courseScore">
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
            :on-success="uploadPPTSuccess"
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
            :on-success="uploadVideoSuccess"
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
import { addCourse, updateCourse, getDetailById } from '@/api/course'
import { listClassByUserId } from '@/api/class'

import fileRequest from '@/utils/fileRequest'
export default {
  data() {
    return {
      formTitle: '',
      dialogVisible: false,
      labelPosition: 'right',
      isAddOPt: true,
      classNames: [],
      resourceNum: 0,
      fileList: [],
      uploadUrl: fileRequest.fileUploadUrl,
      dateFormat: 'yyyy-MM-dd hh:mm:ss',
      form: {
        pptResources: [{}],
        videoResources: [{}]
      },
      defaultForm: {
        pptResources: [{}],
        videoResources: [{}]
      },
      rules: {
        courseName: [
          { required: true, message: '课程名称不能为空', trigger: 'blur,change' }
        ],
        classNames: [
          { required: false, message: '班级名称不能为空', trigger: 'change' }
        ],
        courseScore: [
          { required: true, message: '课程学分不能为空', trigger: 'change' }
        ]
      }
    }
  },
  mounted() {
    this.listClassesByUserId()
  },
  methods: {
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
      //   resourceType=1, ppt、word资源
      const resource = { resourcePath: data, resourceType: 1, resourceDes: 'ppt、word资源' }
      this.form.pptResources.push(resource)
    },
    uploadVideoSuccess(response, file, fileList) {
      const data = response.data
      //   resourceType=2, 视频资源
      const resource = { resourcePath: data, resourceType: 2, resourceDes: '视频资源' }
      this.form.videoResources.push(resource)
    },
    async editForm(courseId) {
      this.formTitle = '修改课程'
      const data = await getDetailById(courseId)
      this.form = data.data
      this.isAddOPt = false
      this.dialogVisible = true
    },
    addForm() {
      this.formTitle = '添加课程'
      this.isAddOPt = true
      this.dialogVisible = true
      this.reset()
    },
    checkParam(formName) {
      let pass = true
      this.$refs[formName].validate((valid) => {
        if (valid) {
          pass = true
        } else {
          console.log('请先填写完表单必填选!!')
          this.$message.error('请先填写完表单必填选!!')
          pass = false
        }
      })
      return pass
    },
    async doSave() {
      const pass = this.checkParam('formCom')
      if (!pass) {
        return
      }
      let data = ''
      if (this.isAddOPt) {
        data = await addCourse(this.form)
        this.$message.success(data.data)
      } else {
        data = await updateCourse(this.form)
        this.$message.success(data.data)
      }

      this.dialogVisible = false
      this.$emit('refreshDataList')
    },
    reset() {
      this.form = this.defaultForm
      this.$refs.formCom.resetFields()
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

