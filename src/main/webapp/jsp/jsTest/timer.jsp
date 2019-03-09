<%--
  Created by IntelliJ IDEA.
  User: lyCui
  Date: 2019/3/9
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>搞懂js中的定时器</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/timer.js"></script>
</head>
<body>
<div>注册成功！<span>8</span>S后自动跳转...</div>
<div id="myDiv" style="height: 100px;width: 100px;background-color: green;position:absolute;left: 0;top: 10px;">a</div>
<div id="myDiv1" style="height: 100px;width: 100px;background-color: yellow;position:absolute;left: 0;top: 110px;">b</div>
<div style="height: 40px;margin-top: 220px;">
    <input id="myInput" type="text" name="username" placeholder="请输入英文字母"/>
</div>
<script>
    myDiv.onclick = function () {
        var timer = setInterval(function () {
            if(parseInt(myDiv.style.left) >= 400) {
                clearInterval(timer);
                return false;
            }
            myDiv.style.left = parseInt(myDiv.style.left)+5+'px';
        },16);
    }
    myDiv1.onclick = function () {
        var timer = setTimeout(function fn() {
            if(parseInt(myDiv1.style.left) >= 400) {
                return false;
            }
            setTimeout(fn,16);
            myDiv1.style.left = parseInt(myDiv1.style.left)+5+'px';
        },16)
    }
    myInput.onkeypress = function () {
        setTimeout(() => myInput.value = myInput.value.toUpperCase());
    }
</script>
</body>
</html>
