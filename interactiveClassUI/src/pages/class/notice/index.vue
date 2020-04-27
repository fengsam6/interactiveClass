<template>
    <view>
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
    </view>
</template>

<script>
    import {addNotice,queryNotice} from "@/api/notice"
    import {getStoreUserInfo, saveUserInfoStore,getUserInfo} from '@/api/user'
    export default {
        name: "index",
        props:{
            noticeArr:{
                type:Array,
                default:[]
            }
        },
        data() {
            return {

            }
        },
        mounted() {

        },
        methods: {
            publishGg() {
                addNotice(this.notice).then(resp => {
                    this.successAlert("添加通知成功");
                    this.queryNotice();
                });
            },
        }
    }
</script>

<style scoped>

</style>