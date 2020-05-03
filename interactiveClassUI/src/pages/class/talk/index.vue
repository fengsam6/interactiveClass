<template>
    <view>
        <view  class="msgList">
            <van-row v-for="(msg,i) in messages" :key="i">
                <van-col span="8">
                    <text class="userName">{{msg.userName}}</text>
                    <text>:</text>
                </van-col>
                <van-col span="16">
                    <view>{{msg.content}}</view>
                </van-col>
            </van-row>
        </view>
        <van-row>
            <van-col span="18">
                <van-cell-group>
                    <van-field
                            :value="content"
                            required
                            clearable
                            placeholder="输入消息内容"
                            @change="contentChange"
                    />
                </van-cell-group>
            </van-col>
            <van-col span="6">
                <van-button type="info" @click="sendMsg()" size="small">发送</van-button>
            </van-col>
        </van-row>

    </view>
</template>

<script>
    import config from "@/utils/config";
    import {getToken} from '@/utils/tokenUtil'
    import {takeStoreUserInfo} from "@/api/user"

    const websocketUrl = config.websocketUrl
    const token = getToken()
    export default {
        name: "talk",
        data() {
            return {
                content: '',
                userInfo: {},
                messages: [],
                socketTask: null
            }
        },
        mounted() {
            console.log(websocketUrl)
            this.connectSocketInit()
        },
        methods: {
            // 进入这个页面的时候创建websocket连接【整个页面随时使用】
            connectSocketInit() {
                this.userInfo = takeStoreUserInfo()
                const userId = this.userInfo.id
                const userName = this.userInfo.name || 'test'
                // 创建一个this.socketTask对象【发送、接收、关闭socket都由这个对象操作】
                this.socketTask = uni.connectSocket({
                    // 【非常重要】必须确保你的服务器是成功的,如果是手机测试千万别使用ws://127.0.0.1:9099【特别容易犯的错误】
                    url: websocketUrl + userId,
                    //认证token
                    header: {
                        'USER-TOKEN': token
                    },
                    success: (data) => {
                        console.log(data);
                        this.appendSelfMsg('欢迎进入讨论室')
                        console.log("websocket连接成功");
                    },
                    fail: (data) => {
                        console.log(data);
                        this.appendSelfMsg('连接websocket失败')
                        console.log("连接websocket失败");
                    },
                });

                // 消息的发送和接收必须在正常连接打开中,才能发送或接收【否则会失败】
                this.socketTask.onOpen((res) => {
                    console.log("WebSocket连接正常打开中...！");
                    this.is_open_socket = true;
                    // 注：只有连接正常打开中 ，才能正常收到消息
                    this.socketTask.onMessage((res) => {
                        const msgObj = JSON.parse(res.data)
                        const userId = msgObj.userId
                        const content = msgObj.content
                        const userName = msgObj.userName || 'test'
                        this.appendMsg(userId, userName, content)
                        console.log("收到服务器内容：" + res.data);
                    });
                })
                // 这里仅是事件监听【如果socket关闭了会执行】
                this.socketTask.onClose(() => {
                    this.appendMsg('成功推出聊天室')
                    console.log("已经被关闭了")
                })
            },
            send(msg) {
                // 注：只有连接正常打开中 ，才能正常成功发送消息
                this.socketTask.send({
                    data: msg,
                    async success() {
                        console.log(msg + "消息发送成功");
                    },
                });
            },
            // 关闭websocket【离开这个页面的时候执行关闭】
            closeSocket() {
                this.socketTask.close({
                    success(res) {
                        this.is_open_socket = false;
                        console.log("关闭成功", res)
                    },
                    fail(err) {
                        console.log("关闭失败", err)
                    }
                })
            },
            clickRequest() {
                if (this.is_open_socket) {
                    // websocket的服务器的原理是:发送一次消息,同时返回一组数据【否则服务器会进去死循环崩溃】
                    this.socketTask.send({
                        data: "请求一次发送一次message",
                        async success() {
                            console.log("消息发送成功");
                        },
                    });
                }
            },
            leave() {
                this.$uniReLaunch("/pages/tabbar/wallet/wallet")
            },
            contentChange(event) {
                // event.detail 为当前输入的值
                console.log(event.detail);
                this.content = event.detail
            },
            sendMsg() {
             const msg =  this.contentToJsonMsg(this.content)
                this.send(msg)
                this.appendJsonMsg(msg)
                this.content=''
            },

            appendMsg(userId, userName, content) {
                const msg = {userId, userName, content}
                this.messages.push(msg)
            },
            appendJsonMsg(msg) {
                this.messages.push(msg)
            },
            contentToJsonMsg(content){
                const userId = this.userInfo.id
                const userName = this.userInfo.name || 'test'
                const msg = {userId, userName, content}
                return msg;
            },
            appendSelfMsg(content) {
                const userId = this.userInfo.id
                const userName = this.userInfo.name || 'test'
                const msg = {userId, userName, content}
                this.messages.push(msg)
            }
        }


    }
</script>

<style scoped>
    .msgList {
        margin: 1px 4px;
        height: 540px;
    }
    .userName{
        font-size: 16px;
    }
</style>