<template>
    <view>
        <view v-for="(item,i) in arrPaper2" :key="i">
            <view class="sj_xx" @click="paperListPage">
                <view class="m_row">
                    <text>{{item.paperName}}</text>
                    <text style="font-size: 12px;color: #666666;">{{item.paperTime}}</text>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    export default {
        name: "index",
        data(){
            return {
                arrPaper2:[
                    {
                        paperName:'122222',
                        paperTime:'120分钟'
                    }
                ]
            }
        },
        props:{
            arrPaper:{
                type:Array,
                default:[]
            }
        },
        methods:{
            paperListPage(){
                uni.navigateTo({
                    url: '/pages/class/paper/paperList'
                });
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
.m_row{
    display: flex;
    justify-content:space-between;
}
</style>