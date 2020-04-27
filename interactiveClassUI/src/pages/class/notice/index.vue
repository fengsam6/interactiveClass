<template>
    <view v-for="(item,i) in noticeArr" :key="i">
        <van-row>
            <view class="notice_title">
                {{item.noticeTitle}}
            </view>
            <view class="notice_content">
                {{item.noticeContent}}
            </view>
            <van-col span="10" offset="14">
				<view class="notice_foot">
					{{item.publishTime}}
				</view>
            </van-col>
            </van-row>
            <van-divider  custom-style="height:1px"/>
        </view>

</template>

<script>
    export default {
        name: "index"
    }
</script>

<style scoped>

</style>