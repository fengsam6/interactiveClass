<template>
    <view>
        <view class="top_wrap">
            <van-row>
                <van-col span="12">
                    <view class="top_aside">
                        <view>
                            <text class="user-name" v-text="userInfo.name"></text>
                        </view>
                        <view>
                            <van-button type="default" size="small" @click="editUserInfo">编辑个人资料</van-button>
                        </view>
                    </view>
                </van-col>
                <van-col span="8" offset="4">
                    <van-image
                            round
                            width="5rem"
                            height="5rem"
                            :src="userInfo.avatar"
                            @click="changeImg"
                    />
                </van-col>
            </van-row>
        </view>
        <view>
            <van-uploader :file-list="fileList" accept="image" @after-read="afterRead"/>
            <van-uploader @after-read="afterRead" :file-list="fileList" accept="all">
                <van-button icon="photo" type="primary">上传文件</van-button>
            </van-uploader>

        </view>
        <view>
            <button @click="uploadFile2">微信上传文件</button>
        </view>
        <view class="me_main">
            <van-cell-group>
                <van-cell title="我的课件库" is-link/>
                <van-cell title="我的试卷库" is-link/>
                <van-cell title="签到" is-link/>
                <van-cell title="帮助" is-link/>
                <van-cell title="退出" is-link @click="doLogout"/>
            </van-cell-group>
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
                    role: "1"
                },
                uploadUrl: uploadUrl,
                fileList: []
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
            doLogout() {
                logout()
                clearStorageSync()
                uni.navigateTo({
                    url: '/pages/user/login/index'
                });
            },
            editUserInfo() {
                uni.navigateTo({
                    url: '/pages/user/userInfo/index'
                });
            },
            changeImg() {
                uni.uploadFile({
                    url: uploadUrl, //仅为示例，非真实的接口地址
                    filePath: "/test",
                    name: 'file',
                    formData: {
                        'user': 'test'
                    },
                    success: (uploadFileRes) => {
                        console.log(uploadFileRes.data);
                    }
                });
            },
            afterRead(event) {
                const {file} = event.detail;
                // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
                wx.uploadFile({
                    url: uploadUrl, // 仅为示例，非真实的接口地址
                    filePath: file.path,
                    name: 'file',
                    formData: {user: 'test'},
                    success(res) {
                        // 上传完成需要更新 fileList
                        const {fileList = []} = this.data;
                        console.log(fileList);
                    }
                });
            },
            uploadFile2() {
                wx.chooseMessageFile({
                    count: 10,
                    type: 'all',
                    success(res) {
                        // tempFilePath可以作为img标签的src属性显示图片
                        const tempFilePaths = res.tempFiles
                        console.log(tempFilePaths)
                        uni.uploadFile({
                            url: uploadUrl, //仅为示例，非真实的接口地址
                            filePath: tempFilePaths[0].path,
                            name: 'file',
                            formData: {
                                'user': 'test'
                            },
                            success: (uploadFileRes) => {
                                console.log(uploadFileRes.data);
                            }
                        });
                    }
                })
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
