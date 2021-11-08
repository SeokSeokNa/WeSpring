<%--
  Created by IntelliJ IDEA.
  User: Hwang
  Date: 2021-08-21
  Time: 오후 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script type ="text/javascript" src="/resources/js/auth.js"></script>
    <link rel="stylesheet" href="/resources/css/board.css">
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<body style="text-align: center;">

<section>
    <div id="main">
        <h1>게시판</h1>
    </div>

    <div class="container">
        <!-- Table -->
        <table class="table table-striped" id="boardtable" cellspacing="0">
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
            </tr>
            </thead>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td><c:out value="${board.num}"/></td>
                    <td>
                        <a href="/board/read?num=${board.num}">
                            <c:out value="${board.title}"/>
                        </a>
                    </td>
                    <td><c:out value="${board.id}"/></td>
                    <td><c:out value="${board.board_date}"/></td>
                </tr>
            </c:forEach>
        </table>
        <hr/>
        <%--        <a id="btn" class="btn btn-danger pull-right" href="/board/post" onclick="location.href='/board/post'">글쓰기</a>--%>
        <button id="write_btn" type="button" onclick="goUrl('/board/post')">글쓰기</button>
        <%--페이징--%>
        <c:choose>
            <c:when test="${boardList.size() > 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                            <%--이전버튼 상태표시--%>
                        <c:choose>
                            <c:when test="${currentPage==0}">
                                <li class="page-item disabled"><a class="page-link">이전</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                                         href="/board/list?currentPage=${currentPage-1}">이전</a></li>
                            </c:otherwise>
                        </c:choose>

                            <%--페이지 번호 표시--%>
                        <c:forEach var="i" begin="${start}" end="${end}" step="1">
                            <c:choose>
                                <c:when test="${currentPage+1==i}">
                                    <li class="page-item active"><a class="page-link"
                                                                    href="/board/list?currentPage=${i-1}&searchType=${param.searchType}&keyword=${param.keyword}">${i}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link"
                                                             href="/board/list?currentPage=${i-1}&searchType=${param.searchType}&keyword=${param.keyword}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>


                            <%--다음버튼 상태표시--%>
                        <c:choose>
                            <c:when test="${currentPage == pageCount-1}">
                                <li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                                         href="/board/list?currentPage=${currentPage+1}">다음</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </c:when>
            <c:otherwise>
                <p>게시글이 존재하지 않습니다!</p>
            </c:otherwise>

        </c:choose>


    </div>
</section>

<div class="search">
    <div class="searchBar">
        <select id="searchType" name="searchType">
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="id">작성자</option>
        </select>
        <input type="text" id="keyword" name="keyword" value="${param.keyword}"
               style="width: 300px; height: 40px; color: #0f0f0f"
               placeholder="검색어를 입력하세요"/>
        <button id="btnSearch" name="btnSearch">검색</button>
    </div>
</div>


<c:url var="boardList" value="/board/list"></c:url>
<script type="text/javascript">
    $(document).ready(function () {
        $(document).on('click', '#btnSearch', function (e) {
            e.preventDefault(); // 폼 태그의 전송을 막음
            var url = "${boardList}"; //{} -> EL
            url = url + "?searchType=" + $('#searchType').val(); //() -> 제이쿼리
            url = url + "&keyword=" + $('#keyword').val();
            location.href = url;
            console.log(url);
            //멍텅구리 바보 *2*4
        });
    });


    function fn_go_page(pageNo) {
        $("#pageIndex").val(pageNo);
        $("#listForm").submit();
        return false;
    }

    // function goPost(go_url) {
    //     $.ajax({
    //         url: go_url,
    //         dataType: "json",
    //         beforeSend: function (xmlHttpRequest) {
    //             xmlHttpRequest.setRequestHeader("AJAX","true");
    //         },
    //         error: function (request, status, error) { // success 는 동작을 안해서 error에서 200  코드받으면 이동  아니면 안함
    //             if (request.status == 200) {
    //                 location.href = go_url;
    //                 return;
    //             }
    //             console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
    //             let parse = JSON.parse(request.responseText);
    //             console.log(parse);
    //             if (confirm(parse.message)) {
    //                 location.href = "/signup/new";
    //             }
    //         }
    //     });
    // }

    $(document).ready(function () {
        $(document).on('click', '#btn', function (e) {
            e.preventDefault(); // 폼 태그의 전송을 막음
            if (${sessionScope.user_info ==null}) {
                alert("로그인 후 이용해주세요")
                location.href = "/";
            } else {
                location.href = "/board/post";
            }
        });
    });


</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>