<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/theme.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/member.css"/>"/>
</head>
<body>
<%@include file="/WEB-INF/views/includes/nav-bar.jsp" %>
<div class="container">
    <div class="row clearfix">
        <div class="col-sm-3 col-md-3 column">
            <div class="row">
                <div class="col-md-12">
                    <div class="thumbnail" style="    border-radius: 0px;">
                        <img src="<c:url value="/images/services-box1.jpg"/>" class="img-thumbnail" alt="">
                        <div id="memberDiv" class="caption" style="text-align:center;">
                            <h3>
                                <!--用户名-->
                            </h3>
                            <span class="label label-danger" style="cursor:pointer;"
                                  onclick="window.location.href='<c:url value="/member/auth"/>'">未实名认证</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list-group">
                <div class="list-group-item active">
                    资产总览<span class="badge"><i class="glyphicon glyphicon-chevron-right"></i></span>
                </div>
                <div class="list-group-item " style="cursor:pointer;"
                     onclick="window.location.href='minecrowdfunding.html'">
                    我的众筹<span class="badge"><i class="glyphicon glyphicon-chevron-right"></i></span>
                </div>
            </div>
        </div>
        <div class="col-sm-9 col-md-9 column">
            <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                <b>
                    我的钱包
                </b>
            </blockquote>
            <div id="main" style="width: 600px;height:400px;"></div>
            <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                <b>
                    理财
                </b>
            </blockquote>
            <div id="main1" style="width: 600px;height:400px;"></div>
            <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
                <b>
                    比例
                </b>
            </blockquote>
            <div id="main2" style="width: 600px;height:400px;"></div>
        </div>
    </div>
</div>
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
<script type="text/javascript" src="<c:url value="/script/back-to-top.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/echarts.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/member.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.session.js"/>"></script>
</body>
</html>
