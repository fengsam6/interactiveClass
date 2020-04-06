<template>
    <view>
        <van-cell-group class="form">
            <van-field
                    v-model="form.userName"
                    placeholder="请输入用户名"
                    label="用户名"
                    class="form_field"
            />
            <van-field
                    v-model="form.userName"
                    placeholder="请输入密码"
                    password="true"
                    label="用户名"
                    class="form_field"
            />
            <view class="btn_info">
                <van-button type="info" size="small"  @click="register">注册</van-button>
                <van-button @click="login" type="info" class="btn_margin" size="small">登录</van-button>
            </view>
        </van-cell-group>
    </view>
</template>

<script>
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
				uni.navigateTo({
					url: '/pages/user/register/index'
				});

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
