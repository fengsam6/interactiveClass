<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle" width="750px" height="560px" style="">
    <el-form ref="formCom" :model="form" label-width="120px" :inline="true" :label-position="labelPosition">
      <el-form-item label="选择题">
        <el-switch v-model="form.select" @change="switchStatus"></el-switch>
      </el-form-item>
      <el-form-item label="试题名称">
        <el-input v-model="form.questionName" :placeholder="mplaceholder" type="textarea" :rows="4"class="formItem" />
      </el-form-item>
      <el-form-item label="A选项" :style="mstyle">
        <el-input v-model="form.questionA" class="formItem" />
      </el-form-item>
      <el-form-item label="B选项" :style="mstyle">
        <el-input v-model="form.questionB" class="formItem" />
      </el-form-item>
      <el-form-item label="C选项" :style="mstyle">
        <el-input v-model="form.questionC" class="formItem" />
      </el-form-item>
      <el-form-item label="D选项" :style="mstyle">
        <el-input v-model="form.questionD" class="formItem" />
      </el-form-item>
      <el-form-item label="正确选项" :style="mstyle">
        <el-select v-model="form.questionAnswer" placeholder="请选择正确答案">
          <el-option label="A" value="A"></el-option>
          <el-option label="B" value="B"></el-option>
          <el-option label="C" value="C"></el-option>
          <el-option label="D" value="D"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="分值">
        <el-select v-model="form.score" placeholder="请选择该题分值">
          <el-option label="2" value="2"></el-option>
          <el-option label="4" value="4"></el-option>
          <el-option label="5" value="5"></el-option>
          <el-option label="10" value="10"></el-option>
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
  import { addQuestion } from '@/api/paper'
  import { listClassByUserId } from '@/api/class'
  export default {
    data() {
      return {
        formTitle: '编辑用户',
        dialogVisible: false,
        mstyle:{
        },
        mplaceholder:"请输入你的问题",
        labelPosition: 'right',
        isAddOPt: true,
        classNames: [],
        fileList: [],
        dateFormat: 'yyyy-MM-dd hh:mm:ss',
        form: {
          paperId:'',
          questionType:1,
          select:true
        },
        defaultForm: {
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
      switchStatus(){
        if(this.form.select){
          this.mstyle={};
          this.form.questionType=1;
          this.mplaceholder="请输入你的问题";
        }else{
          this.mstyle={
            display:'none'
          };
          this.mplaceholder="请输入你的问题";
          this.form.questionType=2;
        }

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
      async editForm(userId) {
        this.formTitle = '修改课程'
        // const data = await getUserInfoById(userId)
        // this.form = data.data
        this.isAddOPt = false
        this.dialogVisible = true
      },
      addRequestForm(paperId) {
        debugger
        this.formTitle = '添加试题'
        this.form.paperId=paperId;
        this.dialogVisible = true
      },
      async doSave() {
        let data = ''
        debugger
        if (this.isAddOPt) {
          data = await addQuestion(this.form)
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
   width: 560px;
  }
  .line{
    text-align: center;
  }
</style>

