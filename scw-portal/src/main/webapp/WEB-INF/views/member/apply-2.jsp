<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form role="form" class="submitBtn" style="margin-top:20px;">
    <div class="form-group">
        <label for="email">邮箱地址</label>
        <input name="email" type="text" class="form-control" id="email" placeholder="请输入用于接收验证码的邮箱地址">
    </div>
    <button type="button" url="/scw-portal/auth/apply-1" class="btn btn-default unuse_btn">上一步</button>
    <button type="button" url="/scw-portal/auth/apply-3" class="btn btn-success unuse_btn">下一步</button>
    <button class="btn btn-default unuse_btn" url="/scw-portal/auth/apply-2" style="display: none"></button>
</form>
<script type="text/javascript" src="<c:url value="/jquery/jquery.session.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/apply-2.js"/>"></script>


