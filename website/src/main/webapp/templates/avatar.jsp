
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