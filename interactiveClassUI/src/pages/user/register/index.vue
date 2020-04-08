<template>
    <view>
        <van-cell-group class="form">
            <van-field
                    v-model="formData.account"
                    placeholder="请输入用户名"
                    label="用户名"
                    class="form_field"
                    @change="onUserAccountChange"
            />
            <van-field
                    v-model="formData.password"
                    placeholder="请输入密码"
                    password="true"
                    label="用户密码"
                    class="form_field"
                    @change="onUserAccountChange"
            />
            <van-radio-group v-model="formData.role">
                <van-radio name="1">学生</van-radio>
                <van-radio name="2">老师</van-radio>
            </van-radio-group>
            <view class="btn_info">
                <van-button type="info" size="small" @click="register">注册</van-button>
                <van-button @click="login" type="info" class="btn_margin" size="small">登录</van-button>
            </view>
        </van-cell-group>
    </view>
</template>

<script>
    import {doRegister} from "@/api/user"

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
        onLoad() {

        },
        methods: {
            login() {
                uni.navigateTo({
                    url: '/pages/index/index'
                });
            },
            register() {
                console.log(this.formData)
                doRegister(this.formData).then(resp => {
                    uni.showToast("注册成功")
                    uni.navigateTo({
                        url: '/pages/user/login/index'
                    });
                })

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
