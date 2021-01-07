<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        td {
            text-align: center;
        }
        .btn {
            width: 50px;
            height: 25px;
            margin-bottom: 10px;
        }

    </style>
    <script>
        window.onload=function () {
            var allbtn = document.getElementById("allbtn");
            allbtn.onclick = function () {
                var checks = document.getElementsByTagName("input");
                for (var i = 0; i < checks.length; i++) {
                    if(checks[i].type=='checkbox') {
                        checks[i].checked = allbtn.checked;
                    }
                }
            }
        }
    </script>
</head>
<body>
<form action="RoleController.do?method=addRoleForUser" align="center" method="post">
        <h3 align="center">正在为【${requestScope.truename}】设置角色</h3>
        <input type="submit" class="btn" value="保存">
    <input type="hidden" name="uid" value="${requestScope.uid}">
        <table align="center" border="1" width="50%">
        <tr><th><input type="checkbox" id="allbtn"></th><th>角色编号</th><th>角色名称</th></tr>
        <c:forEach items="${requestScope.list}" var="role">
            <tr>
                <c:set var="f" value="false"></c:set>
                <c:forEach items="${requestScope.ulist}" var="urole">
                    <c:if test="${role.rid==urole.rid}">
                        <c:set var="f" value="true"></c:set>
                        <td><input type="checkbox" name="role" value="${role.rid}" checked="checked" ></td>
                    </c:if>
                </c:forEach>
                <c:if test="${f==false}">
                    <td><input type="checkbox" name="role" value="${role.rid}"></td>
                </c:if>
                <td>${role.rid}</td>
                <td>${role.rname}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
