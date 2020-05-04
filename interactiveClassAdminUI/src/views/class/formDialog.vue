<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle">
    <el-form ref="formCom" :model="form" :rules="rules" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="班级名称" prop="className">
        <el-input v-model="form.className" class="formItem" />
      </el-form-item>
      <el-form-item label="班级人数" prop="classNum">
        <el-input v-model="form.classNum" class="formItem" />
      </el-form-item>
      <el-form-item v-if="!isAddOPt" label="班级邀请码" prop="classCreateCode">
        <el-input v-model="form.classCreateCode" :disabled="!isAddOPt" class="formItem" />
      </el-form-item>
      <el-form-item label="班级简介" prop="classIntroduce">
        <el-input
          v-model="form.classIntroduce"
          type="textarea"
          :rows="3"
          class="textareaItem"
          placeholder="请输入班级简介"
        />
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
import { getClassById, add, update } from '@/api/class'

export default {
  data() {
    return {
      formTitle: '',
      dialogVisible: false,
      labelPosition: 'right',
      isAddOPt: true,
      form: {},
      rules: {
        name: [
          { required: true, message: '用户名称不能为空', trigger: 'blur,change' }
        ],
        userNum: [
          { required: true, message: '用户编号不能为空', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    showDialog() {
      this.dialogVisible = true
    },
    async editForm(userId) {
      this.formTitle = '编辑班级'
      const data = await getClassById(userId)
      this.form = data.data
      this.isAddOPt = false
      this.dialogVisible = true
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
    onCancel() {
      this.dialogVisible = false
    },
    addForm() {
      this.formTitle = '添加班级'
      this.isAddOPt = true
      this.dialogVisible = true
      this.reset()
    },
    reset() {
      this.form = {}
      this.$refs.formCom.resetFields()
    },
    async doSave() {
      const pass = this.checkParam('formCom')
      if (!pass) {
        return
      }
      if (this.isAddOPt) {
        const data = await add(this.form)
        this.$message.success(data.data)
      } else {
        const data = await update(this.form)
        this.$message.success(data.data)
      }
      this.dialogVisible = false
      this.$emit('refreshDataList')
    }
  }
}
</script>

<style scoped>
  .formItem{
    width: 180px;
  }
  .textareaItem{
    display: inline-block;
    width: 490px;
    margin: 0 auto;
  }
  .btn_opt{
    width: 260px;
    margin: 0 auto;
  }
.line{
  text-align: center;
}
</style>

