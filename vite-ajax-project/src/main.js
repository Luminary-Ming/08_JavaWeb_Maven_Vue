import "./assets/main.css";

import { createApp } from "vue";
// 全局引入 ElementPlue 组件（这种方式一般不采用，真实项目按需引入）
import ElementPlus from "element-plus";
// 全局引入 ElementPlus 的样式
import "element-plus/dist/index.css";

import App from "./App.vue";

// 构建出Vue的实例
const app = createApp(App);

// 将ElementPlus的所有组件绑定到Vue实例上
app.use(ElementPlus);
// Vue实例与页面绑定
app.mount("#app");
