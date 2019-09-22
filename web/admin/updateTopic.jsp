<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/5 0005
  Time: 下午 4:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="UpdateTopicServlet" method="post">
    <input type="hidden" name="topicId" class="titleid" value="${topic.id}">
    版块名称：<input type="text" name="topicName" class="titlename" value="${topic.name}">
    <input type="submit" value="确认修改">
</form>
</body>

</html>
