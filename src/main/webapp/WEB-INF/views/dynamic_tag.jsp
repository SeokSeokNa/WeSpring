<%--
  Created by IntelliJ IDEA.
  User: seokseok
  Date: 2021-08-28
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>
<table style="border: 1px solid black;">
        <thead>
            <th>컬럼1</th>
            <th>컬럼2</th>
            <th>컬럼3</th>
        </thead>
        <tbody>
            <tr>
                <td>값1</td>
                <td>값2</td>
                <td>값3</td>
            </tr>
        </tbody>

</table>

<button id="plus_tag" class="asdasd">태그 생성</button>

    <script type="text/javascript">
        $("#plus_tag").on('click' , function () {
            $("table > tbody").append("<tr><td>값1</td><td>값2</td><td>값3</td></tr>")
        })
    </script>
</body>
</html>
