<%@ page import="com.duyi.qxgl.domain.Fn" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %><%--
  Created by IntelliJ IDEA.
  User: 1579262144
  Date: 2020/11/17
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }
        a {
            text-decoration: none;
            color: #000;
        }
        .top {
            position: absolute;
            height: 98px;
            width: 100%;
            /*background-color: pink;*/
            border-bottom: 2px solid #ccc;
        }
        h2 {
            float: left;
            margin: 20px 0 0 40px;
            font-size : 30px;
            font-family: 'DejaVu Serif', Georgia, "Times New Roman", Times, serif;
        }

        .msg {
            float: right;
            height: 30px;
            margin: 63px 100px 20px 0;
        }
        .left {
            position: absolute;

            width: 200px;
            top: 100px;
            /*background-color: skyblue;*/
            left:0;
            bottom: 0;
            border-right:2px solid #ccc;
        }
        .menuBox{
            margin:20px;
        }
        .menuBox dt{
            margin-top:2px;
        }
        dd dt {
            display: inline-block;
            padding: 5px;
            font-size: 14px;
            margin-left: 25px;
            /*background-color: skyblue;*/
        }
        dd dt:hover {
            background-color: skyblue;
        }
        .right {
            position: absolute;
            /*background-color: red;*/
            top: 100px;
            left: 202px;
            right: 0;
            bottom: 0;
        }
        .exit:hover {
            color: #ff4400;
        }
    </style>
    <script>
        window.onload=function () {
            <%
            List<Fn> menus=(List<Fn>) session.getAttribute("menus");
            String json=JSON.toJSONString(menus);
            %>
            var json='<%=json%>';
            var menus=JSON.parse(json);
            var postion=document.getElementById("menubox");
            showFn(menus,postion);
        }
        function  showFn(fns,postion) {
            for(var i=0;i<fns.length;i++){
                var fn=fns[i];
                var dt=document.createElement("dt");
                if(fn.fhref){
                    dt.innerHTML='<a href="'+fn.fhref+'"  target="content">'+fn.fname+'</a>';
                }else {
                    dt.innerHTML=fn.fname;
                }
                postion.appendChild(dt);
                var list=fn.list;
                if(list!=null && list.length>0){
                    var dd=document.createElement("dd");
                    var dl=document.createElement("dl");
                    showFn(list,dl);
                    dd.appendChild(dl);
                    postion.appendChild(dd);
                }

            }
        }
    </script>
</head>
<body>
<div class="top">
    <h2>王某人集团</h2>
    <div class="msg">欢迎 【${sessionScope.loginuser.truename}】 [<a class="exit" href="UserController.do?method=exit">退出</a>]</div>
</div>
<div class="left">
    <dl class="menuBox" id="menubox">
        <!--
        <dt>权限管理</dt>
        <dd>
            <dl>
                <dt><a href="UserController.do?method=list&page=1" target="content">用户管理</a></dt>
                <dt><a href="role.jsp" target="content">角色管理</a></dt>
                <dt><a href="fn.jsp" target="content">功能管理</a></dt>
            </dl>
        </dd>
            <dt>基本信息管理</dt>
                <dd>
                    <dl>
                        <dt>商品管理</dt>
                        <dt>供应商管理</dt>
                        <dt>库房管理</dt>
                    </dl>
                </dd>
                -->
    </dl>

</div>

<div class="right">
    <iframe name="content" frameborder="0" width="100%" height="100%">

    </iframe>
</div>
</body>
</html>
