<template>
    <view>
        <van-dialog
                use-slot
                title="添加试卷"
                :show="addPaperBtn"
                show-cancel-button
                @close="addPaperBtn=false"
                @confirm="addPaper"
        >
            <van-cell-group>
                <van-field
                        :value="paperName"
                        placeholder="请输入试卷标题"
                        border=true
                        required
                        @change="paperValueChange($event,'paperName')"
                />
                <van-field
                        :value="paperTime"
                        placeholder="请输入考试时长"
                        border=true
                        required
                        @change="paperValueChange($event,'paperTime')"
                />
            </van-cell-group>
        </van-dialog>
    </view>
</template>
<script>
    import {createPaper} from "@/api/paper"
    export default {
        name: "addPaperForm",
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
                paper:{
                    paperName:'',
                    paperTime:'',
                    classId:'',
                    courseId:''
                },
                addPaperBtn:false
            }
        },
        methods: {
            paperValueChange(event, index) {
                // event.detail 为当前输入的值
                if (index == 'paperName') {
                    this.paper.paperName= event.detail;
                }
                if (index == 'paperTime') {
                    this.paper.paperTime = event.detail;
                }
            },
            showDialog(){
                this.addPaperBtn = true
            },
            addPaper(){
                this.paper.classId=this.course.item.id;
                this.paper.courseId=this.course.courseId;
                createPaper(this.paper).then(resp => {
                    this.successAlert("添加试卷成功");
                    this.$emit("queryPaper")
                });
            },
        }
    }
</script>

<style scoped>

</style>