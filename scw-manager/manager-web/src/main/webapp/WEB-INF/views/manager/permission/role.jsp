<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/ztree/zTreeStyle.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/role.css"/>">
</head>
<body>
${pageContext.setAttribute("navinfo","角色维护")}
<%@ include file="/WEB-INF/views/includes/nav-bar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/views/includes/user_menu.jsp" %>
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
                                <input class="form-control has-success" name="searchParam" type="text"
                                       placeholder="请输入查询条件" value="${searchParam}"/>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="role" items="${pageInfo.list}">
                                <tr>
                                    <td>${role.id}</td>
                                    <td><input type="checkbox"></td>
                                    <td>${role.name}</td>
                                    <td>
                                        <button type="button" rid="${role.id}"
                                                class="btn btn-success btn-xs assignPermissionModelBtn"><i
                                                class=" glyphicon glyphicon-check"></i></button>
                                        <button type="button" class="btn btn-primary btn-xs"><i
                                                class=" glyphicon glyphicon-pencil"></i></button>
                                        <button type="button" class="btn btn-danger btn-xs"><i
                                                class=" glyphicon glyphicon-remove"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li><a href="<c:url value="/permission/role/list?pageNumber=1"/>">首页</a></li>
                                        <c:choose>
                                            <c:when test="${pageInfo.hasPreviousPage}">
                                                <li>
                                                    <a href="<c:url value="/permission/role/list?pageNumber=${pageInfo.prePage}"/>">上一页</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="disabled"><a href="#">上一页</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="currentPageNumber" items="${pageInfo.navigatepageNums}">
                                            <c:if test="${currentPageNumber==pageInfo.pageNum}">
                                                <li class="active"><a
                                                        href="<c:url value="/permission/role/list?pageNumber=${currentPageNumber}"/>">1
                                                    <span class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${currentPageNumber!=pageInfo.pageNum}">
                                                <li>
                                                    <a href="<c:url value="/permission/role/list?pageNumber=${currentPageNumber}"/>">(current)</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${pageInfo.hasNextPage}">
                                                <li>
                                                    <a href="<c:url value="/permission/role/list?pageNumber=${pageInfo.nextPage}"/>">上一页</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="disabled"><a href="#">下一页</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                        <li>
                                            <a href="<c:url value="/permission/role/list?pageNumber=${pageInfo.pages}"/>">尾页</a>
                                        </li>
                                    </ul>
                                    <div class="judge">
                                        当前第 ${pageInfo.pageNum} 页,总共 ${pageInfo.pages} 页,一共 ${pageInfo.total} 条记录
                                    </div>
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
<!--模态框-->
<div class="modal fade" id="permissionModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">分配权限</h4>
            </div>
            <div class="modal-body">
                <!--展示权限树-->
                <div>
                    <ul id="permissionTree" class="ztree">

                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="addPermissionBtn" class="btn btn-primary">分配权限</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/docs.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/ztree/jquery.ztree.all-3.5.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/role.js"/>"></script>
</body>
</html>
