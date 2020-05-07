<template>
    <view>
        <view class="paperListTitle" v-if="hasPaper">
            <view style="margin-top: 10px;" v-show="message=='提交'">
                <van-count-down :time="countDown" />
            </view>
            <view class="score" v-show="message=='已提交'">
                您的得分:{{userScore.totalScore}}
            </view>
            <view style="margin-top: 10px; margin-right: 8px;">
                <van-button type="info" size="small" @click="subAnswer">{{message}}</van-button>
            </view>
        </view>
        <view v-else>
            该试卷还未添加题目
        </view>
        <van-divider />
        <view v-for="(item,i) in questionArr" :key="i">
            <view v-if="item.questionType==1">
                <view class="sj_st">{{i+1}}、{{item.questionName}}（{{answerArr[i].myAnswer}} ）</view>
                <view class="sj_xx">
                    <view @click="selectAnswer(item.id,'A')">
                        <text>A、</text>
                        <text>{{item.questionA}}</text>
                    </view>
                    <view @click="selectAnswer(item.id,'B')">
                        <text>B、</text>
                        <text>{{item.questionB}}</text>
                    </view>
                    <view @click="selectAnswer(item.id,'C')">
                        <text>C、</text>
                        <text>{{item.questionC}}</text>
                    </view>
                    <view @click="selectAnswer(item.id,'D')">
                        <text>D、</text>
                        <text>{{item.questionD}}</text>
                    </view>
                </view>
            </view>
            <view v-else>
                <view class="sj_st">{{i+1}}、{{item.questionName}}</view>
                <view>
                    <van-field
                            :value="answerArr[i].myAnswer"
                            label="你的答案是"
                            icon="question-o"
                            placeholder="请用&p区别每个空的答案"
                            border=true
                            required
                            @change="valueChange($event,item.id)"
                    />
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    import {queryPaperQuestionById} from "@/api/paper"
    import {subAnswer,queryMyResult} from "@/api/userQuestion"
    import {addUserScore,queryUserScore} from "@/api/userScore"
    export default {
        name: "paperList.vue",
        data(){
            return{
                countDown:120*60*60*1000,
                questionArr:[],
                totalScore:0,
                message:'提交',
                paperId:'',
                hasPaper:true,
                answerArr:[],
                userScore:null,
                answerResult:[]
            }
        },
        mounted(){
            this.queryPaperQuestion(this.paperId);
        },
        onLoad(option){
            this.paperId=option.paperId;
        },
        methods:{
            queryMyResult(){
                var data={
                    paperId:this.paperId
                }
                queryMyResult(data).then(resp=>{
                    this.answerResult=resp;
                    if(this.answerResult.length>0){
                        this.message='已提交';
                    }
                    for(var i=0;i<this.answerResult.length;i++){
                        this.answerArr[i]. myAnswer=this.answerResult[i].myAnswer;
                    }
                })

            },
            selectAnswer(id,option){
                for(var i=0;i<this.questionArr.length;i++){
                    if(this.questionArr[i].id==id){
                        this.answerArr[i].myAnswer=option;
                        if(option==this.questionArr[i].questionAnswer){
                            this.answerArr[i].score=this.questionArr[i].score;
                        }else{
                            this.answerArr[i].score='0';
                        }
                    }
                }
            },
            valueChange(event, id) {
                for(var i=0;i<this.questionArr.length;i++){
                    if(this.questionArr[i].id==id){
                        this.answerArr[i].myAnswer=event.detail;
                        if(event.detail==this.questionArr[i].questionAnswer){
                            this.answerArr[i].score=this.questionArr[i].score;
                        }else{
                            this.answerArr[i].score='0';
                        }
                    }
                }
            },
            queryPaperQuestion(paperId){
                var params={
                    paperId:paperId
                }
                queryPaperQuestionById(params).then(resp=>{
                    this.questionArr=resp;
                    if(this.questionArr.length==0){
                        this.hasPaper=false;
                    }
                    for(var i=0;i<this.questionArr.length;i++){
                        var arr={
                            questionId:this.questionArr[i].id,
                            myAnswer:'',
                            score:'0'
                        }
                        this.answerArr.push(arr);
                    }
                }).then(resp=>{
                    this.queryMyResult();
                }).then(resp=>{
                    var params={
                        paperId:paperId
                    }
                    queryUserScore(params).then(resp=> {
                        this.userScore = resp;
                    })
                })
            },
            subAnswer(){
                if(this.message=='已提交'){
                    this.successAlert("不可重复答题");
                    return false;
                }
                var resultScore=0;
                for(var i=0;i<this.questionArr.length;i++){
                    if(this.answerArr[i].myAnswer==this.questionArr[i].questionAnswer){
                        resultScore=Number(this.questionArr[i].score)+Number(resultScore);
                    }else{
                        resultScore=Number(resultScore)+0;
                    }
                }
                var data=JSON.stringify(this.answerArr);
                console.log(this.answerArr);
                var params={
                    paperId:this.questionArr[0].paperId,
                    totalScore: resultScore
                }
                subAnswer(data).then(resp=>{
                    this.message="已提交";
                }).then(resp=>{
                    addUserScore(params).then(resp=> {
                    }).then(resp=>{
                        queryUserScore(params).then(resp=> {
                            this.userScore = resp;
                        })
                    })
                    })

            }
        }
    }
</script>

<style scoped>
    .score{
        margin-top: 15px;
        font-size: 14px;
        margin-left: 8px;

    }
    .paperListTitle{
        display: flex;
        justify-content:space-between;
    }
    .sj_xx view{
        min-height: 46px;
        background: #fff;
        box-shadow: 0 4px 8px 0 rgba(28,31,33,.1);
        border-radius: 8px;
        margin-bottom: 20px;
        padding: 20px 12px;
        box-sizing: border-box;
    }
    .sj_xx text,.sj_st{
        font-family: PingFangSC-Regular;
        font-size: 14px;
        color: #1c1f21;
        letter-spacing: 0;
        line-height: 22px;
        word-break: break-word;
    }
</style>