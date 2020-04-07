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
		<van-row>
				<van-col span="10" offset="4">
					<text v-if="IsTeacher">我教的课</text>
					<text v-else>我听的课</text>
				</van-col>
				<van-col span="6" offset="4">
					<view class="m_contant2_l">
						<van-icon  class="m_add" name="add" @click="addkc" />
					</view>
				</van-col>
		</van-row>
		<view class="line"></view>
		<van-popup
				:show="addKcShow"
				position="bottom"
				closeable=true
				round=true
				close-on-click-overlay=true
				custom-style="height: 20%;"
				@close="onClose"
		>
			<van-cell-group>
				<van-cell title="单元格" value="添加课程" />
				<van-cell title="单元格" value="加入班级" />
			</van-cell-group>
		</van-popup>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				title: 'Hello',
				IsTeacher:true,
				addKcShow:false
			}
		},
		onLoad() {

		},
		methods: {
			addkc(){
				this.addKcShow=true;
			},
			onClose(){
				this.addKcShow=false;
			}
		}
	}
</script>

<style>
	.index_img1{
		width: 100rpx;
		height: 80rpx;
	}
	.m_add{
		width: 60rpx;
		height: 60rpx;
		margin-left: 100rpx;
	}
	.line{
		width: 100%;
		margin: 0 auto;
		height: 1px;
		background-color: #D8D8D8;
	}
</style>
