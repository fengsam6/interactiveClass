<template>
    <view>
        <van-cell-group class="form">
            <van-field
                    :value="formData.account"
                    placeholder="用户名"
                    label="用户名"
                    class="form_field"
                    @change="onUserAccountChange"
            />
            <van-field
                    :value="formData.password"
                    placeholder="请输入密码"
                    password="true"
                    label="用户密码"
                    class="form_field"
                    @change="onUserAccountChange"
            />
            <van-radio-group :value="formData.role">
                <van-radio name="1" value="1">学生</van-radio>
                <van-radio name="2" value="2">老师</van-radio>
            </van-radio-group>
            <view class="btn_info">
                <van-button type="info" size="small" @click="update">更新</van-button>
                <van-button @click="login" type="info" class="btn_margin" size="small">取消</van-button>
            </view>
        </van-cell-group>
    </view>
</template>

<script>
    import {saveUserInfoStore, getStoreUserInfo} from "@/api/user"

    export default {
        data() {
            return {
                formData: {
                    account: "",
                    password: "",
                    role: 1
                }
            }
        },
        onShow() {
            this.getUserInfo()
        },
        methods: {
            async getUserInfo() {
                this.userInfo = await getStoreUserInfo()
            },
            update() {
                console.log(this.formData)
            },
            onUserAccountChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.account = event.detail
            },
            onPasswordChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.password = event.detail
            },
        }
    }
</script>

<style type="text/css" scoped>
    .form {
        margin-top: 150px;
    }

    .btn_info {
        width: 200px;
        margin: 0 auto;
    }

    .form_field {
        margin: 5px 0;
    }

    .btn_margin {
        margin-left: 10px;
    }
</style>
