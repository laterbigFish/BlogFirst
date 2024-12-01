$(document).ajaxSend(function (e, xhr, opt) {
    let token = localStorage.getItem("user_token");
    xhr.setRequestHeader("user_header_token", user_token);  //设置衣蛾user_header_token这样的头部  将其设置为 user_token
});

$(document).ajaxError(function(event,xhr,options,exc){
    if(xhr.status==401){
        alert("用户未登录, 请先登录");
        location.href = "blog_login.html";
    }else if(xhr.status==404){
        //TODO
    }else if(xhr.status==500){
        //TODO
    }
});