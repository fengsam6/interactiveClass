<template>
    <view>
        <view v-for="(item,i) in studentList" :key="i" class="kj_zlist">
            <view class="kj_list">
                <view class="kj_title">{{item.stuName}}</view>
                <view style="line-height: 50px">签到状态：</view>
                <view style="line-height: 50px">{{item.signStatus}}</view>
                <view class="kj_download" style="margin-left: 10px;line-height: 50px;  ">
                    <van-button color="#1E9FFF"  size="small" @click="selefAnalysis(item.stuId)">分析</van-button>
                </view>
            </view>
            <view class="line"></view>
        </view>
        <van-dialog
                use-slot
                title="张三的平时分析"
                :show="analysisBtn"
                @close="analysisBtn=false"
        >
           <view>
               <view>考试得分：</view>
               <view>发言次数</view>
               <view>连续签到天数</view>
           </view>
        </van-dialog>
    </view>
</template>

<script>
    import {showClassUsers} from "@/api/classUser"
    export default {
        name: "index.vue",
        data(){
           return {
               analysisBtn:false,
               studentCom:{
                   classId:'',
                   courseId:''
               },
               studentList:[]
           }
        },
        mounted(){
            this.queryStuList();
        },
        onLoad(option) {
            const dataItem = JSON.parse(decodeURIComponent(option.item));
            this.studentCom.classId=dataItem.item.id;
            this.studentCom.courseId=dataItem.courseId;
        },
        methods:{
            queryStuList(){
                showClassUsers(this.studentCom).then(resp => {
                    this.studentList=resp;
                });
            },
            selefAnalysis(stuId){
              //  this.successAlert("尽情期待...");
                this.analysisBtn=true;
                console.log("移除人员:  "+stuId);
            }
        }
    }
</script>

<style scoped>
    .kj_zlist{
        box-shadow: 0 4px 8px 0 rgba(28,31,33,.1);
        margin: 10px auto;
        border: 1px solid #F0F0F0;
        border-radius: 4px;
    }
    .kj_list{
        display: flex;
        margin-bottom: 2px;
    }
    .kj_title{
        flex: 1;
        height: 50px;
        line-height: 50px;
        color:black;
        margin-left: 10px;

    }
</style>