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
      <a class="navbar-brand" href="#">< 与 ${user.username} 的聊天</a>
    </div>
  </div>
</nav>

<div class="container-fluid">

  <div class="comment-list">
    <c:forEach var="message" items="${messages}" >
      <%@include file="chat-message.jsp"%>
    </c:forEach>
    <c:forEach var="user" items="${unpassedTeachers}" >
        <div class="comment-list-item">
        <%@include file="user.jsp"%>
        <div class="tag">
          <span class="label label-default pull-right">等待通过</span>
        </div>
        </div>
    </c:forEach>
  </div>

    <div class="page-header">
    <h3>徒弟</h3>
    </div>

    <div class="comment-list">
    <c:forEach var="user" items="${passedStudents}" >
      <div class="comment-list-item">
        <%@include file="user.jsp"%>
        <div class="tag">
          <h4 class="pull-right">
            <a href="/students/${user.id}?_method=DELETE">
              <i class="fa fa-trash-o"></i>
            </a>
            <a class="btn-chat" href="/chat/${user.id}">
              <i class="fa fa-comment-o"></i>
            </a>
          </h4>
        </div>
      </div>
    </c:forEach>
    <c:forEach var="user" items="${unpassedStudents}" >
        <div class="comment-list-item">
        <%@include file="user.jsp"%>
        <div class="tag">
        <h4 class="pull-right">
        <a href="/students/${user.id}?_method=POST">
        <i class="fa fa-check"></i>
        </a>
        <a href="/students/${user.id}?_method=DELETE">
        <i class="fa fa-close"></i>
        </a>
        </h4>
        </div>
        </div>
    </c:forEach>
    </div>
</div>

<script src="/lib/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/lib/sockjs.min.js"></script>
<script src="/lib/stomp.min.js"></script>
<script src="/js/chat.js"></script>
</body>
</html>
