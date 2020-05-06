<template>
    <view>
        <view class="paperListTitle">
            <view style="margin-top: 10px;">
                <van-count-down :time="countDown" />
            </view>
            <view style="margin-top: 10px;">
                <van-button type="info" size="small" @click="subAnswer">提交</van-button>
            </view>
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
    import {subAnswer} from "@/api/userQuestion"
    export default {
        name: "paperList.vue",
        data(){
            return{
                countDown:120*60*60*1000,
                questionArr:[],
                paperId:'',
                answerArr:[]
            }
        },
        mounted(){
            this.queryPaperQuestion(this.paperId);
        },
        onLoad(option){
            this.paperId=option.paperId;
        },
        methods:{
            selectAnswer(id,option){
                for(var i=0;i<this.questionArr.length;i++){
                    if(this.questionArr[i].id==id){
                        this.answerArr[i].myAnswer=option;
                    }
                }
            },
            valueChange(event, id) {
                for(var i=0;i<this.questionArr.length;i++){
                    if(this.questionArr[i].id==id){
                        this.answerArr[i].myAnswer=event.detail;
                    }
                }
            },
            queryPaperQuestion(paperId){
                var params={
                    paperId:paperId
                }
                queryPaperQuestionById(params).then(resp=>{
                    this.questionArr=resp;
                    for(var i=0;i<this.questionArr.length;i++){
                        var arr={
                            questionId:this.questionArr[i].id,
                            myAnswer:''
                        }
                        this.answerArr.push(arr);
                    }
                })
            },
            subAnswer(){
                var data=JSON.stringify(this.answerArr);
                console.log(this.answerArr);
                subAnswer(data).then(resp=>{
                    console.log(resp);
                })
            }
        }
    }
</script>

<style scoped>
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