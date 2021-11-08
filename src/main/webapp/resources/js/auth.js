


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
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            let parse = JSON.parse(request.responseText);
            console.log(parse);
            if (confirm(parse.message)) {
                location.href = "/signup/new";
            }

        }
    });
}


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
        error: function (request, status, error) { // success 는 동작을 안해서 error에서 200  코드받으면 이동  아니면 안함
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            let parse = JSON.parse(request.responseText);
            console.log(parse);
            if (confirm(parse.message)) {
                location.href = "/signup/new";
            }

        }
    });
}

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
        error: function (request, status, error) { // success 는 동작을 안해서 error에서 200  코드받으면 이동  아니면 안함
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            let parse = JSON.parse(request.responseText);
            console.log(parse);
            if (confirm(parse.message)) {
                location.href = "/signup/new";
            }

        }
    });
}



