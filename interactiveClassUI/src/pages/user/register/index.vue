<template>
    <view>
        <van-cell-group>
            <view class="form">
                <van-field
                        :value="formData.name"
                        placeholder="请输入用户名"
                        label="用户名"
                        class="form_field"
                        required
                        @change="onUserNameChange"
                />
                <van-field
                        :value="formData.userNum"
                        placeholder="请输入学号"
                        label="学号"
                        class="form_field"
                        required
                        @change="onUserNumChange"
                />
                <van-field
                        :value="formData.password"
                        placeholder="请输入密码"
                        password="true"
                        label="用户密码"
                        class="form_field"
                        required
                        @change="onPasswordChange"
                />
                <van-radio-group :value="formData.roleId" @change="onRoleChange">
                    <van-row>
                        <van-col span="8" offset="4">
                            <van-radio name="1" value="1">学生</van-radio>
                        </van-col>
                        <van-col span="10" offset="4">
                            <van-radio name="2" value="2">老师</van-radio>
                        </van-col>
                    </van-row>
                </van-radio-group>
                <view class="btn_info">
                    <van-button type="info" @click="register" round>注册</van-button>
                    <van-button @click="login" type="info" class="btn_margin" round plain>登录</van-button>
                </view>
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
                    role: "1"
                }
            }
        },
        onLoad() {

        },
        methods: {
            login() {
                uni.navigateTo({
                    url: '/pages/user/login/index'
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
            onUserNameChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.name = event.detail
            },
            onUserNumChange(event){
                this.formData.userNum = event.detail
            },
            onPasswordChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.password = event.detail
            },
            onRoleChange(event) {
                this.formData.roleId = event.detail
            }
        }
    }
</script>

<style type="text/css" scoped>
    .form {
        margin-top: 80rpx;
    }

    .btn_info {
        width: 200px;
        margin: 0 auto;
    }

    .form_field {
        margin: 8px 2px;
    }

    .btn_margin {
        margin-left: 20px;
    }
</style>
