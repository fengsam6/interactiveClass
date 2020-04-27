<template>
    <view>
        <view v-for="(item,i) in [1,2,3,4,5]" :key="i">
            <view class="sj_st">{{i+1}}、在控制台运行一个 Java 程序 Test . class ，使用的命令正确的是（ ）</view>
            <view class="sj_xx">
                <view>
                    <text>A、</text>
                    <text>java Test . class</text>
                </view>
                <view>
                    <text>B、</text>
                    <text>javac Test . class</text>
                </view>
                <view>
                    <text>C、</text>
                    <text>java Test</text>
                </view>
                <view>
                    <text>D、</text>
                    <text>javac Test</text>
                </view>
            </view>
        </view>
    </view>
</template>

<script>
    export default {
        name: "index"
    }
</script>

<style scoped>

</style>