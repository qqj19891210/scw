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

    changePageStatus("/permission/user/list");

    //为所有分页链接绑定单击事件,让其动态带上分页查询参数
    $(".pagination").find("a").click(function () {
        //获取到查询表单的查询参数
        var param = $("input[name=searchParam]").val();
        //不禁用默认行为,而是为超链接拼装上查询条件
        var href = $(this).prop("href") + "&searchParam=" + param;
        $(this).prop("href", href);
    });

    //全选和全不选功能
    checkAll_reverse($("#checkAll_btn"), $(".single_check"))

    //点击删除按钮,先拿到所有员工的id
    $(".deleteAllBtn").click(function () {
        var delUrl = "/scw/permission/user/del?ids=";
        var ids = "";
        $(".single_check:checked").each(function () {
            //取出自定义的id属性
            ids += $(this).attr("del_id") + ",";
        });
        //剔除最后一个逗号
        delUrl += ids.substring(0, ids.length - 1);
        if (confirm("确认删除【" + ids + "】这个员工吗")) {
            location.href = delUrl;
        }
        return false;
    });

    //权限分配按钮,来到权限分配页面
    $(".assignBtn").click(function () {
        //必须将用户id带给后台
        var id = $(this).attr("user_id");
        //跳转到权限分配页面
        var url="/scw/permission/role/toAssignRolePage?id="+id;
        location.href=url;
    });

    //删除小按钮
    $(".removeBtn").click(function () {;
        var uid=$(this).attr("remove_id");
        var delUrl="/scw/permission/user/del?ids="+uid;
        if (confirm("确认删除【" + uid + "】这个员工吗")) {
            location.href=delUrl;
        }
        return false;
    });

    //修改用户按钮
    $(".assignUserModelBtn").click(function () {
        var options = {
            //点击背景不会关闭模态框
            backdrop: "static",
            show: true
        };
        //打开模态框
        $('#permissionModel').modal(options);
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

//传入全选按钮对象
function checkAll_reverse(checkAll_btn, checkBtn) {
    checkAll_btn.click(function () {
        //获取单选框的值,如果选中则是true,反之是false
        var flag = $(this).prop("checked");
        checkBtn.prop("checked", flag);
    });
    checkBtn.click(function () {
        //当 checkBtn被点满以后checkAll_btn也勾上,否则不勾上
        //获取被选中的checkBtn的个数
        var flag = checkBtn.filter(":checked").length == checkBtn.length;
        checkAll_btn.prop("checked", flag);
    });
}
