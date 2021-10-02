<%--
  Created by IntelliJ IDEA.
  User: seokseok
  Date: 2021-09-28
  Time: 오후 2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/boardList.css">
    <title>Title</title>

</head>
<body>
    <div class="container">
        <br>
        <h1 class="text-center"><a href="#">게시판</a></h1>

        <br>
        <br>
        <table class="table table-hover table-striped text-center" style="border: 1px solid">
            <thead>
            <tr>
                <th>페이지 번호</th>
                <th>글번호</th>
                <th>제목</th>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${boardList}" var="board">
                <tr>
                    <td>${board.pageNo}</td>
                    <td>${board.boardNum}</td>
                    <td>${board.title}</td>
                    <td>${board.content}</td>
                    <td>${board.userId}</td>
                    <td>${board.writeDate}</td>
                    <td>${board.hit}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>

        <%--페이지 버튼--%>

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <%--이전버튼 상태표시--%>
                <c:choose>
                    <c:when test="${currentPage==0}">
                        <li class="page-item disabled"><a class="page-link">이전</a></li><%--클래스에 disabled 클래스 추가 함으로 disabled css 적용7ㄹ--%>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/board/list?currentPage=${currentPage-1}">이전</a></li>
                    </c:otherwise>
                </c:choose>

                <%--페이지 버튼 개수 생성--%>
                <c:forEach var="i" begin="${start}" end="${end}" step="1">
                    <c:choose>
                        <c:when test="${currentPage == i-1}">
                            <li class="page-item active"><a class="page-link" href="/board/list?currentPage=${i-1}">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/board/list?currentPage=${i-1}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>

                 <%--다음버튼 상태표시--%>
                 <c:choose>
                     <c:when test="${currentPage == pageCount-1}">
                         <li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
                     </c:when>
                     <c:otherwise>
                         <li class="page-item"><a class="page-link" href="/board/list?currentPage=${currentPage+1}">다음</a></li>
                     </c:otherwise>
                 </c:choose>
            </ul>
        </nav>
        <a class="btn btn-outline-info float-right btn_float">글쓰기</a>
    </div>



</body>
</html>
