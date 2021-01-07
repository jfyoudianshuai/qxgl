<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="jf" uri="http://java.jf.com/jsp/jstl/jf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        td ,th {
            width: 150px;
            height: 24px;
            text-align: center;
            line-height: 24px;
        }
        .news a {
            display: inline-block;
            width: 100px;
            height: 30px;
            background-color: #08f;
            border: 2px solid black;
            border-radius: 10px;
            margin: 10px auto;
            text-align: center;
            line-height: 30px;
            text-decoration: none;
            color: #fff;
        }
        .titlt {
            font-size: 30px;
            font-weight:800;
            margin: 10px auto;
            color: red;
            text-align: center;
        }
        .news {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="titlt">用户列表</div>
    <div class="news">
    <jf:a href="newuser.jsp" id="new"  label="新建用户"  authorty="用户-新建"></jf:a>

    <a href="newusers.jsp" id="new">导入用户</a>
    </div>
    <table align="center" border="1">
        <tr><th>用户编号</th><th>用户名</th><th>真实姓名</th><th>性别</th><th>年龄</th><th>操作</th></tr>
        <c:forEach var="user" items="${requestScope.page.userList}">
            <tr><td>${user.uid}</td><td>${user.uname}</td><td>${user.truename}</td><td>${user.sex}</td><td>${user.age}</td><td>

                <a href="UserController.do?method=edit&uid=${user.uid}">编辑</a>|<jf:a href="JavaScript:shan(${user.uid},'${user.uname}');" label="删除 |" authorty="用户-删除"></jf:a><a href="RoleController.do?method=setRole&truename=${user.truename}&uid=${user.uid}">设置角色</a></td></tr>
        </c:forEach>
        <tr><td colspan="6" align="left">第${requestScope.page.page}页/共${requestScope.page.max}页
                <span style="float: right"><a href="UserController.do?method=list&page=1">首页</a> |
            <c:if test="${requestScope.page.page>1}">
                <a href="UserController.do?method=list&page=${requestScope.page.page-1}">上一页</a>|
            </c:if>
            <c:if test="${requestScope.page.page<requestScope.page.max}">
                <a href="UserController.do?method=list&page=${requestScope.page.page+1}">下一页</a>|
            </c:if>
                <a href="UserController.do?method=list&page=${requestScope.page.max}">末页</a></span>
            </td></tr>
    </table>
</body>
    <script>
        function shan(id,name) {
            var choose=confirm("确定要删除"+name+"吗");
            if(choose==true){
                location.href="UserController.do?method=delete&uid="+id;
            }
        }
    </script>
</html>
