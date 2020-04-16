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
                            <van-button type="default" size="small" @click="editUserInfo" class="edit_btn">编辑个人资料
                            </van-button>
                        </view>
                    </view>
                </van-col>
                <van-col span="8" offset="4">
                    <van-image
                            round
                            width="5rem"
                            height="5rem"
                            :src="userInfo.avatar || defaultAvatar"
                            @click="changeImg"
                    />
                </van-col>
            </van-row>
        </view>

        <view class="me_main">
            <van-cell-group>
                <van-cell title="我的课件库" is-link size="large"/>
                <van-cell title="我的试卷库" is-link size="large"/>
                <van-cell title="签到" is-link size="large"/>
                <van-cell title="帮助" is-link size="large"/>
                <van-cell title="退出" is-link size="large" @click="doLogout"/>
            </van-cell-group>
        </view>

    </view>
</template>

<script>
    import {getUserInfo, logout, saveUserInfoStore, getStoreUserInfo} from "@/api/user"
    import {clearStorageSync} from '@/utils/storage'
    import {uploadUrl, chooseAndUploadImage} from '@/utils/fileUpload'

    export default {
        data() {
            return {
                defaultAvatar: '/static/images/user/cat.jpeg',
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
                chooseAndUploadImage().then(data => {
                    console.log(data)
                })
            }
        }
    }
</script>

<style type="text/css" scoped>
    .top_wrap {
        margin-top: 10px;
    }

    .edit_btn {
        margin: 8px 1px;
    }

    .top_aside {
        margin-left: 15px;
    }

    .user-name {
        font-size: 16px;
    }

    .me_main {
        margin: 20px 2px 4px 2px;
    }
</style>
