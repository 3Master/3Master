<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>搜索</title>

  <link rel="icon" href="/img/logo.jpg">
  <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <link href="/css/search.css" rel="stylesheet">
  <script src="/js/search.js"></script>
</head>

<body>

<nav class="navbar navbar-default navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">三人行</a>
    </div>

    <ul class="nav navbar-nav navbar-right">
      <li class="active"><a href="/search">搜索</a></li>
      <li class=""><a href="/message">消息</a></li>
    </ul>
  </div>
</nav>

<div class="container-fluid">
  <form>
    <div class="input-group">
      <input type="text" name="skill" class="form-control" placeholder="输入您想学习的任何技能" required autofocus>
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">搜素</button>
      </span>
    </div>
  </form>

  <div class="comment-list">
    <div class="comment-list-item">
      <c:forEach var="user" items="${users}" >
      <div class="avatar">
        <a><img src="${user.avatar}"></a>
      </div>
      <div class="body">
        <h3>
          ${user.username}
          <a class="btn btn-default btn-sm pull-right" data-id="${user.id}" onclick="onRequest()">抱大腿</a>
        </h3>

        <p class="info">
          <span class="label label-info">${user.skill1}</span>
          <span class="label label-info">${user.skill2}</span>
          <span class="label label-info">${user.skill3}</span>
        </p>
      </div>
      </c:forEach>
    </div>
  </div>
</div>

<script src="/lib/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
