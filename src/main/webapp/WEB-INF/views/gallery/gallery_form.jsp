<%--
  Created by IntelliJ IDEA.
  User: nsk76
  Date: 2021-09-21
  Time: 오후 6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/gallery/new" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="제목을 입력해주세요"/>
    <input type="text" name="userId" value="${sessionScope.user_info.userId}" style="display: none"/>
    <input type="file" multiple="multiple" name="files"/>
    <button type="submit">전송</button>
</form>
    <a href="/gallery/list">목록</a>

</body>
</html>