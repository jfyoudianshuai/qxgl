<%--
  Created by IntelliJ IDEA.
  User: 1579262144
  Date: 2020/11/18
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
    <style>
        form {
            text-align: center;
            margin-top: 30px;
        }
        span {
            display: inline-block;
            width: 100px;
            height: 30px;
            font-size: 14px;
            font-weight: 600;
            line-height: 30px;
            text-align: right;
        }
        input ,select {
            outline: none;
            width: 300px;
            height: 30px;
            border-radius: 10px;
            padding: 3px;
            margin: 10px;
        }
        button {
            width: 70px;
            height: 30px;
            font-size: 16px;
            margin-top: 30px;
            line-height: 28px;
        }
    </style>
</head>
<body>
<form  action="UserController.do?method=update" method="post">
    <input type="hidden" name="uid" value="${requestScope.user.uid}"><br>
    <span>用户名：</span><input type="text" name="uname" value='${requestScope.user.uname}' required="required"><br>
    <span>真实姓名：</span><input type="text" name="truename" value='${requestScope.user.truename}' required="required"><br>
    <span>性别：</span>
    <c:if test="${requestScope.user.sex=='男'}">
    <select name="sex" style="font-weight: 600;">
        <option select='select'>男</option>
        <option >女</option>
    </select><br>
    </c:if>
    <c:if test="${requestScope.user.sex=='女'}">
    <select name="sex" style="font-weight: 600;">
        <option >男</option>
        <option select='select'>女</option>
    </select><br>
    </c:if>
    <span>年龄：</span> <input type="text" name="age" value="${requestScope.user.age}" required="required"><br>
    <button>保存</button>
</body>
</html>


