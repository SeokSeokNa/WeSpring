<%--
  Created by IntelliJ IDEA.
  User: seok
  Date: 2021-11-05
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>
<button onclick="loginWithKakao();">카카오 로그인</button>
<script type="text/javascript">
    function loginWithKakao() {
        $.ajax({
            url: '/login/getKakaoAuthUrl',
            type: 'get',
        }).done(function (res) {
            console.log(res);
            location.href = res;
        });

    }
</script>
</body>
</html>
