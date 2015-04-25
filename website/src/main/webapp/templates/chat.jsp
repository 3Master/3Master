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
      <a class="navbar-brand" href="#">与 ${to.username} 的聊天</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/message">返回</a></li>
    </ul>
  </div>
</nav>

<div class="container-fluid">

  <div class="comment-list">
    <c:forEach var="message" items="${messages}" >
      <%@include file="chat-message.jsp"%>
    </c:forEach>
  </div>

  <form class="form-inline msg-form">
    <div class="form-group">
      <div class="input-group">
        <input type="text" class="form-control msg-input" placeholder="在此输入信息……">
        <div class="input-group-addon btn-send">发送</div>
      </div>
    </div>
  </form>

  <input type="hidden" id="remote-id" value="${to.id}">
  <input type="hidden" id="remote-name" value="${to.username}">
  <input type="hidden" id="remote-avatar" value="${to.avatar}">

  <input type="hidden" id="local-id" value="${currentUser.id}">
  <input type="hidden" id="local-name" value="${currentUser.username}">
  <input type="hidden" id="local-avatar" value="${currentUser.avatar}">
</div>

<script src="/lib/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/chat.js"></script>
</body>
</html>
