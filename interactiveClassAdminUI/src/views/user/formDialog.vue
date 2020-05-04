<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle">
    <el-form ref="formCom" :model="form" :rules="rules" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="用户姓名" prop="name">
        <el-input v-model="form.name" class="formItem" />
      </el-form-item>
      <el-form-item label="用户编号" prop="userNum">
        <el-input v-model="form.userNum" :disabled="!isAddOPt" class="formItem" />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.sex" placeholder="选择用户性别" class="formItem">
          <el-option label="男" :value="0" />
          <el-option label="女" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="密码" prop="password" >
        <el-input v-model="form.password" class="formItem" />
      </el-form-item>
      <el-form-item label="角色" prop="roleId">
        <el-select v-model="form.roleId" placeholder="选择用户角色" class="formItem">
          <el-option label="学生" :value="1" />
          <el-option label="老师" :value="2" />
        </el-select>
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
import { getUserInfoById, add, updateUserInfo } from '@/api/user'

export default {
  data() {
    return {
      formTitle: '编辑用户',
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
    async getUserById(userId) {
      const data = await getUserInfoById(userId)
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
      this.formTitle = '添加用户'
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
        const data = await updateUserInfo(this.form)
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
    width: 160px;
  }
  .btn_opt{
    text-align: center;
  }
.line{
  text-align: center;
}
</style>

