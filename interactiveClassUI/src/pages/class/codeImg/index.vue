<template>
    <view style="text-align: center;">
        <image :src="qr" style="width:80%;"></image>
    </view>
</template>

<script>
    import Qr from "../../../utils/wxqrcode.js"
    export default {
       // components: {tkiQrcode},
        data(){
            return{
               qr:''
            }
        },
        beforeMount(){
           this.getQr();
        },
        methods:{
            getQr(){
                this.qr=Qr.createQrCodeImg("rghyhenehy434398439343")
            }
        }
    }
</script>

<style scoped>

</style>