<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/10 0010
  Time: 下午 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body background="/upfiles/003.jpg" style="color: #FCF6DA">
<div style="width: 700px;margin: 0px auto">
    <h2 style="text-align: center">${news.title}</h2>
    <span style="display: block;text-align: center">${news.user.name}在${news.createTime}发布于${news.topic.name} </span><br>
    <span style="display: block; text-align: center"><img src="/upfiles/${news.pic}" width="300px" height="200px"></span> <br><br>
    　　${news.content}
</div>
</body>
</html>
