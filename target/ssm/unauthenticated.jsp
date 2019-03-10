<%--
  Created by IntelliJ IDEA.
  User: lyCui
  Date: 2019/3/10
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>您还未认证</title>
</head>
<body>
<p>请先登录，完成用户认证！</p>
<%
   String ex = ((Exception)request.getAttribute("ex")).getMessage();
%>
<strong><%=ex%></strong>
</body>
</html>
