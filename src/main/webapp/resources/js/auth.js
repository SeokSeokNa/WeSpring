//get 메소드
function goUrl(go_url) {
    $.ajax({
        url: go_url,
        method: "get",
        beforeSend: function (xmlHttpRequest) {
            xmlHttpRequest.setRequestHeader("AJAX","true");
        },
        success: function (res) {
            console.log(res);
            location.href = go_url;
        },
        error: function (request, status, error) { // success 는 동작을 안해서 error에서 200  코드받으면 이동  아니면 안함
            extracted(request, error);

        }
    });
}

//post 메소드
function goPost(go_url,form) {

    $.ajax({
        url: go_url,
        method:"post",
        beforeSend: function (xmlHttpRequest) {
            xmlHttpRequest.setRequestHeader("AJAX","true");
        },
        success: function (res) {
            form.submit();
        },
        error: function (request, status, error) {
            extracted(request, error);

        }
    });
}


//delete 메소드
function goDelete(go_url) {
    $.ajax({
        url: go_url,
        method:"post",
        beforeSend: function (xmlHttpRequest) {
            xmlHttpRequest.setRequestHeader("AJAX","true");
        },
        success: function (res) {
            location.href=go_url;
        },
        error: function (request, status, error) {
            extracted(request, error);


        }
    });
}

//토큰 재발급 메소드
function getToken() {
    $.ajax({
        url: "/user/getToken",
        method:"post",
        beforeSend: function (xmlHttpRequest) {
            xmlHttpRequest.setRequestHeader("AJAX","true");
        },
        success: function (res) {
            location.href="/getToken";
        },
        error: function (request, status, error) { // success 는 동작을 안해서 error에서 200  코드받으면 이동  아니면 안함
            extracted(request, error);

        }
    });
}


//에러처리 메소드
function extracted(request, error) {
    console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
    let parse = JSON.parse(request.responseText);
    console.log(parse);
    console.log("111");
    if (parse.code == "E0001") {
        if (confirm(parse.message)) {
            location.href = "/signup/new";
        }
    } else if (parse.code == "E0002") {
        getToken();
    } else if (parse.code == "E0003") {
        if (confirm(parse.message)) {
            location.href = "/signup/new";
        }
    }
}



