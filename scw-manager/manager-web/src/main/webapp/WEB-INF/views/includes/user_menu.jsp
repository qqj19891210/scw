<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="col-sm-3 col-md-2 sidebar">
    <div class="tree">
        <ul style="padding-left:0px;" class="list-group">
            <li class="list-group-item tree-closed">
                <a href="<c:url value="/permission/main"/>"><i class="glyphicon glyphicon-dashboard"></i>
                    控制面板</a>
            </li>
            <c:forEach var="menu" items="${userMenus}">
            <li class="list-group-item tree-closed">
                    <span><i class="${menu.icon}"></i> ${menu.name}<span class="badge"
                                                                         style="float:right"> ${fn:length(menu.children)}</span></span>
                <ul style="margin-top:10px;display:none;">
                    <c:forEach var="child_menu" items="${menu.children}">
                        <li style="height:30px;">
                            <a href="<c:url value="${child_menu.url}"/>"><i class="${child_menu.icon}"></i> ${child_menu.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            </c:forEach>
    </div>
</div>
