<template>
    <view>
        <view>
            <van-uploader :file-list="fileList" accept="image" @after-read="afterRead" />
            <van-uploader @after-read="afterRead" :file-list="fileList" accept="all">
                <van-button icon="photo" type="primary">上传文件</van-button>
            </van-uploader>
        </view>
    </view>
</template>

<script>
    import {getUserInfo, logout, saveUserInfoStore, getStoreUserInfo} from "@/api/user"
    import {clearStorageSync} from '@/utils/storage'
    import {uploadUrl} from '@/utils/fileUpload'

    export default {
        data() {
            return {
                userInfo: {
                    name: 'test',
                    avatar: '/static/images/user/cat.jpeg',
                    role:"1"
                },
                uploadUrl: uploadUrl,
                fileList:[]
            }
        },
        // 页面展示时候显示
        onShow() {
            this.doGetStoreUserInfo()
        },
        mounted() {
            this.doGetUserInfo()
        },
        methods: {
            doGetUserInfo() {
                getUserInfo().then(resp => {
                    this.userInfo = resp
                    saveUserInfoStore(resp)
                })
            },
            async doGetStoreUserInfo() {
                this.userInfo = await getStoreUserInfo()
                console.log(this.userInfo)
            },


            afterRead(event) {
                const { file } = event.detail;
                // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
                wx.uploadFile({
                    url: this.uploadUrl, // 仅为示例，非真实的接口地址
                    filePath: file.path,
                    name: 'file',
                    formData: { user: 'test' },
                    success(res) {
                        // 上传完成需要更新 fileList
                        const { fileList = [] } = this.data;
                        fileList.push({ ...file, url: res.data });
                        this.setData({ fileList });
                    }
                });
            }
        }
    }
</script>

<style type="text/css" scoped>
    .top_wrap {
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
