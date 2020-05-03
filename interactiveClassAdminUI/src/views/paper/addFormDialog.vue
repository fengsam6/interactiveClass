<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle" width="60%">
    <el-form ref="formCom" :model="form" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="试题名称">
        <el-input v-model="form.paperName" class="formItem" />
      </el-form-item>
      <el-form-item label="A选项">
        <el-input v-model="form.requestA" class="formItem" />
      </el-form-item>
      <el-form-item label="B选项">
        <el-input v-model="form.requestB" class="formItem" />
      </el-form-item>
      <el-form-item label="C选项">
        <el-input v-model="form.requestC" class="formItem" />
      </el-form-item>
      <el-form-item label="D选项">
        <el-input v-model="form.requestD" class="formItem" />
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
          courseResources: [{}]
        },
        defaultForm: {
          courseResources: [{}]
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
        //   resourceType=1, ppt、word资源
        const resource = { resourcePath: data, resourceType: 1, resourceDes: 'ppt、word资源' }
        this.form.courseResources.push(resource)
      },
      uploadVideoSuccess(response, file, fileList) {
        const data = response.data
        //   resourceType=2, 视频资源
        const resource = { resourcePath: data, resourceType: 2, resourceDes: '视频资源' }
        this.form.courseResources.push(resource)
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
        this.reset()
        this.isAddOPt = true
        this.dialogVisible = true
      },
      async doSave() {
        let data = ''
        if (this.isAddOPt) {
          data = await addCourse(this.form)
        }
        this.$message.success(data.data)
        this.dialogVisible = false
        this.$emit('refreshDataList')
      },
      reset() {
        this.form = this.defaultForm
      },
      onCancel() {
        this.dialogVisible = false
      }
    }
  }
</script>

<style scoped>
  .formItem{
   /* width: 200px;*/
  }
  .line{
    text-align: center;
  }
</style>

