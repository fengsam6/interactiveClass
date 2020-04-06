<template>
    <view>
        <van-cell-group class="form">
            <van-field
                    v-model="form.userAccount"
                    placeholder="请输入用户名"
                    label="用户名"
					autosize
                    class="form_field"
            />

            <van-field
                    v-model="form.password"
                    placeholder="请输入密码"
                    password="true"
                    label="用户密码"
					autosize
                    class="form_field"
            />
            <view class="btn_info">
                <van-button @click="login" type="info" size="small" round >登录</van-button>
                <van-button type="info" size="small" class="btn_margin" @click="register" round >注册</van-button>
            </view>

        </van-cell-group>
    </view>
</template>

<script>
    import {doLogin} from "@/api/user"
    export default {
        data() {
            return {
                form: {}
            }
        },
        onLoad() {

        },
        methods: {
			login(){
                doLogin(this.form).then(resp=>{
                    uni.log(resp)
                    uni.redirectTo({
                        url: '/pages/index/index'
                    });
                })

			},
			register(){
				uni.redirectTo({
					url: '/pages/user/register/index'
				});

			}
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
