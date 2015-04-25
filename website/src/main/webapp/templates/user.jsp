<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="avatar">
    <%@include file="avatar.jsp"%>
</div>
<div class="body">
    <h5>${user.username}</h5>
    <p class="info">
        <span class="label label-info">${user.skill1}</span>
        <span class="label label-info">${user.skill2}</span>
        <span class="label label-info">${user.skill3}</span>
    </p>
</div>