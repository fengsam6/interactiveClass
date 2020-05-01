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
        name: "paperList.vue"
    }
</script>

<style scoped>
    .sj_xx view{
        min-height: 46px;
        background: #fff;
        box-shadow: 0 4px 8px 0 rgba(28,31,33,.1);
        border-radius: 8px;
        margin-bottom: 20px;
        padding: 20px 12px;
        box-sizing: border-box;
    }
    .sj_xx text,.sj_st{
        font-family: PingFangSC-Regular;
        font-size: 14px;
        color: #1c1f21;
        letter-spacing: 0;
        line-height: 22px;
        word-break: break-word;
    }
</style>