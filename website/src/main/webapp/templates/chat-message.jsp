<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="comment-list-item">

    <c:forEach var="message" items="${messages}" >
        <c:if test="${currentUser.id != message.from.id}">
            <c:set var="user" value="message.from"/>
            <%@include file="avatar.jsp"%>
        </c:if>

        <div class="body">
            <p>${message.body}</p>
        </div>

        <c:if test="${currentUser.id == message.from.id}">
            <c:set var="user" value="message.from"/>
            <%@include file="avatar.jsp"%>
        </c:if>

    </c:forEach>

</div>