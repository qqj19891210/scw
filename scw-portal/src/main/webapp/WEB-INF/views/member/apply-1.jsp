<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form role="form" id="certForm" class="submitBtn" style="margin-top:20px;" enctype="multipart/form-data">
    <c:forEach var="cert" items="${certs}">
        <div class="form-group">
            <label>${cert.name}</label>
            <input type="hidden" name="certid" value="${cert.id}"/>
            <input type="file" name="file" class="form-control">
            <br>
            <div class="imgShow">

            </div>
        </div>
    </c:forEach>
    <input id="memberHidden" type="hidden" name="memberid"/>
    <button type="button" url="/scw-portal/auth/apply" class="btn btn-default unuse_btn">上一步</button>
    <!--下一步先要提交文件上传表单,提交成功以后来到下一页-->
    <button form="true" type="button" url="/scw-portal/auth/apply-2" class="btn btn-success unuse_btn">下一步</button>
    <button class="btn btn-default unuse_btn" url="/scw-portal/auth/apply-1" style="display: none"></button>
</form>
<script type="text/javascript" src="<c:url value="/jquery/jquery.session.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/apply-1.js"/>"></script>


