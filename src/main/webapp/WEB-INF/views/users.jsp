<%--
  Created by IntelliJ IDEA.
  User: seokseok
  Date: 2021-08-11
  Time: 오후 3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <table style="border: 1px solid black;">
        <c:forEach items="${users}" var="list">
            <tr>
                <td>${list.id}</td>
                <td>${list.name}</td>
            </tr>

        </c:forEach>
    </table>

</body>
</html>
