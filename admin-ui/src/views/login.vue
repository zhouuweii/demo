<template>
  <div class="login">
    <el-form class="login-form">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="登陆" name="login">
          <el-form ref="loginForm" :model="loginForm" :rules="loginRules">
            <h3 class="title">若依后台管理系统</h3>
            <el-form-item prop="username">
              <el-input v-model="loginForm.username" auto-complete="off" placeholder="账号" type="text">
                <svg-icon slot="prefix" class="el-input__icon input-icon" icon-class="user"/>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                v-model="loginForm.password"
                auto-complete="off"
                placeholder="密码"
                type="password"
                @keyup.enter.native="handleLogin"
              >
                <svg-icon slot="prefix" class="el-input__icon input-icon" icon-class="password"/>
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <el-input
                v-model="loginForm.code"
                auto-complete="off"
                placeholder="验证码"
                style="width: 63%"
                @keyup.enter.native="handleLogin"
              >
                <svg-icon slot="prefix" class="el-input__icon input-icon" icon-class="validCode"/>
              </el-input>
              <div class="login-code">
                <img :src="codeUrl" class="login-code-img" @click="getCode"/>
              </div>
            </el-form-item>
            <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
            <el-form-item style="width:100%;">
              <el-button
                :loading="loading"
                size="medium"
                style="width:100%;"
                type="primary"
                @click.native.prevent="handleLogin"
              >
                <span v-if="!loading">登 录</span>
                <span v-else>登 录 中...</span>
              </el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="register">
          <el-form ref="registerForm" :model="registerForm" :rules="registerRules">
            <h3 class="title">若依后台管理系统</h3>
            <el-form-item prop="username">
              <el-input v-model="registerForm.username" auto-complete="off" placeholder="用户名" type="text">
                <svg-icon slot="prefix" class="el-input__icon input-icon" icon-class="user"/>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                v-model="registerForm.password"
                auto-complete="off"
                placeholder="密码"
                type="password"
                @keyup.enter.native="handleLogin"
              >
                <svg-icon slot="prefix" class="el-input__icon input-icon" icon-class="password"/>
              </el-input>
            </el-form-item>
            <el-form-item prop="phone">
              <el-input v-model="registerForm.phone" auto-complete="off" placeholder="账号" type="text">
                <svg-icon slot="prefix" class="el-input__icon input-icon" icon-class="user"/>
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <el-input
                v-model="registerForm.code"
                auto-complete="off"
                placeholder="验证码"
                style="width: 63%"
                @keyup.enter.native="handleLogin"
              >
                <svg-icon slot="prefix" class="el-input__icon input-icon" icon-class="validCode"/>
              </el-input>
              &nbsp;<el-button @click="getSmsCode()" type="primary">&nbsp;获取验证码</el-button>
            </el-form-item>
            <el-form-item style="width:100%;">
              <el-button
                :loading="loading"
                size="medium"
                style="width:100%;"
                type="primary"
                @click.native.prevent="register"
              >
                <span v-if="!loading">注 册</span>
                <span v-else>注 册 中...</span>
              </el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>


    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2020-2021 Weiter All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import {getCodeImg} from "@/api/login";
import * as loginApi from "@/api/login";
import Cookies from "js-cookie";
import {decrypt, encrypt} from '@/utils/jsencrypt'

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      cookiePassword: "",
      loading: false,
      redirect: undefined,
      //标签页
      activeName: 'login',
      //登陆表单
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      //注册表单
      registerForm: {
        username: "",
        password: "",
        phone: "",
        code: "",
        smsId: ""
      },
      //登陆校验
      loginRules: {
        username: [
          {required: true, trigger: "blur", message: "用户名不能为空"}
        ],
        password: [
          {required: true, trigger: "blur", message: "密码不能为空"}
        ],
        code: [{required: true, trigger: "change", message: "验证码不能为空"}]
      },
      //注册校验
      registerRules: {
        username: [
          {required: true, trigger: "blur", message: "用户名不能为空"}
        ],
        password: [
          {required: true, trigger: "blur", message: "密码不能为空"}
        ],
        phone: [
          {required: true, trigger: "change", message: "手机号不能为空"},
          {min: 11, max: 11, message: '请输入11位手机号码', trigger: 'blur'},
          { pattern:/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/, message: "请输入合法手机号/电话号", trigger: "blur" }
        ],
      }
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    //登陆验证码
    getCode() {
      getCodeImg().then(res => {
        this.codeUrl = "data:image/jpg;base64," + res.data.image;
        this.loginForm.uuid = res.data.uuid;
      });
    },
    //短信验证码
    getSmsCode() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          loginApi.getSmsCode(this.registerForm.phone).then(res => {
            if (res.success) {
              this.$message({message: '获取验证码成功', type: 'success', duration: 1500})
              this.registerForm.smsId = res.data;
            } else {
              if (res.message) {
                this.$message.error(res.message)
              } else {
                this.$message({message: '获取验证码失败', type: 'error', duration: 1500})
              }
            }
          });
        }
      });
    },
    //登陆
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, {expires: 30});
            Cookies.set("password", encrypt(this.loginForm.password), {expires: 30});
            Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({path: this.redirect || "/"}).catch(() => {
            });
          }).catch(() => {
            this.loading = false;
            this.getCode();
          });
        }
      });
    },
    //注册
    register() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          loginApi.register(this.registerForm).then((res) => {
            if (res.success) {
              this.$notify({title: '注册成功', message: '您好，欢迎你成为我们中的一员，祝您使用愉快！', type: 'success', duration: 3000})
              this.activeName="login"
            } else {
              if (res.message) {
                this.$message.error(res.message)
              } else {
                this.$message({message: '注册失败', type: 'error', duration: 1500})
              }
            }
          });
        }
      });
    },
    //获取cookie
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    //标签页
    handleClick(tab, event) {
    }
  }
};
</script>

<style lang="scss" rel="stylesheet/scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;

  .el-input {
    height: 38px;

    input {
      height: 38px;
    }
  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}

.login-code {
  width: 33%;
  height: 38px;
  float: right;

  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}

.login-code-img {
  height: 38px;
}
</style>
