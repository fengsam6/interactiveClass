<template>
  <el-dialog :visible.sync="dialogVisible" class="app-container" :title="formTitle" width="750px" height="560px" style="">
    <div>
      <div v-for="(item,i) in questionArr" :key="item.id">
        <div v-if="item.questionType==1">
          <div>
            {{i+1}}.{{item.questionName}}（）
          </div>
          <div>
            A、{{item.questionA}}
          </div>
          <div>
            B、{{item.questionB}}
          </div>
          <div>
            C、{{item.questionC}}
          </div>
          <div>
            D、{{item.questionD}}
          </div>
        </div>
        <div v-else>
          <div>
            {{i+1}}.{{item.questionName}}
          </div>
        </div>
      </div>
    </div>
  </el-dialog>
</template>

<script>
  import { queryPaperQuestionById } from '@/api/paper'
    export default {
        name: " ",
        data() {
          return {
            formTitle: '试题列表',
            dialogVisible: false,
            questionArr:[]
          }
        },
      mounted(){

      },
      methods:{
        showDialog() {
          this.dialogVisible = true
        },
        async questionList(paperId) {
         var params={
           paperId:paperId
         };
          this.dialogVisible = true;
          const data = await queryPaperQuestionById(params)
          this.questionArr = data.data
        },
      }

    }
</script>

<style scoped>

</style>
