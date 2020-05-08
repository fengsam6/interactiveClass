<template>
    <view class="qiun-columns">
        <view class="qiun-bg-white qiun-title-bar qiun-common-mt" >
            <view class="qiun-title-dot-light">优秀占比</view>
        </view>
        <view class="qiun-charts" >
            <canvas canvas-id="canvasRing" id="canvasRing" class="charts" @touchstart="touchRing"></canvas>
        </view>
    </view>
</template>

<script>
    import uCharts from '@/utils/u-charts.js'
    import {queryPaperResult} from "@/api/paper"
    var _self;
    var canvaRing=null;

    export default {
        data() {
            return {
                cWidth:'',
                cHeight:'',
                pixelRatio:1,
                serverData:'',
                courseId:''
            }
        },
        onLoad(option) {
            this.courseId=option.item;
            _self = this;
            this.cWidth=uni.upx2px(750);
            this.cHeight=uni.upx2px(500);
            this.getPaperData();
        },
        methods: {
            getPaperData(){
                var data={
                    courseId:this.courseId
                }
                queryPaperResult(data).then(resp=>{
                    var newArr=resp;
                    var newData=[];
                    let Ring={series:[]};
                    for(var i=0;i<newArr.length;i++){
                        var value={
                            name:newArr[i].className,
                            data:newArr[i].goodRate
                        }
                        newData.push(value);
                    }
                    Ring.series=newData;
                    this.showRing("canvasRing",Ring);
                })
            },
           /* getServerData(){
                uni.request({
                    url: 'https://www.ucharts.cn/data.json',
                    data:{
                    },
                    success: function(res) {
                        debugger
                        console.log(res.data.data)
                        let Ring={series:[]};
                        //这里我后台返回的是数组，所以用等于，如果您后台返回的是单条数据，需要push进去
                        Ring.series=res.data.data.Ring.series;
                        _self.showRing("canvasRing",Ring);
                    },
                    fail: () => {
                        _self.tips="网络错误，小程序端请检查合法域名";
                    },
                });
            },*/
            showRing(canvasId,chartData){
                canvaRing=new uCharts({
                    $this:_self,
                    canvasId: canvasId,
                    type: 'ring',
                    fontSize:11,
                    legend:true,
                    extra: {
                        pie: {
                            offsetAngle: -45,
                            ringWidth: 40*_self.pixelRatio,
                            labelWidth:15
                        }
                    },
                    background:'#FFFFFF',
                    pixelRatio:_self.pixelRatio,
                    series: chartData.series,
                    animation: true,
                    width: _self.cWidth*_self.pixelRatio,
                    height: _self.cHeight*_self.pixelRatio,
                    disablePieStroke: true,
                    dataLabel: true,
                });
            },
            touchRing(e){
                canvaRing.showToolTip(e, {
                    format: function (item) {
                        return item.name + ':' + item.goodRate
                    }
                });
            },
        }
    }
</script>

<style>
    page{background:#F2F2F2;width: 750upx;overflow-x: hidden;}
    .qiun-padding{padding:2%; width:96%;}
    .qiun-wrap{display:flex; flex-wrap:wrap;}
    .qiun-rows{display:flex; flex-direction:row !important;}
    .qiun-columns{display:flex; flex-direction:column !important;}
    .qiun-common-mt{margin-top:10upx;}
    .qiun-bg-white{background:#FFFFFF;}
    .qiun-title-bar{width:96%; padding:10upx 2%; flex-wrap:nowrap;}
    .qiun-title-dot-light{border-left: 10upx solid #0ea391; padding-left: 10upx; font-size: 32upx;color: #000000}
    .qiun-charts{width: 750upx; height:500upx;background-color: #FFFFFF;}
    .charts{width: 750upx; height:500upx;background-color: #FFFFFF;}
</style>