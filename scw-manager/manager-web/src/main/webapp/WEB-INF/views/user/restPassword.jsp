<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>重置密码</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/restPassword.css"/>">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-重置密码</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <form id="restPasswordForm" class="form-signin" role="form" method="post"
          action="<c:url value="/permission/user/updatePwd"/>">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 重置密码</h2>
        <input type="hidden" name="token" value="${param.token}"/>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="password_input" name="password" placeholder="请输入新密码"
                   autofocus>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <div class="errorClass" id="password_span"></div>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="repassword_input" name="repassword" placeholder="再次确认密码"
                   style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <div class="errorClass" id="repassword_span"></div>
        </div>
        <button class="btn btn-lg btn-success btn-block resetBtn">确认重置</button>
    </form>
</div>
<script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/messages_zh.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/restPassword.js"/>"></script>
</body>
</html>

