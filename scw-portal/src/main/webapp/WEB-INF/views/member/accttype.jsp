<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/theme.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/accttype.css"/>"/>
</head>
<body>
<%@include file="/WEB-INF/views/includes/nav-bar.jsp" %>
<div class="container theme-showcase" role="main">
    <div class="page-header">
        <h1>实名认证 - 账户类型选择</h1>
    </div>
    <div style="padding-top:10px;">
        <div class="row" id="showAccountType">

        </div>
        <button type="button" id="toAuthPage_btn" class="btn btn-danger btn-lg btn-block"
                url="/scw-portal/auth/authPage">认证申请
        </button>
    </div>
</div>
<!-- /container -->
<!-- /END THE FEATURETTES -->


<div class="container" style="margin-top:20px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="footer">
                <div class="footerNav">
                    <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow"
                                                                                  href="http://www.atguigu.com">服务条款</a>
                    | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow"
                                                                                    href="http://www.atguigu.com">网站地图</a>
                    | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                </div>
                <div class="copyRight">
                    Copyright ?2017-2017atguigu.com 版权所有
                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/docs.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.session.js"/>"></script>
<script type="text/javascript" src="<c:url value="/layer/layer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/accttype.js"/>"></script>
</body>
</html>
