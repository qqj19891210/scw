<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>忘记密码</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/forgetPsw.css"/>">
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
    <form id="forgetPsw" class="form-signin" role="form" method="post"
          action="<c:url value="/permission/user/sendEmail"/>">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 填写注册时的邮箱</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="email_input" name="email" placeholder="请填写注册邮箱"
                   autofocus>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            <div class="errorClass" id="email_span"></div>
        </div>
        <button type="submit" class="btn btn-lg btn-success btn-block" id="logout">确定</button>
    </form>
</div>
<script type="text/javascript" src="<c:url value="/jquery/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.validate.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/jquery/messages_zh.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/forgetPsw.js"/>"></script>
</body>
</html>

