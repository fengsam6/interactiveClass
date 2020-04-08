<template>
    <view>
        <van-cell-group class="form">
            <van-field
                    :value="formData.userAccount"
                    placeholder="请输入用户名"
                    label="用户名"
                    autosize
                    @change="onUserAccountChange"
                    class="form_field"
            />

            <van-field
                    :value="formData.password"
                    placeholder="请输入密码"
                    password="true"
                    label="用户密码"
                    autosize
                    @change="onPasswordChange"
                    class="form_field"
            />
            <view class="btn_info">
                <van-button @click="login" type="info" size="small" round>登录</van-button>
                <van-button type="info" size="small" class="btn_margin" @click="register" round>注册</van-button>
            </view>

        </van-cell-group>
    </view>
</template>

<script>
    import {doLogin} from "@/api/user"

    export default {
        data() {
            return {
                formData: {
                    userAccount:"test",
                    password:'test'
                }
            }
        },
        onLoad() {

        },
        methods: {
            login() {
                doLogin(this.formData).then(resp => {
                    const token = resp.data
                    if(token!=null){
                        uni.showToast("登录成功")
                        uni.setStorage({key:"user-token",data:token})
                    }

                    console.log(resp)
                    uni.switchTab({
                        url: '/pages/index/index'
                    });
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
                this.formData.userAccount =event.detail
            },
            onPasswordChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.formData.password =event.detail
            },
    }
    }
</script>

<style type="text/css" scoped>
    .form {
        margin-top: 250px;
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
