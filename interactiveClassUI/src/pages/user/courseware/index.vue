<template>
    <view>
        <view v-for="(item,i) in coursewareArr" :key="i" class="kj_zlist">
            <view class="kj_list">
                <view class="kj_title">{{item.courseResourceName}}</view>
                <view class="kj_yl">
                    <van-button type="primary" size="small" @click="preview(item.id)">预览</van-button>
                </view>
                <view class="kj_download" style="margin-right: 24px;">
                    <van-button color="#1E9FFF"  size="small">下载</van-button>
                </view>
            </view>
            <view class="line"></view>
        </view>
    </view>
</template>

<script>
    import {myCourses} from "@/api/courseware"
    export default {
        name: "index",
       data(){
            return{
                coursewareArr:[]
            }
       },
        mounted(){
            this.queryMyCourse();
        },
        methods:{
            preview(id){
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
            queryMyCourse(){
                var data={
                    pageNum:1,
                    pageSize:30
                }
                myCourses(data).then(resp => {
                    this.coursewareArr=resp;
                })
            }
        }
    }
</script>

<style scoped>
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
</style>