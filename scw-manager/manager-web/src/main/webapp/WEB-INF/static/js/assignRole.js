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

    $(".glyphicon-chevron-right").click(function () {
        var rids = "";
        $(".unrole_select option:selected").each(function () {
            rids += $(this).val() + ",";
        });
        rids = rids.substring(0, rids.length - 1);
        // 1选中的过去
        var uid = getUrlParam("id");
        $.ajax({
            url: "/scw/permission/userRole/assignRole?opt=add&uid=" + uid + "&rids=" + rids,
            contentType: "application/json",
            type: "GET",
            dataType: "json",
            async: false,
            cache: false,
            success: function (data) {
                $(".unrole_select option:selected").appendTo(".role_select");
                alert(data);
            }
        });
    });

    $(".glyphicon-chevron-left").click(function () {
        var uid = getUrlParam("id");
        var rids = "";
        $(".role_select option:selected").each(function () {
            rids += $(this).val() + ",";
        });
        rids = rids.substring(0, rids.length - 1);
        $.ajax({
            url: "/scw/permission/userRole/assignRole?opt=remove&uid=" + uid + "&rids=" + rids,
            contentType: "application/json",
            type: "GET",
            dataType: "json",
            async: false,
            cache: false,
            success: function (data) {
                $(".role_select option:selected").appendTo(".unrole_select");
                alert(data);
            }
        });
    });

});

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}