<template>
  <div :class="[`nav-theme-${navTheme}`, `nav-layout-${navStyle}`]">
    <a-layout id="components-layout-demo-side" style="min-height: 100vh">
      <a-layout-sider
        v-if="navStyle === 'left'"
        :theme="navTheme"
        :trigger="null"
        collapsible
        v-model="collapsed"
      >
        <div class="logo">vue pro</div>
        <SiderMenu></SiderMenu>
      </a-layout-sider>
      <a-layout>
        <a-layout-header style="background: #fff; padding: 0">
          <a-icon
            class="trigger"
            :type="collapsed ? 'menu-unfold' : 'menu-fold'"
            @click="collapsed = !collapsed"
          ></a-icon>
          <Header></Header>
        </a-layout-header>
        <a-layout-content style="margin: 0 16px">
          <router-view></router-view>
        </a-layout-content>
        <a-layout-footer style="text-align: center">
          <Fooder />
        </a-layout-footer>
      </a-layout>
    </a-layout>
    <SettingDraw></SettingDraw>
  </div>
</template>

<script>
import Header from "./Header";
import SiderMenu from "./SiderMenu";
import Fooder from "./Fooder";
import SettingDraw from "../components/SettingDraw";

export default {
  name: "BasicLayout",
  data() {
    return {
      collapsed: false
    };
  },
  computed: {
    navTheme() {
      return this.$route.query.navTheme || "dark";
    },
    navStyle() {
      return this.$route.query.navStyle || "left";
    }
  },
  components: { Fooder, SiderMenu, Header, SettingDraw }
};
</script>

<style scoped>
.trigger {
  padding: 0 20px;
  line-height: 64px;
  font-size: 20px;
}

.trigger:hover {
  background: #eeeeee;
}

.logo {
  height: 64px;
  line-height: 64px;
  text-align: center;
  overflow: hidden;
}

.nav-theme-dark >>> .logo {
  color: #ffffff;
}
</style>
