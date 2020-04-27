<template>
    <view>
        <view>
            <view v-if="update" class="signIn address">
                <view class="signIn_left">
                    {{mapAddress}}
                </view>
                <view class="signIn_right">
                    <van-button icon="location-o" type="info" size="small" @click="getMapLocation">重新获取</van-button>
                </view>
            </view>
            <view class="line"></view>
        </view>
        <view class="signIn">
            <view class="signIn_left">
                <view>
                    <van-icon name="clock-o"/>
                    上课
                </view>
                <view class="signIn_time">
                    08:30
                </view>
            </view>
            <view class="signIn_right">
                <van-button icon="success" type="info" size="small" @click="sign(1)">
                    <view v-if="dkflag1">打卡</view>
                    <view>{{nowTime1}}</view>
                </van-button>
            </view>
        </view>
        <van-divider custom-style="height:1px"/>
        <view class="signIn">
            <view class="signIn_left">
                <view>
                    <van-icon name="clock-o"/>
                    下课
                </view>
                <view class="signIn_time">
                    11:30
                </view>
            </view>
            <view class="signIn_right">
                <van-button icon="success" type="info" size="small" @click="sign(2)">
                    <view v-if="dkflag2">打卡</view>
                    <view>{{nowTime2}}</view>
                </van-button>
            </view>
        </view>

    </view>
</template>

<script>
    var moment = require('moment');
    var QQMapWX = require('@/lib/qqmap-wx-jssdk.min.js');
    var qqmapsdk = new QQMapWX({
        key: 'KYVBZ-Y7UWW-PWVRO-R4IK5-5BIT2-RBFVX'
    });
    export default {
        data() {
            return {
                mapAddress: '',
                update: true,
                dkflag1: true,
                dkflag2: true,
                nowTime1: '',
                nowTime2: '',

            }
        },
        onShow() {
        },
        onLoad() {
            this.getLocal();
        },
        methods: {
            getLocal() {
                uni.getLocation({
                    type: 'wgs84',
                    success: () => {
                        qqmapsdk.reverseGeocoder({
                            success: function (addressRes) {
                                var address = addressRes.result.formatted_addresses.recommend;
                                // console.log(address);
                                this.mapAddress = address;
                            }
                        });
                    }
                });
            },
            sign(flag) {
                var now = moment();
                if (flag === 1) {
                    this.dkflag1 = false;
                    this.nowTime1 = now.format('HH:mm:ss');
                }
                if (flag === 2) {
                    this.dkflag2 = false;
                    this.nowTime2 = now.format('HH:mm:ss');
                }
            },
            getMapLocation() {
                console.log("获取位置开始");
                this.getLocal();
            }
        }
    }
</script>

<style type="text/css" scoped>

</style>
