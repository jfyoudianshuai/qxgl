
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="RoleController.do?method=update" method="post">
        <input type="hidden" name="rid" value="${requestScope.role.rid}">
        <table align="center">
        <tr><td>用户名：<input type="text" name="rname" value="${requestScope.role.rname}"></td></tr>
        <tr><td><button>保存</button></td></tr>
        </table>
    </form>
</body>
</html>
