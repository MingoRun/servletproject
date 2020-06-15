<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-06-05
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <div style="width:300px;height:500px;margin:0 auto;">
      <h2>用户登录</h2>
    <form action="login" method="post">
        用户名:&nbsp;&nbsp;<input type="text" name="username" value="" placeholder="请输入用户名" /><br/><br/>
        密&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input type="password" name="password" value="" placeholder="请输入密码" /><br/><br/>
        <input type="submit" value="登录" /><br/>
        <span style="color:red;">${msg}</span>
    </form>
  </div>
  </body>
</html>
