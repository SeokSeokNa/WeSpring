<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/gallery_list.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Title</title>
</head>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<body>

<c:choose>
<c:when test="${sessionScope.user_info.userId != null}">
    <div style="float:right;">
        <input type="button" class="btn btn-outline-success" value="글쓰기" id="create" name="create" onclick="location.href='/gallery/new'">
        <input type="button" class="btn btn-outline-info" value="삭제" id="delete" name="delete" onclick="deleteValue();" >
    </div>

</c:when>
</c:choose>
<div id="wrapper">
    <section>
        <ul id="gallery">
            <c:forEach var="gallery" items="${galleryList}">
                <li>
                    <c:choose>
                    <c:when test="${sessionScope.user_info.userId == gallery.userId}">
                        <input type="checkbox" style="float: right" id="chk" name="chk" value="${gallery.galleryNo}"/>
                    </c:when>
                    </c:choose>
                    <p>${gallery.title}</p>
                    <c:forEach var="i"  begin="0" end="${gallery.photoDtoList.size()-1}" step="1">
                        <c:choose>
                            <%--해당글의 첫번째 사진이라면--%>
                            <c:when test="${i==0}">
                                <a href="${gallery.photoDtoList.get(i).location}?image=250" data-toggle="lightbox" data-title="${gallery.title}"  data-footer="my photo.."   data-gallery="gallery${gallery.galleryNo}" name="checkRow">
                                    <img src="${gallery.photoDtoList.get(i).location}?image=250" alt="" class="img-fluid" data-name = "${gallery.photoDtoList.get(i).newName}">
                                </a>
                            </c:when>
                            <%--해당글의 첫번째 사진이 아니라면--%>
                            <c:otherwise>
                                <a href="${gallery.photoDtoList.get(i).location}?image=250" data-toggle="lightbox" data-title="${gallery.title}" data-footer="my photo.."   data-gallery="gallery${gallery.galleryNo}" style="display: none">
                                    <img src="${gallery.photoDtoList.get(i).location}?image=250" alt="" class="img-fluid" data-name = "${gallery.photoDtoList.get(i).newName}">
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <p>${gallery.userId}</p>
                </li>
            </c:forEach>
    </section>
</div>


<%-- 페이징 구간--%>
<div style="position: absolute;top: 80%;left: 50%;">
    <c:choose>
        <c:when test="${galleryList.size() > 0}">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                        <%--이전버튼 상태표시--%>
                    <c:choose>
                        <c:when test="${currentPage==0}">
                            <li class="page-item disabled"><a class="page-link">이전</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/gallery/list?currentPage=${currentPage-1}">이전</a></li>
                        </c:otherwise>
                    </c:choose>

                        <%--페이지 번호 표시--%>
                    <c:forEach var="i" begin="${start}" end="${end}" step="1">
                        <c:choose>
                            <c:when test="${currentPage+1==i}">
                                <li class="page-item active"><a class="page-link" href="/gallery/list?currentPage=${i-1}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/gallery/list?currentPage=${i-1}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>


                        <%--다음버튼 상태표시--%>
                    <c:choose>
                        <c:when test="${currentPage == pageCount-1}">
                            <li class="page-item disabled"><a class="page-link" href="#">다음</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/gallery/list?currentPage=${currentPage+1}">다음</a></li>
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



<script type="text/javascript">
    $(document).ready(function() {
        $(document).on('click', '#writeBtn' ,function (e){
            e.preventDefault();
            if (${sessionScope.user_info ==null}) {
                alert("로그인 후 이용해주세요")
                location.href="/";
            } else {
                location.href="/gallery/new";
            }
        });
    });

    $(document).on("click", '[data-toggle="lightbox"]', function(event) {
        var option ={
            loadingMessage:'Loading...',
            maxWidth:1500,
            maxHeight: 800
        }
        event.preventDefault();

        $(this).ekkoLightbox(option);
    });


    function deleteValue() {
        var url = "/gallery/delete";
        var valueArr = new Array();
        var list = $("input[name='chk']");
        var img = $("input[name='chk']").parent().children().children("img");
        //사진 이름담기...
        for (var i = 0; i < img.length; i++) {
            let data = $(img[i]).data("name");
            console.log(data);
        }
        var img_data = $("input[name='chk']").parent().children().children(".img-fluid").data("name")
        for (var i = 0; i < list.length; i++) {
            if (list[i].checked) {
                valueArr.push(list[i].value);
            }

        }

        if (valueArr.length == 0){
            alert("선택된 게시물이 없습니다");
        }else {
            var deleteChk = confirm("정말 삭제하시겠습니까?");
            if (deleteChk) {
                $.ajax({
                    url: url,
                    type: 'POST',
                    traditional: true, /*자바 controller에게 배열 파라미터를 넘겨줄경우 꼭 필 수!*/
                    data: {
                        "valueArr": valueArr
                    },
                    success: function (data) {
                        if (data = 1) {
                            alert("삭제성공");
                            location.replace("/gallery/list")
                        } else {
                            alert("삭제 실패");
                        }
                    }
                });
            }

        }
    }










</script>
</body>
</html>