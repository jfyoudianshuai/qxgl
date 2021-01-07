
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        th,td {
            width: 150px;
            height: 30px;
            text-align: center;
            line-height: 30px;
        }
        h2 {
            text-align: center;
        }
    </style>
    <script>
        window.onload=function(){
                tofind(1);
            }
            function doBack(result){
                var p=JSON.parse(result).jsonObject;
                var tbody = document.getElementById("tbody");
                tbody.innerHTML='';
                var list=p.userList;
                for(var i=0;i<list.length;i++) {
                    var td1 = document.createElement("td");
                    var td2 = document.createElement("td");
                    var td3 = document.createElement("td");
                    var role = list[i];
                    var tr = document.createElement("tr");
                    td1.innerHTML = role.rid;
                    td2.innerHTML = role.rname;
                    td3.innerHTML = '<a href="RoleController.do?method=edit&rid='+role.rid+'">编辑</a> | <a href="javascript:deleteRole('+role.rid+',\''+role.rname+'\')">删除</a> | <a href="setFn.jsp?rname='+role.rname+'&rid='+role.rid+'">设置功能</a>';
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tbody.appendChild(tr);
                }
                var left=document.getElementById("left");
                var right=document.getElementById("right");
                left.innerHTML="第"+p.page+"页/共"+p.max+"页";
                right.innerHTML='<a href="javascript:tofind(1)">首页</a> |';
                if(p.page>1){
                    right.innerHTML+='<a href="javascript:tofind('+(p.page-1)+')">上一页</a> | ';
                }
                if(p.page<p.max){
                    right.innerHTML+='<a href="javascript:tofind('+(p.page+1)+')">下一页</a> | ';
                }
                right.innerHTML+='<a href="javascript:tofind('+(p.max)+')">末页</a>';
            }
        function tofind(page) {
            var rolexml = new XMLHttpRequest();
            rolexml.open("get", "RoleController.do?method=list&page=" + page);
            rolexml.send();
            rolexml.onreadystatechange = function () {
                if (rolexml.readyState == 4 && rolexml.status == 200) {
                    doBack(rolexml.responseText);
                }
            }
        }
        function deleteRole(rid,rname){
            var choose=confirm("确定要删除"+rname+"吗");
            if(choose==true) {
                var rolexml = new XMLHttpRequest();
                rolexml.open("get", "RoleController.do?method=delete&rid=" + rid);
                rolexml.send();
                rolexml.onreadystatechange = function () {
                    if (rolexml.readyState == 4 && rolexml.status == 200) {
                        tofind(1);
                    }
                }
            }
        }
    </script>
</head>
<body>
    <table align="center" border="1" width="60%">
        <h2>角色列表</h2>
        <thead>
            <tr>
                <th>角色编号</th><th>角色名称</th><th>操作</th>
            </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
    <table align="center" width="60%">
        <tr><td style="text-align: left" align="left" id="left"></td><td align="right" id="right" style="text-align: right"></td></tr>
    </table>
</body>
</html>
