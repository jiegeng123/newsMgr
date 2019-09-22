<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/2 0002
  Time: 下午 5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>新闻中国</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css"/>
    <style>
        body {
            background: url("/upfiles/003.jpg") repeat-y;
            background-size: 100%;
            color: wheat;
        }

        input {
            color: wheat;
        }

        a {
            color: white;
        }

        a:hover {
            font-weight: bolder;
            color: white;
            font-size: 20px;
        }

        #suggest {
            width: 250px;
            height: 150px;
            border: 1px solid bisque;
            margin-left: 370px;
            display: none;
        }

        .ss {
            cursor: pointer;
            background: #548779;
        }
    </style>
</head>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<body>
<div>新闻管理系统</div>
<div id="header">
    <div id="top_login">
        <a href="UserExitServlet">退出登录</a>
        <c:set var="isLogin" value="${empty sessionScope.user}"/>
        <c:if test="${isLogin}">
            <form action="UserLoginServlet" method="post">
                <div id="userlogin">
                    还没有账号？点此<a href="userReg.jsp">注册</a><br>
                    <label> 登录名 </label>
                    <input type="text" name="username" id="uname" class="login_input"/>
                    <label> 密&#160;&#160;码 </label>
                    <input type="password" name="password" id="upwd" class="login_input"/>
                    <input type="submit" class="login_sub" id="loginbtn" value="登录"/> <br><br>
                </div>
            </form>
        </c:if>
        <c:if test="${!isLogin}">
            <div>欢迎${user.name},登录成功！</div>
        </c:if>

        <div onclick="nomsg()" style="text-align: center">
            输入关键字：<input type="text" name="keyword" id="keyword" size="30"><input type="button" id="searchbtn"
                                                                                  value="搜索">
            <div id="suggest"></div>
        </div>
    </div>
    <label id="error"> </label>
    <img src="Images/friend_logo.gif" alt="Google" id="friend_logo"/></div>
</div>
<div id="container">
    <div class="sidebar">
        <h1><img src="Images/title_1.gif" alt="国内新闻"/></h1>
        <div class="side_list">
            <ul id="china">
                <%--<c:forEach items="${list}" var="news">
                    <li><a href='DetailNewsServlet?newsId=${news.id}'><b>${news.title}
                    </b></a></li>
                </c:forEach>--%>
            </ul>
        </div>
        <h1><img src="Images/title_2.gif" alt="国际新闻"/></h1>
        <div class="side_list">
            <ul id="international">
                <%--<c:forEach items="${list1}" var="news">
                    <li><a href='DetailNewsServlet?newsId=${news.id}'><b>${news.title}
                    </b></a></li>
                </c:forEach>--%>
            </ul>
        </div>
        <h1><img src="Images/title_3.gif" alt="娱乐新闻"/></h1>
        <div class="side_list">
            <ul id="entertainment">
                <%--<c:forEach items="${list2}" var="news">
                    <li><a href='DetailNewsServlet?newsId=${news.id}'><b>${news.title}
                    </b></a></li>
                </c:forEach>--%>
            </ul>
        </div>
    </div>
    <div class="main">
        <div class="class_type"><img src="Images/class_type.gif" alt="新闻中心"/></div>
        <div class="content">
            <ul class="class_date">
                <li id='class_month'></li>
            </ul>
            <ul class="classlist" id="newsL">

            </ul>
            <p align="right"> 当前页数:[<span id="indexP"></span>/<span id="totalP"></span>]&nbsp;
                <a href="javascript:void (0)" class="indexPage" id="first">首页</a>
                <a href="javascript:void (0)" class="indexPage" id="prev">上一页</a>
                <a href="javascript:void (0)" class="indexPage" id="next">下一页</a>
                <a href="javascript:void (0)" class="indexPage" id="last">末页</a></p>
        </div>

        <div class="picnews">
            <ul id="pictures">
                <%--<c:forEach items="${list4}" var="news">
                    <li><a href="DetailNewsServlet?newsId=${news.id}"><img src="/upfiles/${news.pic}" width="249"></a>
                        <a href="detailNews.jsp?newsId=${news.id}">${news.title}</a></li>
                </c:forEach>--%>
            </ul>
        </div>
    </div>
</div>
<div id="friend">
    <h2 class="friend_t">当前在线用户</h2>
    <div class="friend_list">
        <ul>
            <c:forEach items="${onlineUsers}" var="user">
                <li>${user.name}</li>
            </c:forEach>
        </ul>
    </div>
</div>
<div id="footer">
    <p class=""> 24小时客户服务热线：010-88888888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a> &#160;&#160;&#160;&#160;
        新闻热线：010-888888888 <br/></p>
    <p class="copyright"> Copyright &copy; 1999-2010 News China gov, All Right Reserver <br/>
        新闻中心 版权所有 </p>
</div>
</body>
<script>
    var currPage;
    var totalPage;
    $(function () {
        /**
         * 搜索关键字
         */
        $("#keyword").keyup(function () {
            $.ajax({
                url: "NewsTitleServlet",
                type: "POST",
                data: {
                    keyword: $("#keyword").val()
                },
                success: callback
            });
        });

        function callback(data) {
            var daceng = $("#suggest");
            daceng.html("");
            var titles = data.split(",");
            var xiaoceng = "";
            if (titles != null) {
                for (var i = 0; i < titles.length; i++) {
                    xiaoceng += "<div onmouseover=shangse(this) onmouseout=quse(this) onclick=quzhi(this)>" + titles[i] + "</div>";
                }
                daceng.html(xiaoceng).css("display", "block")
            } else {
                daceng.html("")
                daceng.css("display", "none");
            }
        }

        /**
         * 分页显示中间新闻
         * 显示页面其他板块新闻
         */
        var getNewsList = function (curr) {
            $.ajax({
                url: "CenterNewsServlet",
                type: "POST",
                dataType: "JSON",
                data: {
                    pageSize: 16,
                    pageIndex: curr || 1,
                    title: $("#keyword").val()
                },
                success: function (data) {
                    console.log(data);
                    //中间新闻列表
                    $("#newsL").find("li").remove();
                    $.each(data.newsList, function (index, news) {

                        var newsLi = "<li><a href='DetailNewsServlet?newsId=" + news.id + "'>" + news.title + "</a><span>" + news.createTime + "</span></li><li class='space'></li>";
                        $("#newsL").append(newsLi);
                    });
                    currPage = data.pageIndex;
                    totalPage = data.totalPage;
                    $("#indexP").html(currPage);
                    $("#totalP").html(totalPage);

                    //国内新闻列表
                    $.each(data.list,function (index,news) {
                        var newsLi="<li><a href='DetailNewsServlet?newsId="+news.id+"'><b>"+news.title+"</b></a></li>";
                        $("#china").append(newsLi);
                    });

                    //国际新闻列表
                    $.each(data.list1,function (index,news) {
                        var newsLi="<li><a href='DetailNewsServlet?newsId="+news.id+"'><b>"+news.title+"</b></a></li>";
                        $("#international").append(newsLi);
                    });

                    //娱乐新闻列表
                    $.each(data.list2,function (index,news) {
                        var newsLi="<li><a href='DetailNewsServlet?newsId="+news.id+"'><b>"+news.title+"</b></a></li>";
                        $("#entertainment").append(newsLi);
                    });

                    //图片新闻列表
                    $.each(data.list4,function (index,news) {
                        var newsLi="<li><a href='DetailNewsServlet?newsId="+news.id+"'> <img src='/upfiles/"+news.pic+"' width='249px' height='160px'> </a>" +
                            "<a href='DetailNewsServlet?newsId="+news.id+"'><b>"+news.title+"</b></a>" +
                            "</li>";
                        $("#pictures").append(newsLi);
                    });
                }
            });
        }
        $(".indexPage").click(function () {
            var flag = $(this).attr("id");
            if (flag == "first") {
                getNewsList(1);
            } else if (flag == "prev") {
                currPage--;
                getNewsList(currPage);
            } else if (flag == "next") {
                currPage++;
                getNewsList(currPage);
            } else {
                getNewsList(totalPage);
            }
        })
        getNewsList();
        $("#searchbtn").click(function () {
            getNewsList();
        })
    });

    function shangse(obj) {
        obj.className = "ss";
    }

    function quse(obj) {
        obj.className = "";
    }

    function quzhi(obj) {
        document.getElementById("keyword").value = obj.innerText;
        document.getElementById("suggest").style.display = "none";
    }

    function nomsg(obj) {
        document.getElementById("suggest").style.display = "none";
    }
</script>
</html>
