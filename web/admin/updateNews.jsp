<%--
  Created by IntelliJ IDEA.
  User: skf
  Date: 2019/9/6
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="UpdateNewsServlet" method="post">
    <input type="hidden" name="newsId" value="${news.id}">
    新闻标题：<input type="text" name="title" value="${news.title}"><br>
    新闻内容：<br>
    <textarea name="content" cols="30" rows="4" >${news.content}</textarea><br>
    <input type="submit" value="确认修改">
</form>

</body>
</html>
