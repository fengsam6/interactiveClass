<template>
    <view>
        <view>
            <van-row>
                <van-col span="10">
                    <text class="user-name">{{userInfo.userName}}</text>
                </van-col>
                <van-col span="10" offset="4">
                    <van-image
                            round
                            width="6rem"
                            height="6rem"
                            :src="userInfo.avatar"
                    />
                </van-col>
            </van-row>
        </view>
        <van-cell-group>
            <van-cell title="我的课件库" value=">" />
            <van-cell title="我的试卷库" value=">" />
            <van-cell title="我的课件库" value=">" />
            <van-cell title="我的试卷库" value=">" />
        </van-cell-group>
    </view>
</template>

<script>
    import {doLogin} from "@/api/user"

    export default {
        data() {
            return {
                userInfo: {
                    userName: 'test',
                    avatar:'/static/images/user/cat.jpeg'
                }
            }
        },
        onLoad() {

        },
        methods: {}
    }
</script>

<style type="text/css" scoped>
    .user-name{
        margin-left: 5px;
        font-size: 16px;
    }
</style>
