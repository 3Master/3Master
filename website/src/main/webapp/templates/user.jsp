<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="avatar">
    <c:choose>
        <c:when test="not empty user.avatar">
            <a><img src="${user.avatar}"></a>
        </c:when>
        <c:otherwise>
            <a><img src="/img/default-avatar.jpeg"></a>
        </c:otherwise>
    </c:choose>
</div>
<div class="body">
    <h5>${user.username}</h5>
    <p class="info">
        <span class="label label-info">${user.skill1}</span>
        <span class="label label-info">${user.skill2}</span>
        <span class="label label-info">${user.skill3}</span>
    </p>
</div>