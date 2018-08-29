$(function () {

    $("#submitBtn").click(function () {
        $("#registForm").submit();
        return false;
    });

    $("#registForm").validate({
        rules: {
            loginacct: {
                required: true,
                minlength: 6
            },
            userpswd: {
                required: true,
                minlength: 6
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            loginacct: {
                required: "用户账号不能为空",
                minlength: "用户账号必须大于6位"
            },
            userpswd: {
                required: true,
                minlength: 6
            },
            email: {
                required: true,
                email: true
            }
        }
    });

});