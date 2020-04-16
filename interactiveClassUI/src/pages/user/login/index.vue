<template>
    <view>
        <van-cell-group>
            <view class="form">
                <van-field
                        :value="formData.userAccount"
                        placeholder="请输入学号"
                        label="学号"
                        autosize
                        @change="onUserAccountChange"
                        class="form_field"
                        required
                />

                <van-field
                        :value="formData.password"
                        placeholder="请输入密码"
                        password="true"
                        label="用户密码"
                        autosize
                        @change="onPasswordChange"
                        class="form_field"
                        required
                />
                <view class="btn_info">
                    <van-button @click="login" type="info" round>登录</van-button>
                    <van-button type="info" class="btn_margin" @click="register" plain round>注册</van-button>
                </view>
            </view>
        </van-cell-group>
    </view>
</template>

<script>
    import {doLogin} from "@/api/user"
    import {setToken} from '@/utils/tokenUtil'

    export default {
        data() {
            return {
                formData: {
                    userAccount: "222",
                    password: '222'
                }
            }
        },
        onLoad() {

        },
        methods: {
            login() {
                doLogin(this.formData).then(resp => {
                    const token = resp
                    if (token != null && token != '') {
                        this.successAlert("登录成功")
                        setToken(token)
                        setTimeout(() => {
                            uni.switchTab({
                                url: '/pages/index/index'
                            })
                        }, 1500)
                    }
                    console.log(resp)
                })

            },
            register() {
                uni.redirectTo({
                    url: '/pages/user/register/index'
                });
            },
            onUserAccountChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.userAccount = event.detail
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
        margin-top: 40px;
    }

    .btn_info {
        width: 200px;
        margin: 10px auto;
    }

    .form_field {
        margin: 4px 3px;
        padding: 8px 0;
    }

    .btn_margin {
        margin-left: 20px;
    }
</style>
