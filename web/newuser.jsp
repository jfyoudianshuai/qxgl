<%--
  Created by IntelliJ IDEA.
  User: 1579262144
  Date: 2020/11/18
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        em {
            display: inline-block;
            height: 30px;
            line-height: 30px;
            font-style: normal;
            color: red;
            visibility: hidden;
        }
    </style>
</head>
<body>
    <form  action="UserController.do?method=add" method="post" onsubmit="return check()">
        <span>用户名：</span><input type="text" name="uname" required="required"><br>
        <span>密码：</span><input id="upass" type="password" name="upass" required="required" oninput="appear()" ><em>*</em><br>
        <span>确认密码：</span><input id="repass" type="password" required="required" oninput="appear()"> <em>*</em><br>
        <span>真实姓名：</span><input type="text" name="truename" required="required"><br>
        <span>性别：</span>
        <select name="sex" style="font-weight: 600;">
            <option >男</option>
            <option >女</option>
        </select><br>
        <span>年龄：</span> <input type="text" name="age" required="required"><br>
        <button>保存</button>
    </form>
</body>
<script>
    function appear(){
        var tips=document.getElementsByTagName("em");
        var pass1 = document.getElementById("upass");
        var pass2 = document.getElementById("repass");
       if(pass1.value!=''&&pass2.value=='' || pass1.value=='' && pass2.value!='' || pass1.value!=pass2.value){
           for(var i=0;i<tips.length;i++){
               tips[i].style.visibility='visible';
               tips[i].style.color='red';
           }
       }else if(pass1.value=='' && pass2.value==''){
           for(var i=0;i<tips.length;i++){
               tips[i].style.visibility='hidden';
           }
       }else{
           for(var i=0;i<tips.length;i++){
               tips[i].style.color='green';
           }
       }

    }
    function judge(pass) {
        if(pass==''){}
    }
    function check() {
        var pass1 = document.getElementById("upass");
        var pass2 = document.getElementById("repass");
        if (pass1.value !== pass2.value) {
            alert("两次密码不一致，重新填写")
        }
    }
</script>
</html>
