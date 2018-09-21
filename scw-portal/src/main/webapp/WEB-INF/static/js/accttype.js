$(function () {

    $("body").on("click",".thumbnail",function () {
        $('.seltype').remove();
        $(this).prepend('<div class="glyphicon glyphicon-ok seltype"></div>');
    });


    var session = $.session.get("loginacct");
    showLoginacct(session);

    $.ajax({
        url: "/scw-portal/accountType/show",
        contentType: "application/json",
        type: "POST",
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            if (data.code == 1) {
                var list = data.content;
                for (var key in list) {
                    var name = list[key].name;
                    var id = list[key].id;
                    showAccountType(name,id);
                }
            }else{
                layer.msg("加载失败,请重新刷新页面");
            }
        }
    });

    $("body").on("click","#toAuthPage_btn",function () {
        var aEle=$(".seltype").parent("a");
        if(aEle.length==1){
            var url=$(this).attr("url");
            var atId=$(".seltype").parent("a").parent("div").attr("atId");
            url+="?atId="+atId;
            window.location.href=url;
        }else{
            layer.msg("请先选择账户类型");
        }
    });

});

function showLoginacct(session) {
    if (session == null) {
        window.location.href = "/scw-portal/member/index";
    } else {
        var login = $("<li class='dropdown'></li>")
        login.append("<a href='' class='dropdown-toggle' data-toggle='dropdown'><i class='glyphicon glyphicon-user'></i>" + session + "<span class='caret'></span></a>\n" +
            "                            <ul class='dropdown-menu' role='menu'>\n" +
            "                                <li><a href='/scw-portal/member/main'><i class='glyphicon glyphicon-scale'></i> 会员中心</a></li>\n" +
            "                                <li><a href=''><i class='glyphicon glyphicon-comment'></i> 消息</a></li>\n" +
            "                                <li class='divider'></li>\n" +
            "                                <li><a href='index.html'><i class='glyphicon glyphicon-off'></i> 退出系统</a></li>\n" +
            "                            </ul>")
        $(".navbar").find("ul").append(login);
    }
}

function showAccountType(name, id) {
    var div = $(" <div class='col-xs-6 col-md-3' atId='" + id + "'></div>");
    var h2 = $("<h2>" + name + "</h2>");
    var a = $("<a href='#' class='thumbnail'>");
    var img = $("<img alt='100%x180' src='/scw-portal/images/services-box" + id + ".jpg' data-holder-rendered='true' style='height: 180px; width: 100%; display: block;'/>");
    a.append(img);
    div.append(h2).append(a);
    $("#showAccountType").append(div);
}