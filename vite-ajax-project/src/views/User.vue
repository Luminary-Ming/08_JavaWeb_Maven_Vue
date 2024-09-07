<template>
    <div class="box">
        <!-- 用户数据表格 -->
        <el-button type="success" class="add" @click="drawerAdd = true">添加数据</el-button>
        <el-table :data="tableData" style="width: 80%" stripe border>
            <el-table-column prop="id" label="编号" />
            <el-table-column prop="username" label="姓名" />
            <el-table-column prop="type" label="职业" />
            <el-table-column prop="age" label="年龄" />
            <el-table-column prop="sex" label="性别" />
            <el-table-column prop="address" label="地址" />
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="primary" @click="updateUserBtn(scope.row)">修改</el-button>
                    <el-button type="danger" @click="deleteUser(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 添加用户的抽屉弹窗 -->
        <el-drawer v-model="drawerAdd" title="添加用户信息">
            <el-form :model="form" label-width="auto" style="max-width: 600px">
                <el-form-item label="姓名">
                    <el-input v-model="form.username" />
                </el-form-item>

                <el-form-item label="职业">
                    <el-input v-model="form.type" />
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="form.age" />
                </el-form-item>

                <el-form-item label="性别">
                    <el-radio-group v-model="form.sex">
                        <el-radio value="男">男</el-radio>
                        <el-radio value="女">女</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="地址">
                    <el-input v-model="form.address" />
                </el-form-item>

                <el-button type="primary" @click="addUser()">提交</el-button>
                <el-button type="danger" @click="drawerAdd = false">取消</el-button>
            </el-form>
        </el-drawer>

        <!-- 修改用户信息的抽屉弹窗 -->
        <el-drawer v-model="drawerUpdate" title="修改用户信息">
            <el-form :model="updateform" label-width="auto" style="max-width: 600px">
                <el-form-item label="姓名">
                    <el-input v-model="updateform.username" />
                </el-form-item>

                <el-form-item label="职业">
                    <el-input v-model="updateform.type" />
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="updateform.age" />
                </el-form-item>

                <el-form-item label="性别">
                    <el-radio-group v-model="updateform.sex">
                        <el-radio value="男">男</el-radio>
                        <el-radio value="女">女</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="地址">
                    <el-input v-model="updateform.address" />
                </el-form-item>

                <el-button type="primary" @click="updateUser()">提交</el-button>
                <el-button type="danger" @click="drawerUpdate = false">取消</el-button>
            </el-form>
        </el-drawer>
    </div>
</template>

<script>
// 导入 el 组件中的弹窗组件
import { ElMessage } from "element-plus";
// 导入 qs 组件，作用是把对象的属性封装成 key=vaule&key=vaule&...形式
import qs from "qs";
export default {
    data() {
        return {
            // 弹窗属性，控制弹窗出现，关闭
            // 添加的弹窗
            drawerAdd: false,
            // 修改的弹窗
            drawerUpdate: false,
            // 表格数据
            tableData: [],
            // 表单数据
            form: {
                username: "",
                type: "",
                age: "",
                sex: "",
                address: "",
            },
            // 修改的表单数据
            updateform: {
                username: "",
                type: "",
                age: "",
                sex: "",
                address: "",
            },
        };
    },
    methods: {
        // 添加用户
        addUser() {
            let xhr = new XMLHttpRequest();
            xhr.open("post", "http://localhost/vue/addUser");
            /*
                ajax提交数据采用post，需要设置提交数据的格式
                application/x-www-form-urlencoded : 普通表单方式 key=value&key=value
                multipart/form-data: 允许提交的数据中包含文件上传数据
            */
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            //xhr.send(`account=${this.user.account}&password=${this.user.password}`);
            xhr.send(qs.stringify(this.form));
            xhr.addEventListener("readystatechange", () => {
                if (xhr.status == 200 && xhr.readyState == 4) {
                    // 响应返回的是 1 表示添加成功，触发弹窗
                    if (xhr.responseText == 1) {
                        ElMessage({
                            message: "添加成功",
                            type: "success",
                        });
                        // 添加成功重新加载数据;
                        this.loadUserData();
                        // 点击提交后关闭弹窗
                        this.drawerAdd = false;
                        // 提交后清空填写的数据
                        this.form = {
                            username: "",
                            type: "",
                            age: "",
                            sex: "",
                            address: "",
                        };
                    }
                }
            });
        },

        // 根据 id 删除用户
        deleteUser(id) {
            let xhr = new XMLHttpRequest();
            xhr.open("post", "http://localhost/vue/deleteUser");
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(`id=${id}`);
            xhr.addEventListener("readystatechange", () => {
                if (xhr.status == 200 && xhr.readyState == 4) {
                    // 响应返回的是 1 表示删除成功，触发弹窗
                    if (xhr.responseText == 1) {
                        ElMessage({
                            message: "删除成功",
                            type: "success",
                        });
                        // 删除成功重新加载数据;
                        this.loadUserData();
                    }
                }
            });
        },

        // 点击修改按钮
        updateUserBtn(row) {
            // 打开弹窗
            this.drawerUpdate = true;
            // 将这行数据赋值给要修改的 updateform 这个对象
            this.updateform = row;
        },

        // 根据 id 修改用户信息
        updateUser() {
            let xhr = new XMLHttpRequest();
            xhr.open("post", "http://localhost/vue/updateUser");
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send(qs.stringify(this.updateform));
            console.log("this.updateform" + this.updateform);

            xhr.addEventListener("readystatechange", () => {
                if (xhr.status == 200 && xhr.readyState == 4) {
                    if (xhr.responseText == 1) {
                        ElMessage({
                            message: "修改成功",
                            type: "success",
                        });
                        // 修改成功重新加载数据;
                        this.loadUserData();
                        // 点击提交后关闭弹窗
                        this.drawerUpdate = false;
                    }
                }
            });
        },

        // 从数据库中加载用户数据，渲染到页面表格上
        loadUserData() {
            // 创建 XMLHttpRequest 对象
            let xhr = new XMLHttpRequest();
            // 设置请求
            xhr.open("get", "http://localhost/vue/userList");
            // 发送请求
            xhr.send();
            // 监控 xhr 状态变化
            // function () 中这个 this 指的是 xhr
            // xhr.addEventListener("readystatechange", function () {
            //   if (xhr.status == 200 && xhr.readyState == 4) {
            //     console.log(this);
            //   }
            // });
            // () => 箭头函数中没有自己的 this，this 指的上级 this 的对象，即 Vue 实例
            xhr.addEventListener("readystatechange", () => {
                if (xhr.status == 200 && xhr.readyState == 4) {
                    // 通过 JSON.parse() 方法把字符串格式的 json 对象转成 json 对象
                    // JSON.parse(xhr.responseText) 返回的是一个数组
                    this.tableData = JSON.parse(xhr.responseText);
                }
            });
        },
    },
    /*
        常在 vue 生命周期中的 created() 方法发送 ajx 请求的原因？
            vue 实例已经被创建，并且初始化完毕。此时发送 ajax 可以访问组件的 data、computed 属性以及 methods 中的方法。
            在 created() 时期发送 ajx 请求可以避免不必要的等待时间，
            因为 DOM 的渲染和挂载可能是一个相对耗时的过程，特别是对于大型应用或复杂组件。
            特别是你的应用需要在用户看到页面之前就获取数据（比如，预加载数据到页面），那么在 created() 中发送 ajx 请求会更高效。
    */
    created() {
        // 调用 loadUserData() 方法
        this.loadUserData();
    },
    watch: {},
    // 注册组件
    components: { ElMessage, qs },
};
</script>

<style scoped>
.box {
    margin: 80px;
}
.add {
    margin-bottom: 5px;
}
</style>
