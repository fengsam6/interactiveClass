<template>
    <view style="margin-right: 8px;margin-left: 8px;">
        <view class="class_name" style="font-size: 18px; font-weight: bold;">
            {{className}}
        </view>
        <van-divider  custom-style="height:1px"/>
        <van-row>
            <van-grid :border="border">
                <van-grid-item  use-slot>
                    <view>
                        <image
                                style="width: 100%; height: 40px;"
                                src="/static/icon/cygl.png"
                        />
                        <view>成员管理</view>
                    </view>
                </van-grid-item>
                <van-grid-item  use-slot>
                    <view>
                        <image
                                style="width: 100%; height: 40px;"
                                src="/static/icon/tlq.png"
                        />
                        <view>讨论区</view>
                    </view>
            </van-grid-item>
            <van-grid-item  use-slot>
                <view>
                    <image
                            style="width: 100%; height: 40px;"
                            src="/static/icon/sz.png"
                    />
                    <view>设置</view>
                </view>
            </van-grid-item>
            </van-grid>
        </van-row>
        <van-row>
            <view class="class_center2">
                <view style="font-size: 18px;font-weight: bold;">教学日志</view>
                <view>
                    <van-button icon="plus" type="info" size="small" @click="fbshow=true" @click-overlay="fbshow=false">发布</van-button>
                </view>
            </view>
        </van-row>
        <van-divider  custom-style="height:1px"/>
        <van-row>
            <view>
                <van-tabs swipeable>
                    <van-tab title="公告">
                        <notice/>
                    </van-tab>
                    <van-tab title="课件">
                       <courseware/>
                    </van-tab>
                    <van-tab title="试卷">
                        <paper/>
                    </van-tab>
                    <van-tab title="签到">
                        <van-row>
                           <user-sign/>
                            <van-divider  custom-style="height:1px"/>
                        </van-row>
                    </van-tab>
                </van-tabs>
            </view>
        </van-row>
        <van-row custom-class="width:100%;height=100%;">
            <view style=" display:flex; justify-content:center">
                <van-overlay :show="fbshow" @click="fbshow=false">
                    <view class="wrapper" style="margin: 50% 20%;">
                        <view style="width:100%">
                            <van-button icon="star-o" type="primary" round="true" custom-style="width:100%;">发布试卷</van-button>
                        </view>
                        <view style="width:100%;margin-top: 12px;">
                            <van-button icon="star-o" type="primary" round="true" custom-style="width:100%;">发布公告</van-button>
                        </view>
                    </view>
                </van-overlay>
            </view>
        </van-row>
    </view>
</template>
<script>
    import notice from '@/pages/notice/index'
    import sign from '@/pages/user/sign/index'
    import paper from '@/pages/class/paper/index'
    import courseware from '@/pages/class/courseware/index'
    var moment = require('moment');
    var QQMapWX = require('@/lib/qqmap-wx-jssdk.min.js');
    var qqmapsdk;
    qqmapsdk = new QQMapWX({
        key: 'KYVBZ-Y7UWW-PWVRO-R4IK5-5BIT2-RBFVX'
    });
    var _self;
    export default {
        components:{
            notice,
            paper,
            courseware,
            userSign: sign
        },
        data() {
            return {
                className:'',
                border:false,
                maddress:'',
                update:true,
                dkflag1:true,
                dkflag2:true,
                nowTime1:'',
                nowTime2:'',
                fbshow:false
            }
        },
        onLoad(option) {
            const item = JSON.parse(decodeURIComponent(option.item));
            this.className=item.className;
            this.getLocal();
        },
        methods:{
            getLocal(){
                _self=this;
                uni.getLocation({
                    type: 'wgs84',
                    success: function (res) {
                        qqmapsdk.reverseGeocoder({
                            success: function (addressRes) {
                                var address = addressRes.result.formatted_addresses.recommend;
                                // console.log(address);
                                _self.maddress=address;
                            }
                        });
                    }
                });
            },
            preview(){
                wx.downloadFile({
                    url: 'http://video.317hu.com/917b3140-3da6-47d5-911c-a15462fcdeb2.pdf',
                    success: function (res) {
                        var filePath = res.tempFilePath
                        wx.openDocument({
                            filePath: filePath,
                            success: function (res) {
                                console.log('打开文档成功')
                            }
                        })
                    }
                })
            },
            mgetLocation(){
                console.log("获取位置开始");
                this.getLocal();
            },
            Dk(flag){
                var now=moment();
                if(flag==1){
                    this.dkflag1=false;
                    this.nowTime1=now.format('HH:mm:ss');
                }
                if(flag==2){
                    this.dkflag2=false;
                    this.nowTime2=now.format('HH:mm:ss');
                }
            }
        }
    }
</script>
<style>
.option_icon image{
    width:70%;
    height:36px;
}
.class_center2{
    display: flex;
    justify-content: space-between;
}
.notice_title{
    font-family: "Times New Roman", Times, serif;
    font-weight: bold;
    font-size: 16px;
}
.notice_content{
    font-family:"微软雅黑","黑体","宋体";
    font-size: 14px;
    color: #000000;
}
.notice_foot{
    font-size: 12px;
    color: #b3b3b3;
}
.signIn{
    height: 40px;
    display: flex;
    justify-content: space-between;
}
.address{
    height: 50px;
}
 .signIn_left{
    margin: auto 0px;
}
.signIn_right{
    margin: auto 0px;
}
.signIn_time{
    font-size: 12px;
    color: #b3b3b3;
}
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
.kj_yl,.kj_download{
    width: 40px;
    margin: auto 10px;
}
    .sj_xx view{
        min-height: 46px;
        background: #fff;
        box-shadow: 0 4px 8px 0 rgba(28,31,33,.1);
        border-radius: 8px;
        margin-bottom: 20px;
        padding: 20px 12px;
        box-sizing: border-box;
    }
    .sj_xx text,.sj_st{
        font-family: PingFangSC-Regular;
        font-size: 14px;
        color: #1c1f21;
        letter-spacing: 0;
        line-height: 22px;
        word-break: break-word;
    }
</style>