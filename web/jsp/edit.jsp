<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-06-11
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息编辑页面</title>
    <style>
        tr {
            height: 40px;
        }
        .td1 {
            width:120px;
        }

        span {
            color: red;
        }
    </style>
    <script>

        function checkStudent() {
            var id = document.getElementById("id");
            var name = document.getElementById("name");
            var age = document.getElementById("age");
            var sex1 = document.getElementById("sex1");
            var sex2 = document.getElementById("sex2");

            if (id.value == '' || id.value == null || id.value == undefined) {
                document.getElementById("iderr").innerText='请输入学生编号';
                return false;
            }else {
                document.getElementById("iderr").innerText='';
            }

            if (name.value == '' || name.value == null || name.value == undefined) {
                document.getElementById("nameerr").innerText='请输入学生姓名';
                return false;
            }else {
                document.getElementById("nameerr").innerText='';
            }

            if (age.value == '' || age.value == null || age.value == undefined) {
                document.getElementById("ageerr").innerText='请选择学生出生年月';
                return false;
            }else {
                document.getElementById("ageerr").innerText='';
            }

            if ((sex1.value == '' || sex1.value == null || sex1.value == undefined)
                && (sex2.value == '' || sex2.value == null || sex2.value == undefined)) {
                document.getElementById("sexerr").innerText='请选择学生性别';
                return false;
            }else {
                document.getElementById("sexerr").innerText='';
            }

            return true;
        }

    </script>
</head>
<body>
<div style="width:500px;margin:0 auto;">
    <h3 align="center">编辑学生信息</h3>
    <form method="post" action="edit" onsubmit="return checkStudent();">
        <table>
            <tr>
                <td class="td1">编号: </td>
                <td><input type="text" id="id" name="id" value="${student.id}" placeholder="请输入学生编号" readonly/></td>
                <td><span id="iderr"></span></td>
            </tr>
            <tr>
                <td class="td1">学生姓名: </td>
                <td><input type="text" id="name" name="name" value="${student.name}" placeholder="请输入学生姓名" /> </td>
                <td><span id="nameerr"></span></td>
            </tr>
            <tr>
                <td class="td1">学生出生年月: </td>
                <td><input type="date" id="age" name="age" value="${student.age}" readonly /></td>
                <td><span id="ageerr"></span></td>
            </tr>
            <tr>
                <td class="td1">学生性别</td>
                <td>男<input type="radio" id="sex1" name="sex" value="男" <c:if test="${student.sex eq '男'}" >checked readonly</c:if> /> 女<input type="radio" id="sex2" name="sex" value="女" <c:if test="${student.sex eq '女'}" >checked readonly</c:if> /> </td>
                <td><span id="sexerr"></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="编辑" /></td>
                <td><button><a href="${pageContext.request.contextPath}/list"></a>返回</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
