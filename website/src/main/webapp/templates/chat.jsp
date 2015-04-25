<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>消息</title>

  <link rel="icon" href="/img/logo.jpg">
  <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="/css/comment.css" rel="stylesheet">
  <link href="/css/chat.css" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-default navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">< 与 ${toUser.username} 的聊天</a>
    </div>
  </div>
</nav>

<div class="container-fluid">

  <div class="comment-list">
    <c:forEach var="message" items="${messages}" >
      <%@include file="chat-message.jsp"%>
    </c:forEach>
  </div>

</div>

<script src="/lib/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/chat.js"></script>
</body>
</html>
