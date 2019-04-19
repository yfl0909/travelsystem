<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>YFL旅游网-登录</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>
</head>

<script>
    $(function () {
        $("#submit").click(function () {
            $.post("<%=request.getContextPath()%>/loginServlet", $("#loginForm").serialize(), function (data) {
                console.log(data.flag);
                if(data.flag){
                    //登陆成功
                    location.href = "index.jsp";
                }else {
                    //登陆失败
                    $("#errorMsg").html(data.errorMsg);
                }
            });
        });
    })
</script>
<script>
    //图片点击事件
    function changeCheckCode(img) {
        img.src="<%=request.getContextPath()%>/checkCodeServlet?"+new Date().getTime();
    }

    function saveUserInfo() {
        var rmb = document.getElementById("remember");
        if(rmb.checked){
            rmb.value = "1";
        }else{
            rmb.value = "0";
        }
    }
</script>

<body>
<!--引入头部-->
<div id="header"></div>
<%@include file="header.jsp"%>
<!-- 头部 end -->
<section id="login_wrap">
    <div class="fullscreen-bg" style="background: url(images/login_bg_2.jpeg);height: 532px;"></div>

    <div class="login-box">
        <div class="title">
            <img src="images/login_logo.png" alt="">
            <span>欢迎登录YFL旅游账户</span>
        </div>

        <!--登录错误提示消息-->
        <div id="errorMsg" class="alert alert-danger" style="text-align: center" ></div>
        <form id="loginForm" action="" method="post" accept-charset="utf-8">
            <div class="login_inner">
                <input type="hidden" name="action" value="login"/>
                <input name="username" type="text" placeholder="请输入账号" autocomplete="off">
                <input name="password" type="text" placeholder="请输入密码" autocomplete="off">
                <div class="verify">
                    <input name="check" id="check" type="text" placeholder="请输入验证码" autocomplete="off">
                    <span><img src="<%=request.getContextPath()%>/checkCodeServlet" onclick="changeCheckCode(this)"></span>
                </div>
            </div>

            <br>
            <table align="center">
                <tr align="center">
                    <td>
                        <div class="submit_btn">
                            <button type="button" id="submit" name="submit">登录</button>
                        </div><br>
                    </td>
                </tr>
                <tr align="center">
                    <td>
                        <div class="auto_login">
                            <input type="checkbox" name="remember" id="remember" onclick="saveUserInfo()">
                            <span>自动登录</span>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <div class="reg">没有账户？<a href="register.jsp">立即注册</a></div>
    </div>

</section>
<!--引入尾部-->
<div id="footer"></div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<%--<!--导入布局js，共享header和footer-->--%>
<%--<script type="text/javascript" src="js/include.js"></script>--%>
<%@include file="footer.jsp"%>
</body>

</html>
