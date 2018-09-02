$(function () {
    $(".list-group-item").click(function () {
        if ($(this).find("ul")) {
            $(this).toggleClass("tree-closed");
            if ($(this).hasClass("tree-closed")) {
                $("ul", this).hide("fast");
            } else {
                $("ul", this).show("fast");
            }
        }
    });

    $("tbody .btn-success").click(function () {
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function () {
        window.location.href = "edit.html";
    });

    changePageStatus("/permission/user/list");

    //为所有分页链接绑定单击事件,让其动态带上分页查询参数
    $(".pagination").find("a").click(function () {
        //获取到查询表单的查询参数
        var param=$("input[name=searchParam]").val();
        //不禁用默认行为,而是为超链接拼装上查询条件
        var href = $(this).prop("href")+"&searchParam="+param;
        $(this).prop("href",href);
    });

});

function changePageStatus(url) {
    //当前页面所在的哪个超链接是color:red
    //他的父list-group-item的tree-closed是没有的
    //找到当前页面的a链接
    $("a[href='/scw" + url + "']").css("color", "red");

    //加上tree-closed样式
    $("a[href='/scw" + url + "']").parents(".list-group-item").removeClass("tree-closed");

    $("a[href='/scw" + url + "']").parent().parent("ul").show(100);
}
