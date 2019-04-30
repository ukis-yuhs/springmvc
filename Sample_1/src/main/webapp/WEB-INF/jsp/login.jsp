<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%--网站图标--%>
        <link rel="shortcut icon" href="../assets/image/favicon.ico" type="image/x-icon" />
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="../assets/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assets/css/login.css">
        <title>登录</title>
    </head>
    <body>
        <div class="half" id="publicidade">
            <div class="fundo-cor"></div>
            <div class="fundo"></div>
            <div class="w-100 h-100 d-flex justify-content-center align-items-center p-5">
                <h1 class="hero-title text-white p-5">
                    展示你要 <span>表达</span> 的词语
                </h1>
            </div>
        </div>
        <div class="half" id="wpLogin">
            <div class="flex-container-center">
                <form action="/user/login" method="post">
                <div id="loginBox">
                    <div class="container">
                        <div class="login-header flex-container-center">
                            <h4>用户登录</h4>
                        </div>
                        <!-- Content here -->
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">用户名</span>
                            </div>
                            <input type="text" class="form-control" placeholder="请输入用户名" aria-label="username" id="username" name="username" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon2">密　码</span>
                            </div>
                            <input type="password" class="form-control" placeholder="请输入密码" aria-label="password" id="password" name="password" aria-describedby="basic-addon2">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <input type="checkbox" aria-label="Checkbox for following text input" id="rememberMe" name="rememberMe">
                                </div>
                            </div>
                            <input type="text" class="form-control" aria-label="Text input with checkbox" value="记住我" readonly onfocus="false">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <table border="0" width="100%">
                                    <tr>
                                        <td width="40%">
                                            <input class="form-control" type="text" id="randomcode" name="randomcode" required="" placeholder="验证码">
                                        </td>
                                        <td width="30%" style="text-align:right;">
                                            <img id="checkImg" src="" />
                                        </td>
                                        <td width="30%" style="text-align:right;">
                                            <button type="button" class="btn btn-xs" style="padding:2px 15px;margin-bottom:7px;" id="getCheckImg">看不清</button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <span class="text-danger mt-2" id="errorSpan" hidden>
                            用户名或密码不正确
                        </span>
                        <button type="submit" class="btn btn-login" id="btn_login">登录</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="../assets/js/jquery-3.4.0.min.js"></script>
        <script src="../assets/js/popper.min.js"></script>
        <script src="../assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script language="JavaScript">
            $(function () {
                //初期表示触发
                getCheckImg();
                //更新验证码图片绑定
                $("#getCheckImg").click(function(){
                    console.log("getCheckImg");
                    getCheckImg();
                })
            });
            function getCheckImg() {
                $.ajax({
                    url: '/user/getCheckImg',
                    data: null,
                    type: 'POST',
                    success: function (ret) {
                        $("#checkImg").attr("src","data:image/png;base64," + ret.data);
                    },
                    error: function (err) {
                        alert(JSON.stringify(err));
                    }
                });
            }
        </script>
    </body>
</html>
