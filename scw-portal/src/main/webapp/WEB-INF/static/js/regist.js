$(function () {

    $("#submitBtn").click(function () {
        $("#registForm").submit();
        return false;
    });

    $("#registForm").validate({
        rules: {
            loginacct: {
                required: true,
                checkLoginacct: true,
                isLoginacctExist: true
            },
            userpswd: {
                required: true,
                checkUserpswd: true
            },
            email: {
                required: true,
                checkEmail: true,
                isEmailExist: true
            }
        },
        messages: {
            loginacct: {
                required: "用户账号不能为空",
                isLoginacctExist: "用户名已经存在"
            },
            userpswd: {
                required: "密码不能为空",
            },
            email: {
                required: "邮箱不能为空",
                isEmailExist: "此邮箱已经存在"
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

    $.validator.addMethod("checkEmail", function (value, element, params) {
        var checkEmail = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
        return this.optional(element) || (checkEmail.test(value));
    }, "请输入正确的邮箱！");

    //自定义用户名是否存在
    $.validator.addMethod("isLoginacctExist", function (value, element, params) {
        var flag = true;
        var loginaact=JSON.stringify($("#loginacct_input").val())
        $.ajax({
            url: "/scw-portal/member/isLoginacctExist",
            contentType: "application/json",
            data: loginaact,
            type: "POST",
            dataType: "json",
            async: false,
            cache: false,
            success: function (data) {
               if(data!=null){
                   flag=false;
               }
            }
        });
        return this.optional(element) || flag;
    });

    //自定义验证邮箱是否存在
    $.validator.addMethod("isEmailExist", function (value, element, params) {
        var flag = true;
        $.ajax({
            url: "/scw-portal/member/isEmailExist",
            contentType: "application/json",
            data: JSON.stringify($("#email_input").val()),
            type: "POST",
            dataType: "json",
            async: false,
            cache: false,
            success: function (data) {
                if(data!=null){
                    flag=false;
                }
            }
        });
        return this.optional(element) || flag;
    });

});
