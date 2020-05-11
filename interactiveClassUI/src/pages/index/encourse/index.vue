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
                            :value="courseInfo.courseName"
                            placeholder="请输入课程名"
                            @change="getCourseValue($event,'courseName')"
                            input-align="left"
                    >
                    </van-field>
                    <van-field
                              :value="courseInfo.courseIntroduce"
                              placeholder="请输入课程简介"
                              @change="getCourseValue($event,'courseIntroduce')"
                              input-align="left"
                        >
                    </van-field>
                    <van-field
                            :value="courseInfo.beginTime"
                            placeholder="请输入上课时间(hh:mm:ss)"
                            @change="getCourseValue($event,'beginTime')"
                            input-align="left"
                    >
                    </van-field>
                     <van-field
                             :value="courseInfo.endTime"
                             placeholder="请输入下课时间(hh:mm:ss)"
                             @change="getCourseValue($event,'endTime')"
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
    import {createCourse} from "@/api/course"
    import {getStorage, setStorage} from '@/utils/storage'
    export default {
        data() {
            return {
                courseInfo:{
                    courseName: '',
                    createdUserId:'',
                    courseIntroduce:'',
                    beginTime:'',
                    endTime:'',
                   className:''
                },
                classNum:[]
            }
        },
        mounted(){
            var userInfo=getStorage('user');
            this.courseInfo.createdUserId=userInfo.id;
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
                getCourseValue(event,option){
                if(option=='courseName'){
                    this.courseInfo.courseName=event.detail;
                }
                if(option=='courseIntroduce'){
                    this.courseInfo.courseIntroduce=event.detail;
                }
                if(option=='beginTime'){
                    this.courseInfo.beginTime=event.detail;
                }
                if(option=='endTime'){
                    this.courseInfo.endTime=event.detail;
                }
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
                if(this.classNum.length<=0){
                    this.errorAlert('请输入班级');
                    return false;
                }
                var array=[];
                for(var i=0;i<this.classNum.length;i++){
                    array.push(this.classNum[i].value);
                }
                this.courseInfo.classNameList=array.toString();
                var resultJson=this.courseInfo;
                createCourse(this.courseInfo).then(resp => {
                    this.successAlert("创建课程成功")
                })
                this.successAlert(resultJson);
                console.log(resultJson);
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