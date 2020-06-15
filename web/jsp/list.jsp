<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-06-05
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息列表页面</title>
    <style type="text/css">
        html {
            font-family: sans-serif;
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%;
        }

        body {
            margin: 10px;
        }
        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        td,th {
            padding: 0;
        }

        .pure-table {
            border-collapse: collapse;
            border-spacing: 0;
            empty-cells: show;
            border: 1px solid #cbcbcb;
        }

        .pure-table caption {
            color: #000;
            font: italic 85%/1 arial,sans-serif;
            padding: 1em 0;
            text-align: center;
        }

        .pure-table td,.pure-table th {
            border-left: 1px solid #cbcbcb;
            border-width: 0 0 0 1px;
            font-size: inherit;
            margin: 0;
            overflow: visible;
            padding: .5em 1em;
        }

        .pure-table thead {
            background-color: #e0e0e0;
            color: #000;
            text-align: left;
            vertical-align: bottom;
        }

        .pure-table td {
            background-color: transparent;
        }

        .pure-table-bordered td {
            border-bottom: 1px solid #cbcbcb;
        }

        .pure-table-bordered tbody>tr:last-child>td {
            border-bottom-width: 0;
        }

        <!-- 分页样式 -->
        ul.pagination {
            display: inline-block;
            padding: 0;
            margin: 0;
        }

        ul.pagination li {display: inline;}

        ul.pagination li a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
            border: 1px solid #ddd;
        }

        ul.pagination li a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }

        ul.pagination li a:hover:not(.active) {background-color: #ddd;}
        <!-- 分页样式 -->
    </style>
    <script>
        var msg = '${msg}';
        if (msg!=null && msg!='' && msg!=undefined) {
            alert(msg);
            <%session.removeAttribute("msg");session.invalidate();%>
        }
    </script>
</head>
<body>
    <div style="margin:0 auto;width:550px;">
        <h3>学生信息列表</h3>

        <div style="margin-bottom:20px;">

            <form action="list" method="post">
                <table>
                    <tr style="height:30px;">
                        <td>编&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                        <td><input type="text" name="id" value="${stu.id}"/></td>
                        <td>&nbsp;&nbsp;</td>
                        <td>学生姓名：</td>
                        <td><input type="text" name="name" value="${stu.name}"/></td>
                    </tr>
                    <tr style="height:30px;">
                        <td>学生出生年月：</td>
                        <td><input type="text" name="age" value="${stu.age}" /></td>
                        <td>&nbsp;&nbsp;</td>
                        <td>学生性别：</td>
                        <td><input type="text" name="sex" value="${stu.sex}" />&nbsp;&nbsp;<br/></td>
                    </tr>
                    <tr style="height:30px;">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><input type="submit" value="查询" /></td>
                        <td><button type="button" ><a href="${pageContext.request.contextPath}/toAdd">添加</a></button></td>
                    </tr>
                </table>
            </form>

        </div>

        <div>
            <table class="pure-table pure-table-bordered" style="margin:0 auto">
                <thead>
                <tr>
                    <th><b>编号</b></th>
                    <th><b>学生姓名</b></th>
                    <th><b>学生出生年月</b></th>
                    <th><b>学生性别</b></th>
                    <th><b>操作</b></th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty page.data}">
                        <tr>
                            <td colspan="5" style="text-align: center;">
                                暂无学生数据
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${page.data}" var="s">
                            <tr>
                                <td>${s.id }</td>
                                <td>${s.name }</td>
                                <td>${s.age}</td>
                                <td>${s.sex}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/toEdit?id=${s.id}">修改</a>
                                    <a href="${pageContext.request.contextPath}/del?id=${s.id}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
        <div style="float: right;">
            <ul class="pagination" >
                <c:choose>
                    <c:when test="${page.currentPage ne 1}">
                        <li><a href="${pageContext.request.contextPath}/list?currentPage=1">首页</a></li>
                        <li><a href="${pageContext.request.contextPath}/list?currentPage=${page.currentPage-1}">«</a></li>
                    </c:when>
                </c:choose>
                <c:forEach var="i" begin="${page.currentPage}" end="${page.pageNum}" step="1">
                    <li><a <c:if test="${page.currentPage eq i}">class="active" </c:if> <c:if test="${page.currentPage ne i}">href="${pageContext.request.contextPath}/list?currentPage=${i}" </c:if> >${i}</a></li>
                </c:forEach>
                <c:choose>
                    <c:when test="${page.currentPage ne page.pageNum}">
                        <li><a href="${pageContext.request.contextPath}/list?currentPage=${page.currentPage+1}">»</a></li>
                        <li><a href="${pageContext.request.contextPath}/list?currentPage=${page.pageNum}">尾页</a></li>
                    </c:when>
                </c:choose>
                <li><a>共${page.pageNum}页</a></li>
            </ul>
        </div>
    </div>
</body>
</html>