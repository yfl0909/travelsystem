<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>

    <script>

        function changeCheckCode(img) {
            img.src = "<%=request.getContextPath()%>/checkCodeServlet?"+new Date().getTime();
        }

        function checkUserName() {
            var username = $("#username").val();
            var reg_username = /^\w{4,10}$/;
            var flag = reg_username.test(username);
            if(flag){
                $("#username").css("border", "");
            }else{
                $("#username").css("border", "1px solid red");
            }
            return flag;
        }

        function checkPassword(){
            var password = $("#password").val();
            var reg_password = /^\w{4,10}$/;
            var flag = reg_password.test(password);
            if(flag){
                $("#password").css("border", "");
            }else{
                $("#password").css("border", "1px solid red");
            }
            return flag;
        }

        function checkName(){
            var name = $("#name").val();
            var reg_name = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
            var flag = reg_name.test(name);
            if(flag){
                $("#name").css("border", "");
            }else{
                $("#name").css("border", "1px solid red");
            }
            return flag;
        }

        function checkTelephone(){
            var telephone = $("#telephone").val();
            var reg_telephone = /^1[34578]\d{9}$/;
            var flag = reg_telephone.test(telephone);
            if(flag){
                $("#telephone").css("border", "");
            }else{
                $("#telephone").css("border", "1px solid red");
            }
            return flag;
        }

        $(function () {
           $("#registerForm").submit(function () {
               //1、发送数据到服务器
               if(checkUserName() && checkPassword() && checkName() && checkTelephone()){
                   //校验通过 发送ajax请求 提交表单数据
                   $.post("registUserServlet", $(this).serialize(), function (data) {
                       //处理服务器响应的数据
                       if(data.flag){
                           //注册成功
                           //跳转页面
                           location.href = "register_ok.jsp";
                       }else{
                           //注册失败
                           $("#error_msg").html(data.errorMsg);
                       }
                   });
               }
               return false;
           });

           $("#username").blur(checkUserName);
           $("#password").blur(checkPassword);
           $("#name").blur(checkName);
           $("#telephone").blur(checkTelephone);
        });

    </script>
</head>
<body>
<!--引入头部-->
<div id="header"></div>
<%@include file="header.jsp"%>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_form_center">

            <br><br>
            <div id="error_msg" style="color: red; text-align: center"></div>
            <!--注册表单-->
            <form id="registerForm" action="register_ok.jsp" method="post">
                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="username">用户名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="username" name="username" placeholder="请输入账号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="password">密码</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="password" name="password" placeholder="请输入密码">
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td class="td_left">--%>
                            <%--<label for="email">Email</label>--%>
                        <%--</td>--%>
                        <%--<td class="td_right">--%>
                            <%--<input type="text" id="email" name="email" placeholder="请输入Email">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td class="td_left">
                            <label for="name">姓名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="name" placeholder="请输入真实姓名">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="telephone" placeholder="请输入您的手机号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" id="sex" name="sex" value="男" checked> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="birthday">出生日期</label>
                        </td>
                        <td class="td_right">
                            <input type="date" id="birthday" name="birthday" placeholder="年/月/日">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="check">验证码</label>
                        </td>
                        <td class="td_right check">
                            <input type="text" id="check" name="check" class="check">
                            <img id="checkCode" name="checkCode" height="32px" alt=""
                                 src="<%=request.getContextPath()%>/checkCodeServlet" onclick="changeCheckCode(this)">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
                            <input type="submit" class="submit" value="注册">
                            <span id="msg" style="color: red;"></span>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="rg_form_right">
            <p>
                已有账号？
                <a href="login.jsp">立即登录</a>
            </p>
        </div>
    </div>
</div>
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<%@include file="footer.jsp"%>
</body>
</html>
