<template>
    <view>
        <view v-for="(item,i) in [1,2,3,4,5,6,7,8]" :key="i" class="kj_zlist">
            <view class="kj_list">
                <view class="kj_title">JAVA课程PPT</view>
                <view class="kj_yl">
                    <van-button type="primary" size="small" @click="preview">预览</van-button>
                </view>
                <view class="kj_download" style="margin-right: 24px;">
                    <van-button color="#1E9FFF"  size="small">下载</van-button>
                </view>
            </view>
            <view class="line"></view>
        </view>
    </view>
</template>

<script>
    export default {
        name: "index"
    }
</script>

<style scoped>

</style>