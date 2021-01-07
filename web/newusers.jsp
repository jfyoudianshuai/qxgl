<%--
  Created by IntelliJ IDEA.
  User: 1579262144
  Date: 2020/11/20
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .box {
            position: relative;
            width: 300px;
            height: 300px;
            /*background-color: pink;*/
            margin: 20px auto;
        }
        input {
            position: absolute;
            display: block;
            height: 30px;
            top:30px;
            left:30px;
        }
        span {
            position: absolute;
            font-size: 13px;
            color: red;
            top: 60px ;
            left:30px;
            visibility: hidden;
        }
        button {
            /*margin-top: 30px;*/
            position: absolute;
            top: 110px;
            left: 30px;
            width: 50px;
            height: 20px;
            border-radius: 5px;
            line-height: 18px;
        }
        a{
            position: absolute;
            top:80px;
            left: 30px;
        }

    </style>
</head>
<body>
    <div class="box">
        <form action="UserController.do?method=saves" method="post" enctype="multipart/form-data">
            <input  id="file" type="file" name="filename" oninput="excel()" > <span id="prompt">选择文件有误，请选择excel表格</span>
            <a href="UserController.do?method=down">模板下载</a>
            <button>提交</button>
        </form>
    </div>
</body>
<script>
    function excel(){
        var file=document.getElementById("file");
//        C:\fakepath\你小子行啊.bmp
        var names=file.value;
        var prompt = document.getElementById("prompt");
        var name = names.substring(names.length - 5);
        if ('.xlsx' != name && ''!=name) {
            prompt.style.visibility = 'visible';
        } else {
            prompt.style.visibility = 'hidden';
        }

    }
</script>
</html>
