<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu

        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in routes" :key="route.path" v-permission="['student','teacher','admin']" :item="route" :base-path="route.path" />
      </el-menu>

      <!--拓展左侧导航-->
      <el-menu
        v-permission="['admin']"
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-menu" />
            <span>系统管理</span>
          </template>
          <el-menu-item index="1-1">
            <el-link href="http://localhost:8080/swagger-ui.html" target="_blank">接口文档浏览</el-link>
          </el-menu-item>
          <el-menu-item index="1-2">
            <el-link href="/dev-api/druid" target="_blank">数据访问监控</el-link>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'

export default {
  components: { SidebarItem, Logo },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      const r = this.$router.options.routes
      debugger
      return this.$router.options.routes
    },
    baseUrl() {
      return process.env.VUE_APP_BASE_API || '/dev-api'
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
