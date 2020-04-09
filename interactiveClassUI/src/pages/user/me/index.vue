<template>
    <view>
        <view class="top_wrap">
            <van-row >
                <van-col span="12">
                    <view class="top_aside">
                        <view>
                            <text class="user-name">{{userInfo.userName}}</text>
                        </view>
                        <view>
                            <van-button type="default" size="small">编辑个人资料</van-button>
                        </view>
                    </view>
                </van-col>
                <van-col span="8" offset="4">
                    <van-image
                            round
                            width="5rem"
                            height="5rem"
                            :src="userInfo.avatar"
                    />
                </van-col>
            </van-row>
        </view>
        <view class="me_main">
            <van-cell-group>
                <van-cell title="我的课件库" value=">"/>
                <van-cell title="我的试卷库" value=">"/>
                <van-cell title="签到" value=">"/>
                <van-cell title="帮助" value=">"/>
            </van-cell-group>
        </view>

    </view>
</template>

<script>
    import {doLogin} from "@/api/user"

    export default {
        data() {
            return {
                userInfo: {
                    userName: 'test',
                    avatar: '/static/images/user/cat.jpeg'
                }
            }
        },
        onLoad() {

        },
        methods: {}
    }
</script>

<style type="text/css" scoped>
    .top_wrap{
        margin-top: 10px;
    }
    .top_aside {
        margin-left: 15px;
    }

    .user-name {
        font-size: 16px;
    }

    .me_main {
        margin-top: 25px;
    }
</style>
