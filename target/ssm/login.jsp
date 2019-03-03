<%--
  Created by IntelliJ IDEA.
  User: lyCui
  Date: 2019/3/3
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>登录页面</title>
</head>
<body>
<div>
    <div class="loginFunc">
        <div id="lbNormal" class="loginFuncMobile">员工登录</div>
    </div>
    <div class="loginForm">
        <form id="loginform" name="loginform" method="post" class="niceform" action="<%=request.getContextPath()%>/robot/login">
            <div id="idInputLine" class="loginFormIpt showPlaceholder"
                 style="margin-top: 5px;">
                <input id="idInput" type="text" name="username"
                       class="loginFormTdIpt" maxlength="50"/>
                <label for="idInput" class="placeholder" id="idPlaceholder">帐号：</label>
            </div>
            <div class="forgetPwdLine"></div>
            <div id="pwdInputLine" class="loginFormIpt showPlaceholder">
                <input id="pwdInput" class="loginFormTdIpt" type="password"
                       name="password" value=""/>
                <label for="pwdInput" class="placeholder" id="pwdPlaceholder">密码：</label>
            </div>
            <div class="loginFormIpt loginFormIptWiotTh"
                 style="margin-top:58px;">
                <div id="codeInputLine" class="loginFormIpt showPlaceholder"
                     style="margin-left:0px;margin-top:-40px;width:50px;">
                    <input id="loginform:codeInput" class="loginFormTdIpt" type="text"
                           name="checkcode" title="请输入验证码"/>
                    <img id="loginform:vCode" src="${pageContext.request.contextPath}/validatecode.jsp"
                         onclick="javascript:document.getElementById('loginform:vCode').src='${pageContext.request.contextPath}/validatecode.jsp?'+Math.random();"/>
                </div>
                <a onclick="document.forms[0].submit()" id="loginform:j_id19" name="loginform:j_id19">
						<span
                                id="loginform:loginBtn" class="btn btn-login"
                                style="margin-top:-36px;">登录</span>
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
