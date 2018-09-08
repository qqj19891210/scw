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

    changePageStatus("/permission/list");

    initPermissionTree();

    //click只能为当前页面已有的元素进行事件
    $(".editBtn").on("click",function () {
        alert(1);
    });

});

function initPermissionTree() {
    //ztree的设置
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idkey: "id",
                pIdKey: "pid"
            },
            key: {
                url: "no"
            }
        },
        view: {
            //自定义显示的效果
            addDiyDom: showIcon,
            addHoverDom: showEditBtn,
            removeHoverDom: hideEditBtn
        }
    };
    //从数据库查出的所有权限节点
    //发送ajax请求获取所有权限的json数据
    $.ajax({
        url: "/scw/permission/json",
        contentType: "application/json",
        type: "GET",
        dataType: "json",
        async: false,
        cache: false,
        success: function (nodes) {
            //给每一个节点修改或者添加一些属性
            $.each(nodes, function () {
                if (this.pid == 0) {
                    this.open = true;
                }
            });
            //不用var声明这个对象就是全局的
            //把初始化好的ztree对象传递给外界使用,可以通过操作这个对象,来改变整个树
            ztreeObject = $.fn.zTree.init($("#permissionTree"), setting, nodes);
        }
    });
}

//treeId:是权限树ul的id
//treeNode:当前节点信息
function showIcon(treeId, treeNode) {
    //treeNode里边有一个tId,这个tId用来拼串以后就是图标显示位置的元素id和名字显示位置元素的id
    //改图标:找到当前元素图标显示的节点,将这个节点的class设置为当前节点的icon值
    //移除默认图标
    $("#" + treeNode.tId + "_ico").removeClass();
    $("#" + treeNode.tId + "_ico").before("<span class='" + treeNode.icon + "'></span>");
}

//鼠标放在节点上显示编辑按钮
function showEditBtn(treeId, treeNode) {
    $("#" + treeNode.tId + "_a").nextAll("button").remove();
    //使用jquery创建对象
    var editBtn=$("<button type='button' class='btn btn-primary btn-xs editBtn'><span class='glyphicon glyphicon-align-left' aria-hidden='true'></span></button>");
    $("#" + treeNode.tId + "_a").after(editBtn);
    //并给这个对象绑定事件
    editBtn.click(function () {
        $("#myModal").modal("show");
    });
}

//鼠标移出节点时移除编辑按钮
function hideEditBtn(treeId, treeNode) {
    $("#" + treeNode.tId + "_a").nextAll("button").remove();
}

function changePageStatus(url) {
    //当前页面所在的哪个超链接是color:red
    //他的父list-group-item的tree-closed是没有的
    //找到当前页面的a链接
    $("a[href='/scw" + url + "']").css("color", "red");

    //加上tree-closed样式
    $("a[href='/scw" + url + "']").parents(".list-group-item").removeClass("tree-closed");

    $("a[href='/scw" + url + "']").parent().parent("ul").show(100);
}