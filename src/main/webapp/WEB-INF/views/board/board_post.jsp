<%--
  Created by IntelliJ IDEA.
  User: Hwang
  Date: 2021-08-21
  Time: 오후 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/board_post.css">
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<body>

<h1>글쓰기</h1>
<form action="/board/post" method="post">
    <div class="half left cf">
     <%--   <input type="hidden" id="id" placeholder="작성자" name = "id" />--%>
        <c:choose>
            <c:when test="${sessionScope.user_info != null}">
                <input id="id" name="id" type="text" value="${sessionScope.user_info.userId}" readonly style="display: none"/>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

        <input type="text" id="input-subject" placeholder="제목" name = "title"/>
    </div>
    <div class="half right cf">
        <textarea name="content" type="text" id="input-message" placeholder="내용"></textarea>
    </div>
    <input type="submit" value="Submit" id="input-submit">
</form>


</body>
</html>
