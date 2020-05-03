<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle">
    <el-form ref="form" :model="form" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="用户姓名">
        <el-input v-model="form.name" class="formItem" />
      </el-form-item>
      <el-form-item label="用户编号">
        <el-input v-model="form.userNum" :disabled="true" class="formItem" />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.sex" placeholder="please select your zone" class="formItem">
          <el-option label="男" :value="0" />
          <el-option label="女" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" class="formItem" />
      </el-form-item>
      <el-form-item label="角色">
        <el-select v-model="form.roleId" placeholder="please select your zone" class="formItem">
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
import { getUserInfoById } from '@/api/user'

export default {
  data() {
    return {
      formTitle: '编辑用户',
      dialogVisible: false,
      labelPosition: 'right',
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
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
    async getUserById(userId) {
      const data = await getUserInfoById(userId)
      this.form = data.data
      debugger
      this.dialogVisible = true
    },
    onCancel() {
      this.dialogVisible = false
    },
    addForm(userId) {
      this.formTitle = '添加用户'
      this.dialogVisible = true
    },
    doSave() {

    }
  }
}
</script>

<style scoped>
  .formItem{
    width: 160px;
  }
  .btn_opt{
    width: 260px;
    margin: 0 auto;
  }
.line{
  text-align: center;
}
</style>

