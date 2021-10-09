<%--
  Created by IntelliJ IDEA.
  User: seokseok
  Date: 2021-08-21
  Time: 오후 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form action="/board/new" method="post">
    <div>
      <p>Here is where we send an email:</p>
      <input type="text" id="title"  name="title" placeholder="Sender Name" />
      <input type="text" id="content" name="content" placeholder="Email Subject" />
      <input type="text" id="userId" name="userId" value="nsk7647" />
      <button type="submit">전송</button>
    </div>
  </form>
</body>
</html>
