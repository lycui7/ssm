<%--
  Created by IntelliJ IDEA.
  User: lyCui
  Date: 2019/3/9
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String context = request.getContextPath();
    String fullUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+context+"/";
%>
<html>
<head>
    <title>必须掌握的12个JS技能</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/learnJs.js"></script>
    <script type="text/javascript">
        const context = "<%=context%>";
        const fullUrl = "<%=fullUrl%>";
    </script>
</head>
<body>
<%--<%
    out.write(context);
    out.write(fullUrl);
%>--%>
<p>我即将变化！</p>
<span>等待结果中</span>
<strong>等待中。。。</strong>
</body>
</html>

