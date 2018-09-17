$(function () {

    $(".thumbnail img").css("cursor", "pointer");
    $(".thumbnail img").click(function () {
        window.location.href = "project.html";
    });

    var session = $.session.get("loginacct");
    showLoginacct(session);

});

function showLoginacct(session) {
    if (session == null) {
        var noLogin = $("<li><a href='/scw-portal/member/login'>登录</a></li><li><a href='/scw-portal/member/member'>注册</a></li>");
        $(".navbar").find("ul").append(noLogin);
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