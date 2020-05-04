<template>
  <div class="dashboard-container">
    <div class="dashboard-text">当前登录用户: {{ name }}</div>
    <p class="passage">欢迎使用。这是一个互动课堂毕设系统，前台界面展示使用微信小程序开发，后台使用vue开发PC管理系统，系统主要功能有用户管理、班级管理、课程管理、试卷管理。</p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Index',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
  .passage{
    text-indent: 2px;
    text-align: center;
  }
  .dashboard {
    &-container {
      margin: 30px;
    }
    &-text {
      font-size: 18px;
      line-height: 25px;
    }
  }
</style>
