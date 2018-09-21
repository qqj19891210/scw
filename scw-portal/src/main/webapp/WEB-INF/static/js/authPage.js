$(function () {

    var session = $.session.get("loginacct");
    showLoginacct(session);

    //文档加载完成先刷新第一个基本信息
    $.ajax({
        url: "/scw-portal/auth/apply",
        contentType: "application/json",
        type: "POST",
        async: false,
        cache: false,
        success: function (data) {
            //每次append先清空
            $(".pageContent").empty().append(data);
        }
    });

    $("li[role='presentation']").click(function () {
        $(this).addClass("active").siblings("li[role='presentation']").removeClass("active");
        //发送请求获取html
        var url = $(this).find("a").attr("href");
        $.ajax({
            url: url,
            contentType: "application/json",
            type: "POST",
            async: false,
            cache: false,
            success: function (data) {
                //每次append先清空
                $(".pageContent").empty().append(data);
            }
        });
        return false;
    });

    //这些上一步和下一步的button都是后来发送ajax得到的,所以要用on绑定事件
    $("body").on("click", ".unuse_btn", function () {
        var url = $(this).attr("url");
        //这个请求不是ajax需要跳转
        if (url == "/scw-portal/member/auth") {
            location.href = "/scw-portal/member/auth";
            return false;
        } else if ($(this).attr("form") == "true") {
            //这是一个资质文件上传的页面,发送文件上传资质文件的请求
            var formData = new FormData($("#certForm")[0]);
            $.ajax({
                url: "http://localhost:8082/scw-restapi/auth/upload",
                type: "post",
                contentType: false,
                processData: false,
                data: formData,
                dataType: "json",
                async: false,
                cache: false,
                success: function (data) {
                    if (data.msg.code == 1) {
                        //发送ajax来到下一步
                        $.ajax({
                            url: "/scw-portal/auth/apply-2",
                            //contentType: "application/json",
                            type: "POST",
                            async: false,
                            cache: false,
                            success: function (data) {
                                //填充数据
                                $(".pageContent").empty().append(data);
                                // 本页会有一个隐藏button带了本页的url
                                var hiddenUrl = $("button:hidden.unuse_btn").attr("url");
                                //某些li置为active,btn的url和a的url是一样的,
                                $("a[href='" + hiddenUrl + "']").parents("li[role='presentation']").addClass("active").siblings("li[role='presentation']").removeClass("active");
                            }
                        });
                    } else {
                        layer.msg("资质图片上传失败,请重新上传");
                    }
                }
            });
            return false;
        }
        var params = $(".submitBtn").serialize();
        $.ajax({
            url: url,
            //contentType: "application/json",
            type: "POST",
            data: params,
            async: false,
            cache: false,
            success: function (data) {
                //填充数据
                $(".pageContent").empty().append(data);
                // 本页会有一个隐藏button带了本页的url
                var hiddenUrl = $("button:hidden.unuse_btn").attr("url");
                //某些li置为active,btn的url和a的url是一样的,
                $("a[href='" + hiddenUrl + "']").parents("li[role='presentation']").addClass("active").siblings("li[role='presentation']").removeClass("active");
            }
        });
        //表单可能有默认提交
        return false;
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