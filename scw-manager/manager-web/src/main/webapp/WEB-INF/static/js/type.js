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

    changePageStatus("/serviceCtrl/type/ctrl");

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