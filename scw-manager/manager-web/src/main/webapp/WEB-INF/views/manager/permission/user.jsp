<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/user.css"/>">
</head>
<body>
${pageContext.setAttribute("navinfo","用户维护")}
<%@ include file="/WEB-INF/views/includes/nav-bar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/views/includes/user_menu.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;" method="post"
                          action="<c:url value="/permission/user/list"/>">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" name="searchParam" type="text"
                                       placeholder="请输入账号或者昵称信息" value="${searchParam}"/>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='add.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${pageInfo.list}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td><input type="checkbox"></td>
                                    <td>${user.loginacct}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs"><i
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
                                        <%--
                                            即使点击分页链接也应该带上查询条件的值
                                            给分页超链接绑定单机事件
                                        --%>
                                        <li><a href="<c:url value="/permission/user/list?pageNumber=1"/>">首页</a></li>
                                        <!--判断是否有上一页-->
                                        <c:choose>
                                            <c:when test="${pageInfo.hasPreviousPage}">
                                                <li>
                                                    <a href="<c:url value="/permission/user/list?pageNumber=${pageInfo.prePage}"/>">上一页</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="disabled">
                                                    <a href="#">上一页</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                        <!--遍历连续显示的页码-->
                                        <c:forEach var="currentPageNumber" items="${pageInfo.navigatepageNums}">
                                            <!--如果是当前页有高亮标记-->
                                            <c:if test="${currentPageNumber==pageInfo.pageNum}">
                                                <li class="active"><a
                                                        href="<c:url value="/permission/user/list?pageNumber=${currentPageNumber}"/>">${currentPageNumber}<span
                                                        class="sr-only">(current)</span></a></li>
                                            </c:if>
                                            <c:if test="${currentPageNumber!=pageInfo.pageNum}">
                                                <li>
                                                    <a href="<c:url value="/permission/user/list?pageNumber=${currentPageNumber}"/>">${currentPageNumber}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <c:choose>
                                            <c:when test="${pageInfo.hasNextPage}">
                                                <li>
                                                    <a href="<c:url value="/permission/user/list?pageNumber=${pageInfo.nextPage}"/>">下一页</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="disabled">
                                                    <a href="#">下一页</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                        <li>
                                            <a href="<c:url value="/permission/user/list?pageNumber=${pageInfo.pages}"/>">尾页</a>
                                        </li>
                                    </ul>
                                    <!--文字信息-->
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
<script src="<c:url value="/jquery/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/bootstrap/bootstrap.min.js"/>"></script>
<script src="<c:url value="/script/docs.min.js"/>"></script>
<script src="<c:url value="/js/user.js"/>"></script>
</body>
</html>
