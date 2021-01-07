<%--
  Created by IntelliJ IDEA.
  User: 1579262144
  Date: 2020/11/21
  Time: 11:11
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

        li.name{

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
        dt:hover {
            cursor: pointer;
        }
        .menubox {
            width: 600px;
            border: 1px solid #ccc;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .title {
            font-weight: bold;

        }
        #root,#child {
            height:30px;
            margin: 0 30px;
        }
        dt {
            height: 21px;
            margin:-5px 0 5px 20px;
        }
        #active {
            background-color: skyblue;
        }
    </style>
    <script>
        window.onload=function(){
            first();
            creroot();
            crechil();
        }
        function crechil(){
            var chil=document.getElementById("child");
            chil.onclick=function () {
                var fname=document.getElementById("fname");
                if(fname){
                    alert("先完成未完成的工作");
                    return;
                }
                var active=document.getElementById("active");
                if(!active){
                    alert("请先选择父级功能");
                    return;
                }

                var next=active.nextElementSibling;
                if(next==null){
                    var dd=document.createElement("dd");
                    var dl=document.createElement("dl");
                    inputfn(dl);
                    dd.appendChild(dl);
                    active.parentNode.appendChild(dd);

                }else if(next.tagName=="DD"){
                    var dl=next.firstElementChild;
                    inputfn(dl);
                }else if(next.tagName=="DT"){
                    var dd=document.createElement("dd");
                    var dl=document.createElement("dl");
                    inputfn(dl);
                    dd.appendChild(dl);
                    active.parentNode.insertBefore(dd,next);
                }
                var can=document.getElementById("cancel");
                can.onclick = function () {
                    if(next==null || next.tagName=="DT") {
                        dd.remove();
                    }else {
                        var fname = document.getElementById("fname");
                        var cre=fname.parentNode.parentNode.parentNode;
                        next.firstElementChild.removeChild(cre);
                    }
                }
                var ok=document.getElementById("OK");
                ok.onclick=function () {
                    var pid=active.getAttribute("fid");
                    var fname=document.getElementById("fname");
                    var frag=document.getElementById("frag").value=="菜单"?1:2;
                    var fhref=document.getElementById("fhref");
                    savefn(fname.value,frag,fhref.value,pid);
                }

            }
        }
        function first(){
            var xml=new XMLHttpRequest();
            xml.open("get","FnController.do?method=list",true);
            xml.send();
            function writeWindow(list,dl){
                for(var i=0;i<list.length;i++){
                    var fn=list[i];//获取到list里面的每一个fn功能
                    var dt=document.createElement("dt");
                    var ul=document.createElement("ul");
                    var li1=document.createElement("li");
                    var li2=document.createElement("li");
                    var li3=document.createElement("li");
                    dt.setAttribute("fid",fn.fid);
                    li1.setAttribute("class",'name');
                    li2.setAttribute("class",'role');
                    li3.setAttribute("class",'fhref');
                    li1.innerHTML=fn.fname;
                    li2.innerHTML=fn.frag==1?'<span style="color:green">菜单</span>':'<span style="color:red">按钮</span>';
                    li3.innerHTML=fn.fhref==''?'无':fn.fhref;
                    ul.appendChild(li1);
                    ul.appendChild(li3);
                    ul.appendChild(li2);
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
                    window.setInterval(function () {
                        var dts=document.getElementsByTagName("dt");
                        for( var i=0;i<dts.length;i++){
                            var dt=dts[i];
                            dt.onclick=function () {
                                var active=document.getElementById("active");
                                if(active){
                                    active.id='';
                                }
                                this.id='active';
                            }
                        }
                    },200);
                    addtwoclick();
                }
            }
        }
        function creroot(){
            var root=document.getElementById("root");
            root.onclick=function (){
                var cre=document.getElementById("cre");
                if(cre==null) {
                    var menu=document.getElementsByClassName("menubox")[0];
                    inputfn(menu);
                    var can=document.getElementById("cancel");
                    can.onclick = function () {
                        var menu = document.getElementsByClassName("menubox")[0];
                        var cre = document.getElementById("cre");
                        menu.removeChild(cre);
                    }
                    var ok=document.getElementById("OK");
                    ok.onclick=function () {
                        var fname=document.getElementById("fname");
                        var frag=document.getElementById("frag").value=="菜单"?1:2;
                        var fhref=document.getElementById("fhref");
                        var pid=-1;
                        savefn(fname.value,frag,fhref.value,pid);
                    }
                }else {
                    alert("请先完成当前操作");
                }
            }
        }
        function savefn(fname,frag,fhref,pid){
            var fxml=new XMLHttpRequest();
            fxml.open("post","FnController.do?method=insert",true);
            function doBack(){
                alert("保存成功");
                var name=document.getElementById("fname").parentNode;
                var lrole=document.getElementById("frag").parentNode;
                var lhref=document.getElementById("fhref").parentNode;
                name.innerHTML=fname;
                lrole.innerHTML=frag==1?'<span style="color:green">菜单</span>':'<span style="color:red">按钮</span>';
                lhref.innerHTML=fhref==''?'无':fhref;
            }
            fxml.setRequestHeader("content-type","application/x-www-form-urlencoded");
            fxml.send('fname='+fname+'&frag='+frag+'&fhref='+fhref+'&pid='+pid);
            fxml.onreadystatechange=function () {
                if(fxml.readyState==4 && fxml.status==200){
                    doBack(fxml.responseText);
                }
            }

        }
        function inputfn(menu){
            menu.innerHTML+='<dt id="cre">\n' +
                '    <ul>\n' +
                '        <li class="name"><input id="fname" name="fname" style="width: 150px;" type="text"></li>\n' +
                '        <li class="fhref"><input id="fhref" name="fhref" type="text" style="width: 100px"><button id="OK">√</button><button id="cancel">×</button></li>\n' +
                '        <li class="role">\n' +
                '            <select name="frag" id="frag">\n' +
                '                <option>菜单</option>\n' +
                '                <option>按钮</option>\n' +
                '            </select>\n' +
                '        </li>\n' +
                '    </ul>\n' +
                '</dt>';
        }
        function addtwoclick(){
            var dts=document.getElementsByTagName("dt");
            for(var i=0;i<dts.length;i++){
                var dt=dts[i];
                dt.ondblclick=function () {
                    var next=this.nextElementSibling;
                    if(next!=null && next.tagName=="DD"){
                        if(next.style.display=='none'){
                            next.style.display='block';
                        }else {
                            next.style.display='none';
                        }
                    }
                }
            }
        }
    </script>
</head>
<body>
<button id="root">新建根目录</button><button id="child">新建子目录</button>
<dl class="menubox">
    <dt class="title">
        <ul>
            <li class="name">功能名称</li>
            <li class="fhref">链接</li>
            <li class="role">类型</li>
        </ul>
    </dt>
</dl>


</body>
</html>
