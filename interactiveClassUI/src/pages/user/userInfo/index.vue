<template>
    <view>
        <van-cell-group>
            <view class="form">
                <van-field
                        :value="formData.name"
                        placeholder="用户名"
                        label="用户名"
                        class="form_field"
                        required
                        @change="onUserAccountChange"
                />
                <van-field
                        :value="formData.userNum"
                        placeholder="学号"
                        label="学号"
                        disabled
                        class="form_field"
                />
                <van-field
                        :value="formData.password"
                        placeholder="请输入密码"
                        password="true"
                        label="用户密码"
                        class="form_field"
                        required
                        @change="onUserAccountChange"
                />
                <view class="role">
                    <van-radio-group :value="formData.roleId" @change="onRoleChange">
                        <van-radio name="1" value="1">学生</van-radio>
                        <van-radio name="2" value="2">老师</van-radio>
                    </van-radio-group>
                </view>

                <view class="btn_info">
                    <van-button type="info" @click="update" round>更新</van-button>
                    <van-button @click="reset" type="info" class="btn_margin" plain round>重置</van-button>
                </view>
            </view>
        </van-cell-group>
    </view>
</template>

<script>
    import {updateUserInfo, getStoreUserInfo, getAndSaveUserInfoStore} from "@/api/user"

    export default {
        data() {
            return {
                formData: {
                    account: "",
                    password: "",
                    roleId: 1
                }
            }
        },
        onShow() {
            this.getUserInfo()
        },
        methods: {
            async getUserInfo() {
                this.formData = await getStoreUserInfo()
            },
            update() {
                updateUserInfo(this.formData)
                this.successAlert("修改用户信息成功")
                getAndSaveUserInfoStore()

                setTimeout(() => {
                    uni.navigateBack({});
                }, 2000)

            },
            onUserAccountChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.name = event.detail
            },
            onPasswordChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.password = event.detail
            },
            onRoleChange(event) {
                this.formData.roleId = event.detail
            },
            reset() {
                this.getUserInfo()
            }
        }
    }
</script>

<style type="text/css" scoped>
    .form {
        margin-top: 50px;
    }

    .btn_info {
        width: 200px;
        margin: 0 auto;
    }

    .form_field {
        margin: 5px 0;
    }

    .role {
        margin: 5px 4px;
    }

    .btn_margin {
        margin-left: 10px;
    }
</style>
