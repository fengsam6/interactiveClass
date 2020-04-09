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
					<text v-if="IsTeacher" class="m_label">我教的课</text>
					<text v-else class="m_label">我听的课</text>
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
						:value="ClassCode"
						placeholder="请输入班级邀请码"
						border=true
						required
						@change="onClassCodeChange"
				/>
			</van-cell-group>
		</van-dialog>
		<view style="width: 100%;">
			<van-row>
				<view>
					<scroll-view :scroll-top="scrollTop" scroll-y="true" class="scroll-Y" @scrolltoupper="upper" @scrolltolower="lower"
								 @scroll="scroll">
						<view v-for="(item,i) in [1,2,3,4,5,6]" class="scroll-view-item uni-bg-red">
							<van-row>
								<view class="cls_list">
									<view class="cls_list_1">
										<view class="cls_list_title cls_fontSize">
											JAVA第一期
										</view>
										<view class="cls_list_icon cls_fontSize" @click="clsIsShow=true">
											<van-icon name="bars" />
										</view>
									</view>
									<view v-for="(item2,i1) in [1,2,3,4,5,6]" @click="managerCls">
										<view class="cls_list_2">
											<view class="cls_list2_left">
												1501班
											</view>
											<view class="cls_list2_icon">
												<van-icon name="arrow" />
											</view>
										</view>
										<view>
											32人
										</view>
										<view class="line"></view>
									</view>
								</view>
							</van-row>
						</view>
					</scroll-view>
				</view>
		</van-row>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				title: 'Hello',
				IsTeacher:true,
				addKcShow:false,
				joinClassshow:false,
				ClassCode:'',
				clsIsShow:false,
				actions: [
					{
						name: '创建课程'
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
		onLoad() {
		},
		methods: {
			onSelect(event) {
				if (event.detail.name == '创建课程') {
					this.encourse();
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
				console.log(this.ClassCode);
				this.ClassCode='';
			},
			onClassCodeChange(event) {
				// event.detail 为当前输入的值
				console.log(event.detail);
				this.ClassCode = event.detail;
			},
			managerCls(){
				uni.navigateTo({
					url: '/pages/class/index'
				});
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
		margin: 5px 10px;
	}
	.line{
		width: 100%;
		margin: 0 auto;
		height: 1px;
		background-color: #D8D8D8;
	}
	.m_label{
		font-size: 20px;
	}
	.cls_list_1{
		display: flex;
		height: 60px;
		justify-content:space-between;
	}
	.cls_list_2{
		display: flex;
		height: 20px;
		justify-content:space-between;
	}
	.cls_fontSize{
		font-size: 26px;
	}
</style>
