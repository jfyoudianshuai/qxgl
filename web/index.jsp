<%--
  Created by IntelliJ IDEA.
  User: 1579262144
  Date: 2020/11/4
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>权限管理系统登录页面</title>
  <style>
    * {
      padding: 0;
      margin: 0;
    }
    body {
      width: 100%;
      height: 100%;
      background:  linear-gradient(to right, #b8cbb8 0%, #b8cbb8 0%, #ddbaee 0%, #e8a8ee 33%, #b8d9f8 66%, #59bcf5 100%);
      /*height: 100%;*/
      /*background: url("imges/body.gif") no-repeat ;*/
      /*background-size: 100% 100%;*/
    }
    .login {
      margin: 100px  auto;
      width: 800px;
      height: 400px;
      background-color: rgba(0,0,0,0.3);
    }
    h1 {
      height: 60px;
      padding: 20px;
      text-align: center;
      font-size: 50px;
      color: #fff;
    }
    .msg {
      margin-left: 150px;
      margin-top: 30px;
      width: 500px;
      height: 45px;
      font-size: 20px;
      line-height: 40px;
      color: #fff;
      border: 0;
      border-bottom: 1px solid #fff;
      background-color: transparent;
    }
    form div {

      margin-left: 150px;
      margin-top: 20px;
      width: 500px;
      /*height: 40px;*/
      font-size: 20px;
      color: #fff;
      /*background-color: #fff;*/
    }
    form h5{
      font-family:'Microsoft YaHei', '黑体-简', '\5b8b\4f53';
      font-weight: 300;
      font-size: 13px;
      color: #fff;
    }
     input {
       outline: none;
    }
    .login .btn {
      margin-top: 10px;
      margin-left: 340px;
      width: 105px;
      height: 35px;
      border: 0;
      border-radius: 5px ;
      font-size: 18px;
      font-weight: 500;
      color: #fff;
      background-color: rgb(214, 91, 136);
    }
    .login .btn:hover {
      cursor: pointer;
      box-shadow: 3px 3px 5px #fff ;
    }
    p {
      width: 800px;
      height: 16px;
      text-align: center;
      font-size: 12px;
      color: red;
      margin: 20px 0;
    }
  </style>
</head>

<Body>


<div class="login">
  <h1>Login</h1>
  <form action="UserController.do?method=login" method="post">
    <input class="msg" type="text" autocomplete="off" name="uname" value="用户名"  onclick="if(value=='用户名')value='' " onblur="if(value=='') value='用户名'"><br>
    <input class="msg inputpass"  type="text" name="upass" value="密码" onclick="if(value=='密码')value=''; type='password' "  onblur="if(value=='') value='密码' ,type='text' "><br>
    <p>
      <c:if test="${param.status==9}">
      用户名或密码错误
    </c:if>
      </p>
    <input class="btn" type="submit" value="Lo gin">
  </form>
</div>
</Body>
</html>
