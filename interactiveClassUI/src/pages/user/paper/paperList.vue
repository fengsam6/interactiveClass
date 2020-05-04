<template>
    <view>
        <view v-for="(item,i) in questionArr" :key="i">
            <view v-if="item.questionType==1">
                <view class="sj_st">{{i+1}}、{{item.questionName}}（ ）</view>
                <view class="sj_xx">
                    <view>
                        <text>A、</text>
                        <text>{{item.questionA}}</text>
                    </view>
                    <view>
                        <text>B、</text>
                        <text>{{item.questionB}}</text>
                    </view>
                    <view>
                        <text>C、</text>
                        <text>{{item.questionC}}</text>
                    </view>
                    <view>
                        <text>D、</text>
                        <text>{{item.questionD}}</text>
                    </view>
                </view>
            </view>
            <view v-else>
                <view class="sj_st">{{i+1}}、{{item.questionName}}</view>
            </view>
        </view>
    </view>
</template>

<script>
    import {queryPaperQuestionById} from "@/api/paper"
    export default {
        name: "paperList.vue",
        data(){
            return{
                questionArr:[],
                paperId:''
            }
        },
        mounted(){
            this.queryPaperQuestion(this.paperId);
        },
        onLoad(option){
            this.paperId=option.paperId;
        },
        methods:{
            queryPaperQuestion(paperId){
                var params={
                    paperId:paperId
                }
                queryPaperQuestionById(params).then(resp=>{
                    this.questionArr=resp;
                })
            }
        }
    }
</script>

<style scoped>
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