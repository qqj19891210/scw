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
    getAllAdver(1);

    //展示数据之前有一个等待操作


    changePageStatus("/serviceCtrl/ads/list");

    //点击新增添加广告的模态框
    $("#adAdd_btn").click(function () {
        $("#ad_add_model").find(".imgDiv").empty();
        $("#ad_add_model").modal("show");
    });

    $("#submitBtn").click(function () {
        // 1 提价表单
        //使用formdata(js对象来包装form表单)
        //var data=new FormData($("#ad_form")[0]);
        var formData = new FormData();
        //添加一项要提交的内容,使用append即可
        formData.append("name", $("#ad_name_input").val());
        //取出文件项的真正文件信息
        formData.append("file", $("#ad_file_input")[0].files[0]);
        $.ajax({
            url: "/scw/serviceCtrl/ads/upload",
            contentType: false,//不使用默认的内容类型
            type: "POST",
            //data:data,
            data: formData,
            dataType: "json",
            async: false,
            cache: false,
            processData: false,//代表ajax不要处理和编码这些数据,直接提交
            success: function (result) {
                result = JSON.parse(result);
                for (var key in result) {
                    layer.msg(result[key]);
                }
            }
        });
        // 2 关闭模态框
        $("#ad_add_model").modal("hide");
        //初始化页面显示
        getAllAdver(99999);
    });

    //点击按钮查看广告图片
    //on 选择器(元素的祖先元素):大元素.on(事件名,元素选择器,回调函数)
    $("body").on("click", ".showImg", function () {
        var url = $(this).attr("url");
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '240px'],
            content: "<img style='height: 200px;width: 400px' src='/scw/" + url + "'/>"
        });
    });

    //为文件选择项绑定事件
    $("#ad_file_input").change(function (event) {
        //拿到图片项进行显示
        //回调函数获取到事件对象
        //可以用事件对象得到很多信息
        var files = event.target.files;
        var file;
        if (files.length > 0 && files) {
            file = files[0];
        }
        //匹配正则表达式
        var reg = /image*/;
        //只允许图片上传
        if(!reg.test(file.type)){
            alert("请选择一个图片文件");
            //如果不匹配图片类型,则不能选择这个文件
            $("#ad_file_input").val("");
            return false;
        }
        var URL=window.URL||window.webkitURL;
        //创建一个临时的url地址
        var imgURL=URL.createObjectURL(file);
        $(this).parent(".form-group").next(".form-group").find(".imgDiv").append("<img src='"+imgURL+"'/>");
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

//获取所有广告在页面显示
function getAllAdver(pn) {
    $.ajax({
        url: "/scw/serviceCtrl/ads/json",
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
            showAdsToTable($("#adsTable"), result.list);
            //显示分页条数据
            buildPage(result);
            layer.close(index);
        }
    });
}

//构件每行tr
function showAdsToTable(table, data) {
    //显示之前先移除之前的数据
    table.find("tbody").empty();
    //状态:0:未审核 1:审核成功 2:已发布(要发布到首页的) 3:已移除
    //给表单中填数据
    $.each(data, function () {
        var tr = $("<tr></tr>").append("<td>" + this.id + "</td>").append("<td>" + this.name + "</td>").append("<td>" + this.status + "</td>");
        var showImg = $("<button type='button' url='" + this.url + "' class='btn btn-success btn-xs showImg'><i class='glyphicon glyphicon-check'></i></button>");
        var editBtn = $("<button></button>").attr({
            type: "button"
        }).addClass("btn btn-primary btn-xs").append("<i class='glyphicon glyphicon-pencil'></i>");
        var delBtn = $("<button type='button' class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>");
        var tdBtn = $("<td></td>").append(showImg).append(" ").append(editBtn).append(" ").append(delBtn);
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
    ul.append("<li><a onclick='getAllAdver(" + 1 + ")'>首页</a></li>");
    //上一页,都应该点击上一页发送ajax请求
    if (page.hasPreviousPage) {
        ul.append("<li><a  onclick='getAllAdver(" + page.prePage + ")'>上一页</a></li>");
    } else {
        ul.append("<li class='disabled'><a>上一页</a></li>");
    }
    //构造连续页面
    var ns = page.navigatepageNums;
    $.each(ns, function () {
        //判断当前页要加class='active'
        if (page.pageNum == this) {
            ul.append($("<li><a onclick='getAllAdver(" + this + ")'>" + this + "</a></li>").addClass("active"));
        } else {
            ul.append($("<li><a onclick='getAllAdver(" + this + ")'>" + this + "</a></li>"));
        }
    });
    //下一页
    if (page.hasNextPage) {
        ul.append("<li><a  onclick='getAllAdver(" + page.nextPage + ")'>下一页</a></li>");
    } else {
        ul.append("<li class='disabled'><a>下一页</a></li>");
    }
    //尾页
    ul.append("<li><a onclick='getAllAdver(" + page.pages + ")'>尾页</a></li>");
    var div = $("<div>当前第" + page.pageNum + "页,总共" + page.pages + "页,一共" + page.total + "条记录</div>").addClass("judge");
    $("#pageBar_td").empty().append(ul).append(div);
}

//为后来新增的元素绑定事件