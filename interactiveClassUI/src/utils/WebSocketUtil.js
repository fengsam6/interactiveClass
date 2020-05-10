let socketTask=null
let is_open_socket=false

// 进入这个页面的时候创建websocket连接【整个页面随时使用】
export function connectSocketInit() {
    // 创建一个this.socketTask对象【发送、接收、关闭socket都由这个对象操作】
    socketTask = uni.connectSocket({
        // 【非常重要】必须确保你的服务器是成功的,如果是手机测试千万别使用ws://127.0.0.1:9099【特别容易犯的错误】
        url: "ws://119.28.180.110:9099/echo",
        success(data) {
            console.log("websocket连接成功");
        },
    });

    // 消息的发送和接收必须在正常连接打开中,才能发送或接收【否则会失败】
    socketTask.onOpen((res) => {
        console.log("WebSocket连接正常打开中...！");
        this.is_open_socket = true;
        // 注：只有连接正常打开中 ，才能正常收到消息
        this.socketTask.onMessage((res) => {
            console.log("收到服务器内容：" + res.data);
        });
    })
    // 这里仅是事件监听【如果socket关闭了会执行】
   socketTask.onClose(() => {
        console.log("已经被关闭了")
    })
}
export function send(msg){
    // 注：只有连接正常打开中 ，才能正常成功发送消息
   socketTask.send({
        data: msg,
        async success() {
            console.log(msg+"消息发送成功");
        },
    });
}
// 关闭websocket【离开这个页面的时候执行关闭】
export function closeSocket() {
    socketTask.close({
        success(res) {
            this.is_open_socket = false;
            console.log("关闭成功", res)
        },
        fail(err) {
            console.log("关闭失败", err)
        }
    })
}
export function clickRequest(){
    if (is_open_socket) {
        // websocket的服务器的原理是:发送一次消息,同时返回一组数据【否则服务器会进去死循环崩溃】
        this.socketTask.send({
            data: "请求一次发送一次message",
            async success() {
                console.log("消息发送成功");
            },
        });
    }
}
export function leave(){
    this.$uniReLaunch("/pages/tabbar/wallet/wallet")
}