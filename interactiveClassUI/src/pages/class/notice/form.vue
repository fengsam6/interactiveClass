<template>
    <van-dialog
            use-slot
            title="发布公告"
            :show="showfbgg"
            show-cancel-button
            @close="showfbgg=false"
            @confirm="publishGg"
    >
        <van-cell-group>
            <van-field
                    :value="noticeTitle"
                    placeholder="请输入公告标题"
                    label="公告标题"
                    border=true
                    required
                    @change="noticeValueChange($event,'noticeTitle')"
            />
            <van-field
                    :value="noticeContent"
                    label="公告内容"
                    placeholder="请输入公告内容"
                    border=true
                    required
                    @change="noticeValueChange($event,'noticeContent')"
            />
        </van-cell-group>
    </van-dialog>
</template>

<script>
    import {addNotice} from "@/api/notice"
    export default {
        name: "form",
        props:{
            course:{
                type:Object,
                default:function () {
                    return{}
                }
            }
        },
        data(){
            return{
                notice:{
                    noticeTitle:'',
                    noticeContent:'',
                    publishUserId:'',
                    classId:'',
                    courseId:''
                },
                showfbgg:false
            }
        },
        methods: {
            noticeValueChange(event, index) {
                // event.detail 为当前输入的值
                if (index == 'noticeTitle') {
                    this.notice.noticeTitle = event.detail;
                }
                if (index == 'noticeContent') {
                    this.notice.noticeContent = event.detail;
                }
            },
            showDialog(){
                this.showfbgg = true
            },
            publishGg(){
                this.notice.classId=this.course.item.id;
                this.notice.courseId=this.course.courseId;
                addNotice(this.notice).then(resp => {
                    this.successAlert("添加通知成功");
                    this.$emit("refreshNotice")
                });
            },
        }
    }
</script>

<style scoped>

</style>