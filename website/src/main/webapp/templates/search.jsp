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

    <link href="/css/comment.css" rel="stylesheet">
    <link href="/css/navbar.css" rel="stylesheet">
  </head>

  <body>

  <nav class="navbar navbar-default navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">三人行</a>
      </div>

      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a>搜索</a></li>
        <li><a href="/message">我的</a></li>
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
      <c:forEach var="user" items="${users}" >
        <div class="comment-list-item">
          <%@include file="user.jsp"%>
          <div class="tag">
            <a class="btn btn-default btn-sm pull-right" data-id="${user.id}" onclick="onRequest()">抱大腿</a>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>

  <script src="/lib/jquery.min.js"></script>
  <script src="/lib/bootstrap/js/bootstrap.min.js"></script>
  <script src="/js/search.js"></script>
</body>
</html>
