<template>
    <view>
        <view v-for="(item,i) in arrPaper" :key="i">
            <view class="sj_xx" @click="paperListPage(item.id)">
                <view class="m_row">
                    <view>{{item.paperName}}</view>
                    <view style="font-size: 12px;color: #666666;">{{item.paperTime}}</view>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    import {queryMyPaper} from "@/api/paper"
    export default {
        name: "index",
        data(){
            return {
                arrPaper:[]
            }
        },
        beforeMount(){
            this.queryPaper();
        },
        methods:{
            paperListPage(paperId){
                uni.navigateTo({
                    url: '/pages/user/paper/paperList?paperId='+paperId
                });
            },
            queryPaper(){
                var data={
                    pageNum:1,
                    pageSize:30
                }
                queryMyPaper(data).then(resp=>{
                    this.arrPaper=resp;
                });
            }
        }

    }
</script>

<style scoped>
    .sj_xx{
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
    .m_row{
        display: flex;
        justify-content:space-between;
    }
</style>