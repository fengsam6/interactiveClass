<template>
    <view>
        课件库
    </view>
</template>
<script>
    export default {
        onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
            console.log(option.id); //打印出上个页面传递的参数。
            console.log(option.name); //打印出上个页面传递的参数。
        }
    }
</script>
<style>

</style>