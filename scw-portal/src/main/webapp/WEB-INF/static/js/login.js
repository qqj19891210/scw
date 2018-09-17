$(function () {

    $("#loginForm").submit(function () {
        var type = $(":selected").val();
        var check = $("input[name='rememberMe']").is(":checked");
        //管理员登录
        var fromData = {
            "loginacct": $("#loginacct_input").val(),
            "userpswd": $("#userpswd_input").val(),
            "rememberMe": check
        };
        $.ajax({
            url: "/scw-portal/member/loginCheck",
            contentType: "application/json",
            data: JSON.stringify(fromData),
            type: "POST",
            dataType: "json",
            async: false,
            cache: false,
            success: function (data) {
                if (data.code == 1) {
                    $.session.set("loginacct",data.content.loginacct);
                    window.location.href = "/scw-portal/member/index";
                } else {
                    if (data.ext.error == "密码错误") {
                        $("#userpswd_span").html("密码错误");
                    }
                    if (data.ext.error == "账号错误") {
                        $("#loginacct_span").html("账号错误");
                    }
                }
            }
        });
        return false;
    });

    $("#logout").click(function () {
        window.location.href = "index";
    });

    $("#loginForm").validate({
        rules: {
            loginacct: {
                required: true,
                checkLoginacct: true
            },
            userpswd: {
                required: true,
                checkUserpswd: true
            }
        },
        messages: {
            loginacct: {
                required: "用户账号不能为空",
            },
            userpswd: {
                required: "密码不能为空",
            },
        },
        errorPlacement: function (error, element) {
            var spanId = $(element).prop("name") + "_span";
            error.appendTo("#" + spanId);
        }
    });

    $.validator.addMethod("checkLoginacct", function (value, element, params) {
        var checkName = /^[a-zA-Z]\w{6,16}$/;
        return this.optional(element) || (checkName.test(value));
    }, "只允许6-16位英文字母、数字或者下画线！");

    $.validator.addMethod("checkUserpswd", function (value, element, params) {
        var checkPwd = /^[a-zA-Z0-9]{6,16}$/;
        return this.optional(element) || (checkPwd.test(value));
    }, "只允许6-16位英文字母、数字或者下画线！");

});