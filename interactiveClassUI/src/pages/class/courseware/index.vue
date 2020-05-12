<template>
    <view>
        <view v-for="(item,i) in coursewareArr" :key="i" class="kj_zlist">
            <view class="kj_list">
                <view class="kj_title">{{item.courseResourceName}}</view>
                <view class="kj_yl">
                    <van-button type="primary" size="small" @click="preview(item.resourcePath)">预览</van-button>
                </view>
                <view class="kj_download" style="margin-right: 24px;">
                    <van-button color="#1E9FFF"  size="small" @click="preview(item.resourcePath)">下载</van-button>
                </view>
                <view style="height: 50px;line-height: 50px;" class="collection" @click.prevent="collection(item.resourcePath)">
                    <van-icon name="star-o" custom-class="collection"/>
                </view>
            </view>
            <view class="line"></view>
        </view>
    </view>
</template>

<script>
    export default {
        name: "index",
        props:{
            coursewareArr:{
                type:Array,
                default:[]
            }
        },
        methods:{
            preview(path){
                wx.downloadFile({
                    url: 'http://localhost:8080/file/'+path,
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
            collection(id){
                this.successAlert("收藏课件成功");
            }
        }
    }
</script>

<style scoped>
.collection{
    color: red;
    font-size: 20px;
    line-height: 50px;
    width: 50px;
    text-align: center;
}
</style>