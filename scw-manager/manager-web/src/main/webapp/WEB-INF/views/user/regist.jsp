<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/regist.css"/>"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form id="registForm" class="form-signin" role="form" action="<c:url value="/permission/user/registCheck"/>"
          method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="loginacct_input" name="loginacct" placeholder="请输入登录账号"
                   autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <div class="errorClass" id="loginacct_span">${errors['loginacct']}</div>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="userpswd_input" name="userpswd" placeholder="请输入登录密码"
                   style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <div class="errorClass" id="userpswd_span">${errors['userpswd']}</div>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="email_input" name="email" placeholder="请输入邮箱地址"
                   style="margin-top:10px;">
            <span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
            <div class="errorClass" id="email_span">${errors['email']}</div>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control">
                <option>会员</option>
                <option>管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="<c:url value="/login"/>">我有账号</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" id="submitBtn"> 注册</a>
    </form>
</div>
<script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/messages_zh.min.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/regist.js'/>"></script>
</body>
</html>
