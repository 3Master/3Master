<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>消息</title>

  <link rel="icon" href="/img/logo.jpg">
  <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container">

  <div class="page-header">
    <h2>学生申请</h2>
  </div>

  <div class="comment-list">
    <div class="comment-list-item">
      <div class="avatar">
        <a><img src="<% user.avatar %>"></a>
      </div>
      <div class="body">
        <h3><% user.nickname %>
          <a class="btn btn-success btn-sm pull-right" href="/accept/<% user.id %>?_method=POST">收徒</a>
        </h3>
        <p class="info">
          <% for(int i=0; i < user.skills.size(); i++){ %>
          <span class="label label-info"><% user.skills[i] %></span>
          <% } %>
        </p>
      </div>
    </div>
  </div>

</div>

<script src="/lib/jquery.min.js"></script>
<script src="/lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
