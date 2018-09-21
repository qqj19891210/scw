$(function () {

    //为文件选择项绑定事件
    $("input[type='file']").change(function (event) {
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
        if (!reg.test(file.type)) {
            alert("请选择一个图片文件");
            //如果不匹配图片类型,则不能选择这个文件
            $(this).val("");
            return false;
        }
        var URL = window.URL || window.webkitURL;
        //创建一个临时的url地址
        var imgURL = URL.createObjectURL(file);
        $(this).nextAll("div.imgShow").empty().append("<img src='" + imgURL + "'/>");
    });

    var memberId=$.session.get("memberid");
    $("#memberHidden").attr("value",memberId);

});

