<%--
  Created by IntelliJ IDEA.
  User: lyCui
  Date: 2019/3/10
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>测试原生ajax</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxUtils.js"></script>
</head>
<body>
<p></p>
<script type="text/javascript">
 $(function () {
     let ajax = new aJax();
     const myPromise = ajax.send({
         type : 'POST',
         url : '/user/get/1'
     });
    /* async function callback() {
         let result = await myPromise;
       //  let resultStr = JSON.stringify(result);
         setTimeout(() =>  $("p").text(result),5000);
     }
     callback();*/
     myPromise.then(
         function (data) {
             $("p").text(data);
         }
     ).catch(data => $("p").text(data)).finally(() =>console.log("执行完毕"));
 });
</script>
</body>
</html>
