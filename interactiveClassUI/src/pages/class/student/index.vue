<template>
    <view>
        <view v-for="(item,i) in [1,2,3,4,5,6,7,8]" :key="i" class="kj_zlist">
            <view class="kj_list">
                <view class="kj_title">张三</view>
                <view style="line-height: 50px">签到状态：</view>
                <view style="line-height: 50px">已签到</view>
                <view class="kj_download" style="margin-left: 10px;line-height: 50px;  ">
                    <van-button color="#1E9FFF"  size="small">删除</van-button>
                </view>
            </view>
            <view class="line"></view>
        </view>
    </view>
</template>

<script>
    export default {
        name: "index.vue"
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