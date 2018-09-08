$(function () {

    $("#forgetPsw").validate({
        rules: {
            email: {
                required: true,
                checkEmail: true
            }
        },
        messages: {
            email: {
                required: "邮箱不能为空"
            }
        },
        errorPlacement: function (error, element) {
            var spanId = $(element).prop("name") + "_span";
            error.appendTo("#" + spanId);
        }
    });

    $.validator.addMethod("checkEmail", function (value, element, params) {
        var checkEmail =/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;
        return this.optional(element) || (checkEmail.test(value));
    }, "请输入正确的邮箱！");

});