<template>
    <view>
        <van-cell-group class="form">
            <van-field
                    v-model="form.userAccount"
                    placeholder="请输入用户名"
                    label="用户名"
                    class="form_field"
            />
            <van-field
                    v-model="form.password"
                    placeholder="请输入密码"
                    password="true"
                    label="用户密码"
                    class="form_field"
            />
            <van-radio-group v-model="form.role" bind:change="onChange">
                <van-radio name="1">学生</van-radio>
                <van-radio name="2">老师</van-radio>
            </van-radio-group>
            <view class="btn_info">
                <van-button type="info" size="small"  @click="register">注册</van-button>
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
                form: {}
            }
        },
        onLoad() {

        },
        methods: {
			login(){
				uni.navigateTo({
					url: '/pages/index/index'
				});
			},
			register(){
                doRegister(this.from).then(resp=>{
                    uni.navigateTo({
                        url: '/pages/user/login/index'
                    });
                })

			}
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
