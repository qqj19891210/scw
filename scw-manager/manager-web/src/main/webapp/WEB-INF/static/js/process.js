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

    //页面加载完成就调用
    getAllProcess(1);

    //展示数据之前有一个等待操作

    changePageStatus("/processCtrl/process/list");

    //点击新增添加广告的模态框
    $("#processAdd_btn").click(function () {
        $("#process_add_model").find(".imgDiv").empty();
        $("#process_add_model").modal("show");
    });

    $("#submitBtn").click(function () {
        // 1 提价表单
        //使用formdata(js对象来包装form表单)
        //var data=new FormData($("#ad_form")[0]);
        var formData = new FormData();
        //添加一项要提交的内容,使用append即可
        formData.append("processName", $("#process_name_input").val());
        //取出文件项的真正文件信息
        formData.append("processFile", $("#process_file_input")[0].files[0]);
        $.ajax({
            url: "/scw/processCtrl/process/upload",
            contentType: false,//不使用默认的内容类型
            type: "POST",
            //data:data,
            data: formData,
            dataType: "json",
            async: false,
            cache: false,
            processData: false,//代表ajax不要处理和编码这些数据,直接提交
            success: function (result) {
                for (var key in result) {
                    layer.msg(result[key]);
                    //初始化页面显示
                    getAllProcess(99999);
                }
            }
        });
        // 2 关闭模态框
        $("#process_add_model").modal("hide");

    });

    //点击按钮查看广告图片
    //on 选择器(元素的祖先元素):大元素.on(事件名,元素选择器,回调函数)
    $("body").on("click", ".showImg", function () {
        var pdId = $(this).attr("pdId");
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['800px', '600px'],
            content: "<img style='height: 500px;width: 700px' src='/scw/processCtrl/process/img?pdId="+pdId+"'/>"
        });
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

//获取所有流程在页面显示
function getAllProcess(pn) {
    $.ajax({
        url: "/scw/processCtrl/process/json",
        type: "GET",
        contentType: "application/json",
        data: "pn=" + pn,
        dataType: "json",
        async: false,
        cache: false,
        beforeSend: function () {
            //index是layer提示框的索引
            index = layer.load();
        },
        success: function (result) {
            //显示到数据的页面上
            showProcessToTable($("#proTable"), result.list);
            buildPage(result);
            layer.close(index);
        }
    });
}

//构件每行tr
function showProcessToTable(table, data) {
    //显示之前先移除之前的数据
    table.find("tbody").empty();
    $.each(data, function () {
        var tr = $("<tr></tr>").append("<td>" + this.id + "</td>").append("<td>" + this.name + "</td>").append("<td>" + this.key + "</td>").append("<td>" + this.version + "</td>");
        var showImg = $("<button type='button' pdId='" + this.id + "' class='btn btn-success btn-xs showImg'><i class='glyphicon glyphicon-eye-open'></i></button>");
        var delBtn = $("<button type='button' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>");
        var tdBtn = $("<td></td>").append(showImg).append(" ").append(delBtn);
        tdBtn.appendTo(tr);
        table.find("tbody").append(tr);
        //到此整个表格项构件完成
    });
}

//构建分页条
function buildPage(page) {
    var ul = $(" <ul class='pagination'></ul>");
    //各种判断append
    //首页
    ul.append("<li><a onclick='getAllProcess(" + 1 + ")'>首页</a></li>");
    //上一页,都应该点击上一页发送ajax请求
    if (page.hasPreviousPage) {
        ul.append("<li><a  onclick='getAllProcess(" + page.prePage + ")'>上一页</a></li>");
    } else {
        ul.append("<li class='disabled'><a>上一页</a></li>");
    }
    //构造连续页面
    var ns = page.navigatepageNums;
    $.each(ns, function () {
        //判断当前页要加class='active'
        if (page.pageNum == this) {
            ul.append($("<li><a onclick='getAllProcess(" + this + ")'>" + this + "</a></li>").addClass("active"));
        } else {
            ul.append($("<li><a onclick='getAllProcess(" + this + ")'>" + this + "</a></li>"));
        }
    });
    //下一页
    if (page.hasNextPage) {
        ul.append("<li><a  onclick='getAllProcess(" + page.nextPage + ")'>下一页</a></li>");
    } else {
        ul.append("<li class='disabled'><a>下一页</a></li>");
    }
    //尾页
    ul.append("<li><a onclick='getAllProcess(" + page.pages + ")'>尾页</a></li>");
    var div = $("<div>当前第" + page.pageNum + "页,总共" + page.pages + "页,一共" + page.total + "条记录</div>").addClass("judge");
    $("#pageBar_td").empty().append(ul).append(div);
}