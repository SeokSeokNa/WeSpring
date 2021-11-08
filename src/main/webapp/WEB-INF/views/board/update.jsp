<%--
  Created by IntelliJ IDEA.
  User: Hwang
  Date: 2021-08-23
  Time: 오후 6:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="/resources/css/bootStrap/bootStrap_css/bootstrap.min.css">
  <script type ="text/javascript" src="/resources/js/auth.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<body>


<div class="row" style="margin-bottom:20px; margin-left:1px;">
  <div class="col-lg-12">
    <h1 class="page-header">수정 페이지</h1>
  </div>
</div>

<div class="panel" style="margin-left:1px;">
  <div id="contAreaBox">
    <div class="panel">
      <div class="panel-body">
        <form role="form" action="/board/postUpdate" method="post" onsubmit="return _onSubmit();">
          <input type="hidden" id="num" name="num" value="${boardContents.num}">
          <div class="table-responsive" style="text-align:center;">
            <div class="container">
            <table id="datatable-scroller"
                   class="table table-bordered tbl_Form">
              <caption></caption>
              <colgroup>
                <col width="250px" />
                <col />
              </colgroup>
              <tbody>
              <tr>
                <th class="active" >작성자</th>
                <td class="form-inline">
                  <input type="hidden" id="id"
                    name="id" class="form-control" style="width: 200px" value="${boardContents.id}"/>
                  ${boardContents.id}
                </td>
              </tr>
              <tr>
                <th class="active">제목</th>
                <td class="form-inline">
                  <input type="text" id="title"
                     name="title" class="form-control" style="width: 840px" value="${boardContents.title}"/>
                </td>
              </tr>
              <tr>
                <th class="active" >내용</th>
                <td class="form-inline"><textarea
                        id="content" name="content" cols="100" rows="10"
                        class="form-control">${boardContents.content }</textarea></td>
              </tr>
              </tbody>
            </table>
          </div>
          <div style="margin-left:1px;">
            <button type="submit" class="btn btn-primary">수정</button>
            <a class="btn btn-danger pull-right" href="/board/read?num=${boardContents.num}">취소</a>
          </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/resources/css/bootStrap/bootStrap_js/jquery.min.js"></script>
<script src="/resources/css/bootStrap/bootStrap_js/js/bootstrap.min.js"></script>
<script type="text/javascript">
  function _onSubmit(){
    if (!confirm("수정하시겠습니까?")){
      let form = $("form");
      goPost(form.action.url, form);
      return false;
    }
  }
</script>

</body>
</html>
