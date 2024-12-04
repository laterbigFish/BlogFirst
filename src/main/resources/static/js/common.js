$(document).ajaxSend(function (e, xhr, opt) {
    let token = localStorage.getItem("user_token");
    xhr.setRequestHeader("user_header_token", token)
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


function getUserInfo(url){
    $.ajax({
        type: "get",
        url: url,
        success: function(result){
            if(result!=null && result.code==200 && result.data!=null){
                let userInfo = result.data;
                $(".card h3").text(userInfo.userName);
                $(".card a").attr("href", userInfo.githubUrl);
            }else{
                //自行补充
            }
        }
    });
}

function logout(){
    let logout = confirm("是否确认退出");
    if(logout){
        localStorage.removeItem("user_token");
        localStorage.removeItem("login_user_id");
        location.href = "blog_login.html";
    }
    
}