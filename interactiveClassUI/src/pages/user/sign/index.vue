<template>
    <view>
        <view>
            <view v-if="update" class="signIn address">
                <view class="signIn_left">
                    {{maddress}}
                </view>
                <view class="signIn_right">
                    <van-button icon="location-o" type="info" size="small" @click="mgetLocation">重新获取</van-button>
                </view>
            </view>
            <view class="line"></view>
        </view>
        <view class="signIn">
            <view class="signIn_left">
                <view>
                    <van-icon name="clock-o" />上课
                </view>
                <view class="signIn_time">
                    08:30
                </view>
            </view>
            <view class="signIn_right">
                <van-button icon="success" type="info" size="small" @click="Dk(1)">
                    <view v-if="dkflag1">打卡</view>
                    <view>{{nowTime1}}</view>
                </van-button>
            </view>
        </view>
        <van-divider  custom-style="height:1px"/>
        <view class="signIn">
            <view class="signIn_left">
                <view>
                    <van-icon name="clock-o" />下课
                </view>
                <view class="signIn_time">
                    11:30
                </view>
            </view>
            <view class="signIn_right">
                <van-button icon="success" type="info" size="small" @click="Dk(2)">
                    <view v-if="dkflag2">打卡</view>
                    <view>{{nowTime2}}</view>
                </van-button>
            </view>
        </view>

    </view>
</template>

<script>
    import {getStoreUserInfo} from "@/api/user"

    export default {
        data() {
            return {
                formData: {
                    account: "",
                    password: "",
                    role: 1
                }
            }
        },
        onShow() {
            this.getUserInfo()
        },
        methods: {
            async getUserInfo() {
                this.userInfo = await getStoreUserInfo()
            },
            update() {
                console.log(this.formData)
            },
            onUserAccountChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.account = event.detail
            },
            onPasswordChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.password = event.detail
            },
        }
    }
</script>

<style type="text/css" scoped>
    .form {
        margin-top: 150px;
    }

    .btn_info {
        width: 200px;
        margin: 0 auto;
    }

    .form_field {
        margin: 10px 0;
    }

    .btn_margin {
        margin-left: 10px;
    }
</style>
