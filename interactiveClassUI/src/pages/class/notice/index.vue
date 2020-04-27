<template>
    <view>
        <view v-for="(item,i) in noticeArr" :key="i">
            <van-row>
                <view class="notice_title">
                    {{item.noticeTitle}}
                </view>
                <view class="notice_content">
                    {{item.noticeContent}}
                </view>
                <van-col span="10" offset="14">
                    <view class="notice_foot">
                        {{item.publishTime}}
                    </view>
                </van-col>
            </van-row>
            <van-divider  custom-style="height:1px"/>
        </view>
    </view>
</template>

<script>
    import {addNotice,queryNotice} from "@/api/notice"
    import {getStoreUserInfo, saveUserInfoStore,getUserInfo} from '@/api/user'
    export default {
        name: "index",
        data() {
            return {
                userInfo:null,
                noticeArr:[],
                notice:{
                    noticeTitle:'',
                    noticeContent:'',
                    publishUserId:'',
                    classId:'',
                    courseId:''
                },
                border:false
            }
        },
        onShow() {
            this.doGetStoreUserInfo();
        },
        mounted() {
            this.doGetUserInfo();
            this.notice.publishUserId = this.userInfo.id;
            this.queryNotice();
        },
        onLoad(option) {
            debugger
            const item = JSON.parse(decodeURIComponent(option.item));
            this.className=item.className;
            this.notice.classId=item.classId;
            this.notice.courseId=item.courseId;
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
            onChange(event) {
                var index = event.detail.name;
                if (index == 0) {
                    this.queryNotice();
                }
            },
            noticeValueChange(event, index) {
                // event.detail 为当前输入的值
                if (index == 'noticeTitle') {
                    this.notice.noticeTitle = event.detail;
                }
                if (index == 'noticeContent') {
                    this.notice.noticeContent = event.detail;
                }
            },
            queryNotice() {
                var data = {
                    courseId: this.notice.courseId,
                    classId: this.notice.classId,
                    publishUserId: this.notice.publishUserId,
                    page: 1,
                    limit: 30
                }
                queryNotice(data).then(resp => {
                    this.noticeArr = resp;
                });
            },
            publishGg() {
                addNotice(this.notice).then(resp => {
                    this.successAlert("添加通知成功");
                    this.queryNotice();
                });
            },
        }
    }
</script>

<style scoped>

</style>