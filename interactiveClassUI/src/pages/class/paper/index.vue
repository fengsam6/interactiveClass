<template>
    <view>
        <view v-for="(item,i) in arrPaper" :key="i">
            <view class="sj_xx">
                <view>
                    <text>{{item.paperName}}</text>
                    <text>{{item.paperTime}}</text>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    export default {
        name: "index",
        props:{
            arrPaper:{
                type:Array,
                default:[]
            }
        },
    }
</script>

<style scoped>

</style>