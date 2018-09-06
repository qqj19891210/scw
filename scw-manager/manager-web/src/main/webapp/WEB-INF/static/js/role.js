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

    changePageStatus("/permission/role/list")

    //为所有分页链接绑定单击事件,让其动态带上分页查询参数
    $(".pagination").find("a").click(function () {
        //获取到查询表单的查询参数
        var param = $("input[name=searchParam]").val();
        //不禁用默认行为,而是为超链接拼装上查询条件
        var href = $(this).prop("href") + "&searchParam=" + param;
        $(this).prop("href", href);
    });

    $(".assignPermissionModelBtn").click(function () {
        var options = {
            //点击背景不会关闭模态框
            backdrop: "static",
            show: true
        };
        //permissionModel里刷出权限树
        //发送ajax请求获取到所有权限的json数据
        initPermissionTree($(this).attr("rid"));
        //打开模态框
        $('#permissionModel').modal(options);
        //将角色id保存到模态框的属性中
        //打开模态框将角色id传递给权限分配按钮
        $("#addPermissionBtn").attr("rid",$(this).attr("rid"));
    });

    //点击分配权限按钮
    $("#addPermissionBtn").click(function () {
        //获取当前选中的权限
        var nodes = ztreeObject.getCheckedNodes(true);
        //将这些权限和角色的id都发给后台处理
        var permission_ids="";
        var rid=$(this).attr("rid");
        $.each(nodes,function () {
            permission_ids+=this.id+",";
        });
        //找出这个角色对应的权限的值
        $.ajax({
            url: "/scw/permission/rolePermission/update?pids="+permission_ids+"&rid="+rid,
            contentType: "application/json",
            type: "GET",
            dataType: "json",
            async: false,
            cache: false,
            success:function (data) {
                if(data=="true"){
                    alert("权限分配成功");
                    $("#permissionModel").modal("hide");
                }
            }
        });
    });

});

function initPermissionTree(rid) {
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
            addDiyDom: showIcon
        },
        check: {
            enable: true
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
            //勾选当前角色的权限
            checkCurrentPermission(rid);
        }
    });
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

//treeId:是权限树ul的id
//treeNode:当前节点信息
function showIcon(treeId, treeNode) {
    //treeNode里边有一个tId,这个tId用来拼串以后就是图标显示位置的元素id和名字显示位置元素的id
    //改图标:找到当前元素图标显示的节点,将这个节点的class设置为当前节点的icon值
    $("#" + treeNode.tId + "_ico").removeClass().addClass(treeNode.icon);
}

//权限树的勾选方法,传入角色id
function checkCurrentPermission(rid) {
    //拿到角色的id
    //发送请求查出当前角色拥有的权限树
    $.ajax({
        url: "/scw/permission/rolePermission/" + rid,
        contentType: "application/json",
        type: "GET",
        dataType: "json",
        async: false,
        cache: false,
        success: function (data) {
            //查出了当前角色拥有的权限
            /*checkNode三个参数:
              1 要勾选的节点
              2 勾选与否
              3 是否和父节点级联互动
              4 勾选状态变化后,是否调用之前callback规定的回调函数
             */
            $.each(data, function () {
                //从ztree中获取到要勾选的对象
                var node = ztreeObject.getNodeByParam("id", this.id, null);
                ztreeObject.checkNode(node, true, false);
            });
        }
    });
}

