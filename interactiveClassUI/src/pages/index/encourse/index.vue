<template>
    <view style="width: 100%;margin-left: 10px;margin-right: 10px;">
        <view class="course_top">
            <scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y" @scrolltoupper="upper" @scrolltolower="lower"
                         @scroll="scroll">
                <van-cell-group>
                    <view class="course_title">
                        课程
                    </view>
                    <van-field
                            :value="courseName"
                            placeholder="请输入课程名"
                            @change="getCourseName"
                            input-align="left"
                    >
                    </van-field>
                    <view class="course_title" style="display: flex;justify-content:space-between;">
                        <view class="course_title">班级</view>
                        <view style="margin-top:4px;margin-right: 15px;"><van-button type="primary" size="small" @click="addClass">添加班级</van-button></view>
                    </view>
                        <van-field v-for="(item,i) in classNum"
                                :value="item.value"
                                :name="item.name"
                                :key="i"
                                 @change="getClassName($event,item.name)"
                                placeholder="请输入班级名称"
                                input-align="left"
                        >
                            <van-button slot="button" size="small" type="danger" @click="delClass(i)"
                            >删除</van-button
                            >
                        </van-field>
                </van-cell-group>
            </scroll-view>
        </view>
        <view class="course_Confirm">
            <view class="completeBtn">
                <van-button round type="info" size="large" @click="addCourseSub">
                    <view style="font-size: 20px;">完成</view>
                </van-button>
            </view>
        </view>
        <van-toast id="van-toast"/>
    </view>
</template>
<script>
    import Toast from '../../../wxcomponents/vant/toast/toast';
    export default {
        data() {
            return {
                courseName: '',
                classNum:[1]
            }
        },
        methods:{
            addClass(){
                var len=this.classNum.length;
                var param={
                    name:'className'+(len+1),
                    value:''
                };
                this.classNum.push(param);
                console.log(this.classNum);
            },
            delClass(event){
                this.classNum.splice(event,1);
            },
            getCourseName(event){
                this.courseName=event.detail;
            },
            getClassName(event,className){
                this.classNum.map(function(item){
                    if(item.name==className){
                        item.value=event.detail;
                    }
                    return item;
                });
            },
            addCourseSub() {
                this.classNum.filter(function (item) {
                    if(item.value.replace(/\s/ig,'')!='')
                    return item;
                });
                var resultJson={
                    courseName:this.courseName,
                    className:this.classNum
                }
                Toast.success(resultJson);
            }
        }
    }
</script>
<style scoped>
.course_title{
    font-size: 22px;
    font-weight: bold;
}
.course_Confirm{
    height: 60px;
    width: 100%;
    position: fixed;
    bottom: 30rpx;
}
.completeBtn{
    width: 70%;
    margin: 4px auto;
}
</style>