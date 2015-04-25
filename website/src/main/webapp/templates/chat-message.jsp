<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="comment-list-item">

    <c:forEach var="message" items="${messages}" >
        <c:if test="currentUser.id != message.fromId">
            <c:set var="user" value="message.from"/>
            <%@include file="user.jsp"%>
        </c:if>
    </c:forEach>

    <div class="body">
        <h5>${message.from.username}</h5>
        <p class="info">${message.body}</p>
    </div>
</div>