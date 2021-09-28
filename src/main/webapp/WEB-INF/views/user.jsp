<%--
  Created by IntelliJ IDEA.
  User: seokseok
  Date: 2021-08-11
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>유저 정보</title>
    <style>
        h2 {
            color: darkkhaki;
        }
    </style>
</head>
<body>
    <h2>${user.userId}</h2>
    <h2>${user.userPass}</h2>
    <h2>${user.userName}</h2>
    <h2>${user.gender}</h2>
</body>
</html>
