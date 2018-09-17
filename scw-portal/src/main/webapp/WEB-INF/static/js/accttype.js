$(function () {

    $(".thumbnail").click(function () {
        $('.seltype').remove();
        $(this).prepend('<div class="glyphicon glyphicon-ok seltype"></div>');
    });

    var session = $.session.get("loginacct");
    showLoginacct(session);

});

function showLoginacct(session) {
    if (session == null) {
        window.location.href="/scw-portal/member/index";
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