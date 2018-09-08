$(function () {

    $(".resetBtn").click(function () {
        $("#restPasswordForm").submit();
        return false;
    });

    $("#restPasswordForm").validate({
        rules: {
            password: {
                required: true,
                checkPassword: true
            },
            repassword: {
                required: true,
                checkRePassword: true
            }
        },
        messages: {
            password: {
                required: "密码不能为空",
            },
            repassword: {
                required: "确认密码不能为空",
            }
        },
        errorPlacement: function (error, element) {
            var spanId = $(element).prop("name") + "_span";
            error.appendTo("#" + spanId);
        }
    });

    $.validator.addMethod("checkPassword", function (value, element, params) {
        var checkPwd = /^[a-zA-Z0-9]{6,16}$/;
        return this.optional(element) || (checkPwd.test(value));
    }, "只允许6-16位英文字母、数字或者下画线！");

    $.validator.addMethod("checkRePassword", function (value, element, params) {
        var password = $("#password_input").val();
        var repassword = $("#repassword_input").val();
        return password == repassword;
    }, "两次密码输入不一致");

});