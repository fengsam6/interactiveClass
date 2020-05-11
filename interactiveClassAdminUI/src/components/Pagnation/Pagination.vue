<template>
  <div :class="pageStyle">
    <!--    pageNum ，pageSize变了会自动触发事件updateDataList,可以重写pageSizes，pageSize-->
    <el-pagination
      background
      layout="total,sizes,prev, pager, next,jumper"
      :page-sizes="pageSizes"
      :page-size="page.pageSize"
      :total="totalSize"
      :current-page.sync="page.curPage"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { getDefaultPage } from '@/utils/commonUtils'
export default {
  name: 'Pagination',
  props: {
    pageStyle: {
      type: String,
      default: 'pageStyle'
    },
    page: {
      type: Object,
      default: function() {
        return getDefaultPage()
      }
    },
    totalSize: {
      type: Number,
      default: 0
    }

  },
  data() {
    return {
      pageSizes: [20, 50, 100, 200]
    }
  },
  mounted() {

  },
  methods: {
    handleSizeChange: function(pageSize) {
      this.page.pageSize = pageSize
      // pageNum ，pageSize变了会自动触发事件updateDataList
      this.$emit('updateDataList', this.page)
    },
    handleCurrentChange(curPage) {
      this.page.curPage = curPage
      // pageNum ，pageSize变了会自动触发事件updateDataList
      this.$emit('updateDataList', this.page)
    }
  }
}
</script>

<style scoped>
  .pageStyle{
    width: 600px;
    margin: 3px auto;
  }
</style>
