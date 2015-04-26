<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>三人行</title>

  <link rel="icon" href="/img/logo.ico">
  <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <link href="/css/register.css" rel="stylesheet">
</head>

<body>

<div class="container">

  <img src="/img/logo.ico" id="logo">

  <form class="form-register" method="post" action="/register">

    <div class=page-header">
      <h2 class="form-register-heading">三人行</h2>
      <p class="desc">三人行，必有<span class="you">你</span>师</p>
    </div>

    <input type="text" name="username" id="username" class="form-control" placeholder="用户名" required>
    <input type="email" id="email" name="email" class="form-control" placeholder="电子邮件">
    <input type="password" id="password" name="password" class="form-control" placeholder="设置您的密码" required>

    <input type="text" name="skill1" class="form-control" placeholder="超能力一" required>
    <input type="text" name="skill2" class="form-control" placeholder="超能力二" required>
    <input type="text" name="skill3" class="form-control" placeholder="超能力三" required>

    <%--<div class="form-group">--%>
      <%--<input type="file" name="avatar">--%>
      <%--<p class="help-block">在这里选择您的头像</p>--%>
    <%--</div>--%>

    <c:if test="${ not empty errorMsg }">
      <div class="alert alert-danger">${errorMsg}</div>
    </c:if>

    <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
    <a class="btn btn-lg btn-default btn-block" href="/login">登录</a>
  </form>

</div>

<script src="/lib/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
