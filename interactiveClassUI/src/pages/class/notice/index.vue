<template>
    <view>
        <view v-for="(item,i) in [1,2,3,4]" :key="i">
            <van-row>
                <view class="notice_title">
                    课堂纪律
                </view>
                <view class="notice_content">
                    我是一个好学生， 我是一个好学生， 我是一个好学生， 我是一个好学生。。。
                </view>
                <van-col span="10" offset="14">
                    <view class="notice_foot">
                        2020-04-10 23:21:51
                    </view>
                </van-col>
            </van-row>
            <van-divider  custom-style="height:1px"/>
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