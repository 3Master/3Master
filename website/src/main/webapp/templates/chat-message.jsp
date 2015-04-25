<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="comment-list-item">
  <div class="avatar">
    <c:choose>
        <c:when test="not empty user.avatar">
            <a><img src="${message.user.avatar}"></a>
        </c:when>
        <c:otherwise>
            <a><img src="/img/default-avatar.jpeg"></a>
        </c:otherwise>
    </c:choose>
  </div>
  <div class="body">
    <h5>${message.user.username}</h5>
    <p class="info">${message.body}</p>
  </div>
</div>