<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="comment-list-item">

    <c:forEach var="message" items="${messages}" >
        <c:set var="user" value="to"/>

        <%@include file="avatar.jsp"%>

        <div class="body">
            <p class="content">${message.content}</p>
        </div>

    </c:forEach>

</div>