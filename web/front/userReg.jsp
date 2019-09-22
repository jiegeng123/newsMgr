<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/11 0011
  Time: 下午 3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<body style="background: #7592D5">
<div style=" width: 600px; margin: 100px auto;text-align: center;">
    <h2>用户注册</h2>
    <form action="UserRegServlet" method="post">
        请输入用户名：<input type="text" name="username" id="username"><span id="msg"></span><br><br><br>
        　请输入密码：<input type="password" name="pwd"><br><br>
        <input type="submit" value="注册">
    </form>
</div>

<script>
    $(function () {
        $("#username").blur(function () {
            $.post(
                "CheckNameServlet",
                {
                    username: $("#username").val()
                },
                function (data) {
                    if (data == "true") {
                        $("#msg").html("用户名已经存在").css("color", "red");
                        $(":submit").attr("disabled", "disabled");
                    }
                    if (data == "false") {
                        $("#msg").html("可以注册").css("color", "green");
                        $(":submit").removeAttr("disabled");
                    }
                    if (data == "refuse") {
                        $("#msg").html("用户名不能为空").css("color", "pink");
                        $(":submit").attr("disabled", "disabled");
                    }
                }
            );
        });
    })
</script>
</body>
</html>
