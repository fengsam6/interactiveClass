<template>
	<view>
		<van-row>
			<view class="page-section swiper">
				<view class="page-section-spacing">
					<swiper class="swiper" indicator-dots="true" autoplay="true" interval="2000" duration="duration">
						<swiper-item class="m_wrap">
							<image class="m_wrap" src="/static/images/swaper1.jpg"/>
						</swiper-item>
						<swiper-item class="m_wrap">
							<image class="m_wrap" src="/static/images/swaper2.jpg"/>
						</swiper-item>
						<swiper-item class="m_wrap">
							<image class="m_wrap" src="/static/images/swaper3.jpg"/>
						</swiper-item>
					</swiper>
				</view>
			</view>
		</van-row>
		<view style="margin-top: 8px"></view>
		<van-row>
			<van-col span="8" offset="4">
				<view class="m_contant_box">
					<navigator url="/pages/index/courseware/courseware">
						<image src="/static/icon/kj.png" class="index_img1"></image>
						<view>课件库</view>
					</navigator>
				</view>
			</van-col>
			<van-col span="8" offset="4">
				<view class="m_contant_box">
					<navigator url="/pages/index/paper/paper">
						<image src="/static/icon/paper.png" class="index_img1"></image>
						<view>试卷库</view>
					</navigator>
				</view>
			</van-col>
		</van-row>
		<view class="line"></view>
		<van-row>
				<van-col span="10" offset="4">
					<view style="line-height: 46px;height: 46px">
						<text v-if="IsTeacher" class="m_label">我教的课</text>
						<text v-else class="m_label">我听的课</text>
					</view>
				</van-col>
				<van-col span="6" offset="4">
					<view class="m_contant2_l">
						<image src="/static/icon/add.png" class="m_add" @click="addKcShow=true"></image>
					</view>
				</van-col>
		</van-row>
		<view class="line"></view>
		<van-action-sheet
			:show="addKcShow"
			:actions="actions"
			@select="onSelect"
			@cancel="addKcShow=false"
			round
			cancel-text="取消"
	/>
		<van-action-sheet
				:show="clsIsShow"
				:actions="actionsCls"
				@select="onSelectCls"
				@cancel="clsIsShow=false"
				round
				cancel-text="取消"
		/>
		<van-dialog
				use-slot
				title="加入班级"
				:show="joinClassshow"
				show-cancel-button
				@close="joinClassshow=false"
				@confirm="enjoinClass"
		>
			<van-cell-group>
				<van-field
						:value="classCode"
						placeholder="请输入班级邀请码"
						border=true
						required
						@change="onClassCodeChange($event,'joinCls1')"
				/>
			</van-cell-group>
		</van-dialog>
		<van-dialog
				use-slot
				title="创建班级"
				:show="createClassshow"
				show-cancel-button
				@close="createClassshow=false"
				@confirm="subCreateClass"
		>
			<van-cell-group>
				<van-field
						:value="classInfo.className"
						placeholder="请输入班级名称"
						label="班级名称"
						border=true
						required
						@change="onClassCodeChange($event,'createCls1')"
				/>
				<van-field
						:value="classInfo.classNum"
						placeholder="请输入班级人数"
						label="班级人数"
						border=true
						required
						@change="onClassCodeChange($event,'createCls2')"
				/>
				<van-field
						:value="classInfo.classIntroduce"
						type="textarea"
						placeholder="请输入班级简介"
						label="班级简介"
						border=true
						autosize="true"
						required
						@change="onClassCodeChange($event,'createCls3')"
				/>
			</van-cell-group>
		</van-dialog>
		<view style="width: 100%;">
			<van-row>
				<view>
					<scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y" @scrolltoupper="upper" @scrolltolower="lower"
								 @scroll="scroll">
						<view v-for="(item,i) in mCourse" class="scroll-view-item">
								<view class="cls_list">
									<view class="cls_list_1">
										<view class="cls_list_title cls_fontSize">
											{{item.courseName}}
										</view>
										<view class="cls_list_icon cls_fontSize" @click="clsIsShow=true" style="font-size:20px;">
											<van-icon name="bars" />
										</view>
									</view>
									<view @click="managerCls(item)">
										<view class="cls_list_2">
											<view>
												<view style="height: 50%;line-height: 30px;">
													{{item.className}}
												</view>
												<view style="color: #FF5722;height: 50%;line-height: 30px;">
													{{item.classNum}}人
												</view>
											</view>
											<view class="cls_list2_icon" style="margin: auto 4px;font-size:20px;">
												<van-icon name="arrow" />
											</view>
										</view>
										<view class="line"></view>
									</view>
								</view>
						</view>
					</scroll-view>
				</view>
		</van-row>
		</view>
	</view>
</template>
<script>
	import {createClass} from "@/api/class"
	import {joinClass} from "@/api/classUser"
	import {queryMCourse} from "@/api/course"
	import {getStoreUserInfo, saveUserInfoStore,getUserInfo} from '@/api/user'
	import {getStorage,setStorage} from '@/utils/storage'
	export default {
		data() {
			return {
				title: 'Hello',
				IsTeacher:true,
				userInfo:{},
				mCourse:[],
				classInfo:{
					classId:'',
					id:'',
					className:'',
					classNum:'',
					classIntroduce:''
				},
				addKcShow:false,
				createClassshow:false,
				joinClassshow:false,
				classCode:'',
				clsIsShow:false,
				actions: [
					{
						name: '创建课程'
					},
					{
						name: '创建班级'
					},
					{
						name: '加入班级'
					}
				],
				actionsCls:[
					{
						name: '管理'
					},
					{
						name: '置顶'
					}
				],
			}
		},
		onShow() {
			this.doGetStoreUserInfo()
			this.queryMCourseInfo();
		},
		mounted(){
			this.doGetStoreUserInfo();
			this.classInfo.createUserId=this.userInfo.id;
			this.queryMCourseInfo();
		},
		onLoad() {

		},
		methods: {
			onSelect(event) {
				if (event.detail.name == '创建课程') {
					this.encourse();
				}
				if (event.detail.name == '创建班级') {
					this.addKcShow = false;
					this.createClassshow=true;
				}
				if (event.detail.name == '加入班级') {
					this.joinClass();
				}

			},
			onSelectCls(event) {
				console.log(event.detail);
			},
			encourse() {
				uni.navigateTo({
					url: '/pages/index/encourse/index'
				});
			},
			joinClass() {
				this.addKcShow = false;
				this.joinClassshow = true;
				console.log("加入班级")
			},
			enjoinClass() {
				joinClass({classCode:this.classCode}).then(resp => {
					this.successAlert("加入班级成功");
				});
				this.classCode='';
			},
			onClassCodeChange(event,index) {
				// event.detail 为当前输入的值
				if(index=='joinCls1'){
					this.classCode = event.detail;
				}
				if(index=='createCls1'){
					this.classInfo.className = event.detail;
				}
				if(index=='createCls2'){
					this.classInfo.classNum = event.detail;
				}
				if(index=='createCls3'){
					this.classInfo.classIntroduce = event.detail;
				}
				console.log(event.detail);
			},
			subCreateClass(){
				createClass(this.classInfo).then(resp => {
					this.successAlert("创建班级成功")

				})
				console.log(this.classInfo);
			},
			managerCls(item){
				uni.navigateTo({
					url: '/pages/class/index?item='+encodeURIComponent(JSON.stringify(item))
				});
			},
			async doGetStoreUserInfo() {
				this.userInfo = await getStoreUserInfo()
				console.log(this.userInfo)
			},
			queryMCourseInfo(){
				console.log(this.userInfo.id);
				var data={
					userId:this.userInfo.id
				};
				queryMCourse(data).then(resp => {
					this.mCourse=resp;
					// this.successAlert("创建数据成功")
				})
			}
		}
	}


</script>

<style>
	.m_wrap{
		width: 100%;
	}
	.index_img1{
		width: 100rpx;
		height: 80rpx;
	}
	.m_add{
		width: 60rpx;
		height: 60rpx;
		margin-top: 10px;
	}
	.line{
		width: 100%;
		margin: 0 auto;
		height: 1px;
		background-color: #D8D8D8;
	}
	.m_label{
		font-family:"微软雅黑","黑体","宋体";
		font-size: 18px;
		font-weight: 500;
	}
	.cls_list{
		margin-left: 20px;
		margin-right: 20px;
		border: 1px solid #F0F0F0;
		box-shadow: 0 4px 8px 0 rgba(28,31,33,.1);
		margin-top: 2px;
		margin-bottom: 6px;
	}
	.cls_list_1{
		display: flex;
		height: 50px;
		justify-content:space-between;
		background-color: #009688;
	}
	.cls_list_2{
		display: flex;
		height: 60px;
		margin: auto 4px;
		justify-content:space-between;
	}
	.cls_fontSize{
		font-size: 14px;
		font-weight: bold;
		color: white;
		margin: auto 4px;
	}
</style>
