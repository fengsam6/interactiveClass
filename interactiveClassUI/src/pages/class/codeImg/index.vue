<template>
    <view style="text-align: center;">
        <image :src="qr" style="width:80%;"></image>
    </view>
</template>

<script>
    import Qr from "../../../utils/wxqrcode.js"
    import {getClassById} from "@/api/class"
    export default {
        data(){
            return{
                qr:'',
                classId:'',
                classInfo:null
            }
        },
        beforeMount(){

        },
        mounted(){
            this.queryClass();
        },
        onLoad(option) {
            this.classId=option.item;
        },
        methods:{
            getQr(){
                this.qr=Qr.createQrCodeImg(this.classInfo.classCreateCode)
            },
            async queryClass(){
                this.classInfo = await getClassById({id:this.classId});
                this.getQr();
            }
        }
    }
</script>

<style scoped>

</style>