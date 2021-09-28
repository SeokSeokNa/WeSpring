<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hwang
  Date: 2021-08-15
  Time: 오후 6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <title>Title</title>
</head>
<body>

<div class="palm-tree one">
    <div class="palm-frond"></div>
    <div class="divet first"></div>
    <div class="divet second"></div>
</div>
<div class="palm-tree two">
    <div class="palm-frond"></div>
    <div class="divet first"></div>
    <div class="divet second"></div>
</div>
<div class="palm-tree three">
    <div class="palm-frond"></div>
    <div class="divet first"></div>
    <div class="divet second"></div>
</div>
<div class="palm-tree four">
    <div class="palm-frond"></div>
    <div class="divet first"></div>
    <div class="divet second"></div>
</div>
<div class="parasol"></div>
<div class="pool"></div>
<div class="flip-flop"></div>
<div class="flip-flop" id="second-flip-flop"></div>
<div class="chair"></div>
<div class="pool"></div>
<div class="inner-tube"></div>
<div class="sign">

<%--    <c:choose>--%>
<%--        <c:when test="${sessionScope.user_info != null}">--%>
<%--            <h3>${sessionScope.user_info.userName} 님</h3>--%>
<%--        </c:when>--%>
<%--        --%>
<%--        <c:otherwise>--%>
<%--            <h3>Test</h3>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>



    <h1>Pool Rules</h1>
    <ol>
        <li><a href="/signup/new">회원가입</a></li>
        <li><a href="/board/new">글쓰기</a></li>
        <li><a href="/board/list">게시판</a></li>
        <li><a href="/tag">동적태그</a></li>
    </ol>
    <c:choose>
        <c:when test="${sessionScope.user_info !=null}">
            <h2>${sessionScope.user_info.userName}님</h2>
        </c:when>

        <c:otherwise>
            <h2>No Lifeguard On Duty</h2>
        </c:otherwise>
    </c:choose>

    <h3>Swim at your own risk</h3>
</div>

</body>
</html>