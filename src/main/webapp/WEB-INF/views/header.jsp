<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>2D Game Dev</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>

<body>
<div class="header_container">
    <header>
        <h2 class="header_main">NH.Project</h2>
        <nav class="header_nav">
            <ul>
                <li class="header_li"><a href="/" data-content="Home">Home</a></li>
                <li class="header_li"><a href="/board/list" data-content="Board">Board</a></li>
                <li class="header_li"><a href="/gallery/list" data-content="Gallery">Gallery</a></li>
                <c:choose> <%-- 문법의 하나로써 swich case 할려고 이렇게 써야됨 / jsp 라이브러리 --%>
                    <c:when test="${sessionScope.user_info != null}"><%--  if --%>
                        <li class="header_li"><a>${sessionScope.user_info.userName} 님</a></li>
                    </c:when>  <%--  if --%>
                    <c:otherwise><%-- else --%>
                        <li class="header_li"><a href="/signup/new" data-content="Login">Login</a></li>
                    </c:otherwise> <%-- else --%>
                </c:choose>


            </ul>
        </nav>
    </header>
</div>
</body>

</html>