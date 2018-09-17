<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/advertisement.css"/>">
</head>
<body>
${pageContext.setAttribute("navinfo","广告管理")}
<%@include file="/WEB-INF/views/includes/nav-bar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/views/includes/user_menu.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" id="adAdd_btn" class="btn btn-primary" style="float:right;"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table id="adsTable" class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th>广告描述</th>
                                <th>状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td id="pageBar_td" colspan="4" align="center">

                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--广告添加的模态框-->
<div class="modal fade" id="ad_add_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加广告</h4>
            </div>
            <div class="modal-body">
                <form id="ad_form">
                    <div class="form-group">
                        <label for="ad_name_input">广告名称</label>
                        <input type="text" name="name" class="form-control" id="ad_name_input" placeholder="广告名称">
                    </div>
                    <div class="form-group">
                        <label for="ad_file_input">选择广告</label>
                        <input type="file" name="file" id="ad_file_input">
                    </div>
                    <div class="form-group">
                        <!--选择文件的展示位-->
                        <div class="row">
                            <div class="col-md-12 imgDiv"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="submitBtn" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/docs.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/layer/layer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/advertisement.js"/>"></script>
</body>
</html>
