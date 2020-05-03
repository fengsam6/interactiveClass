<template>
    <view>
        <view>
            <view v-if="update" class="signIn address">
                <view class="signIn_left">
                    {{signQuery.signPlace}}
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
                    {{signQuery.beginTime}}
                </view>
            </view>
            <view class="signIn_right">
                <van-button icon="success" type="info" size="small" @click="sign(1)">
                    <view v-if="signInBtn">打卡</view>
                    <view>{{signQuery.signPreTime}}</view>
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
                    {{signQuery.endTime}}
                </view>
            </view>
            <view class="signIn_right">
                <van-button icon="success" type="info" size="small" @click="sign(2)">
                    <view v-if="signOutBtn">打卡</view>
                    <view>{{signQuery.signNextTime}}</view>
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
    var that;
    import {signIn,signOut,queryMySignInfo} from "@/api/sign"
    export default {
        name:"sign",
        props:{
            signData:{
                type:Object,
                default:{}
            },
            signQuery:{
                type:Object,
                default:{}
            }
        },
        data() {
            return {
                update: true,
                signInBtn: true,
                signOutBtn: true,
                signComData:{
                    classId:'',
                    courseId:'',
                    signPlace:'',
                    signIn:0,
                    signTime:''
                }

            }
        },
        onShow() {
        },
        mounted(){
            this.signComData.classId=this.signData.classId;
            this.signComData.courseId=this.signData.courseId;
           // this.getLocal();
        },
        onLoad() {
            //this.getLocal();
        },
        methods: {
            getLocal() {
                uni.getLocation({
                    type: 'wgs84',
                    success: () => {
                        qqmapsdk.reverseGeocoder({
                            success: (addressRes)=> {
                                var address = addressRes.result.formatted_addresses.recommend;
                                // console.log(address);
                                this.signComData.signPlace = address;
                                this.signQuery.signPlace = address;
                            }
                        });
                    }
                });
            },

            sign(flag) {
                var now = moment();
                if (flag === 1) {
                    if(this.signQuery.signPreTime){
                        this.successAlert("您已经签过到了");
                        return;
                    }
                    this.signInBtn = false;
                    this.signQuery.signPreTime = now.format('HH:mm:ss');
                    this.signComData.signIn=1;
                    this.signComData.signPlace=this.signQuery.signPlace;
                    this.signComData.signTime=this.signQuery.signPreTime;
                    signIn(this.signComData).then(resp => {
                        this.successAlert("上课签到成功");
                      //  this.$emit("refreshNotice")
                    });
                }
                if (flag === 2) {
                    if(this.signQuery.signNextTime){
                        this.successAlert("您已经签过到了");
                        return;
                    }
                    this.signOutBtn = false;
                    this.signQuery.signNextTime = now.format('HH:mm:ss');
                    this.signComData.signIn=0;
                    this.signComData.signTime=this.signQuery.signNextTime;
                    signIn(this.signComData).then(resp => {
                        this.successAlert("下课签到成功");
                        //  this.$emit("refreshNotice")
                    });
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
