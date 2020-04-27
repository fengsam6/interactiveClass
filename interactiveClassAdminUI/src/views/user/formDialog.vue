<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle">
    <el-form ref="form" :model="form" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="用户姓名">
        <el-input v-model="form.name" class="formItem" />
      </el-form-item>
      <el-form-item label="用户编号">
        <el-input v-model="form.num" :disabled="true" class="formItem" />
      </el-form-item>
      <el-form-item label="Activity zone">
        <el-select v-model="form.region" placeholder="please select your zone" class="formItem">
          <el-option label="Zone one" value="shanghai" />
          <el-option label="Zone two" value="beijing" />
        </el-select>
      </el-form-item>
      <el-row>
        <el-button type="primary" @click="onSubmit">修改</el-button>
        <el-button @click="onCancel">关闭</el-button>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
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
    onCancel() {
        this.dialogVisible = false
    }
  }
}
</script>

<style scoped>
  .formItem{
    width: 160px;
  }
.line{
  text-align: center;
}
</style>

