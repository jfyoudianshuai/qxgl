<%--
  Created by IntelliJ IDEA.
  User: 1579262144
  Date: 2020/11/29
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        ul,li{
            margin:0;
            padding: 0;
            list-style:none;
        }
        li {
            float: left;
            /*border: 1px solid #ccc;*/
        }

        li.role{
            float: right;
            width: 100px;
        }
        li.fhref {
            float: right;
            width: 200px;
        }
        ul{
            clear: both;
        }
        .menubox {
            width: 600px;
            border: 1px solid #ccc;
            padding-top: 10px;
            padding-bottom: 10px;
            margin: 10px auto;
        }
        dt {
            height: 21px;
            margin:-5px 0 5px 20px;
        }

    </style>
    <script>
        window.onload=function () {
            first();
        }
        function first(){
            var xml=new XMLHttpRequest();
            xml.open("get","FnController.do?method=list");
            xml.send();
            function writeWindow(list,dl){
                for(var i=0;i<list.length;i++){
                    var fn=list[i];//获取到list里面的每一个fn功能
                    var dt=document.createElement("dt");
                    var ul=document.createElement("ul");
                    var li1=document.createElement("li");

                    dt.setAttribute("fid",fn.fid);
                    li1.setAttribute("class",'name');

                    li1.innerHTML='<input type="checkbox" name="fid" value='+fn.fid+'>'+fn.fname;
                    ul.appendChild(li1);
                    dt.appendChild(ul);
                    dl.appendChild(dt);
                    var innerlist=fn.list;
                    if(innerlist!=null && innerlist.length!=0){
                        var dd=document.createElement("dd");
                        var dl2=document.createElement("dl");
                        writeWindow(innerlist,dl2);
                        dd.appendChild(dl2);
                        dl.appendChild(dd);
                    }
                }


            }
            xml.onreadystatechange=function(){
                if(xml.readyState==4 && xml.status==200){
                    var t=xml.responseText;
                    var list=JSON.parse(t).jsonObject;
                    var dl=document.getElementsByClassName("menubox")[0];
                    writeWindow(list,dl);
                    initRoleFn();
                }
            }
        }
        function initRoleFn(){

            var xml=new XMLHttpRequest();
            xml.open("get","FnController.do?method=findFidsRole&rid=${param.rid}");
            xml.send();
            xml.onreadystatechange=function () {
                if(xml.readyState==4 && xml.status==200){
                    doBack(xml.responseText);
                }
            }
            function doBack(result){
                var list=JSON.parse(result).jsonObject;
                for(var i=0;i<list.length;i++){
                    var fid=list[i];
                    var fids=document.getElementsByName("fid");
                    for(var j=0;j<fids.length;j++){
                        if(fid==fids[j].value){
                            fids[j].checked=true;
                        }
                    }
                }
            }
        }
    </script>
</head>
<body>
<form action="FnController.do?method=setFns" method="post" align="center">
    <h2>正在为【${param.rname}】设置功能</h2>
    <input type="hidden" name="rid" value="${param.rid}">
    <input type="submit" value="保存">
    <dl class="menubox">

    </dl>
</form>
</body>
</html>
