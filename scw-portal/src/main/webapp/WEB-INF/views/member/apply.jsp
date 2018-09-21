<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="submitBtn" role="form" style="margin-top:20px;">
    <div class="form-group">
        <label for="realname">真实姓名</label>
        <input type="text" class="form-control" id="realname" name="realname" placeholder="请输入真实名称">
    </div>
    <div class="form-group">
        <label for="cardnum">身份证号码</label>
        <input type="text" class="form-control" id="cardnum" name="cardnum" placeholder="请输入身份证号码">
    </div>
    <div class="form-group">
        <label for="phonenum">电话号码</label>
        <input type="text" class="form-control" id="phonenum" name="phonenum" placeholder="请输入电话号码">
    </div>
    <!--来到账户选择页面-->
    <button type="button" url="/scw-portal/member/auth" class="btn btn-default unuse_btn">上一步</button>
    <!--来到资质文件上传页面-->
    <button type="button" url="/scw-portal/auth/apply-1" class="btn btn-success unuse_btn">下一步</button>
    <button class="btn btn-default unuse_btn" url="/scw-portal/auth/apply" style="display: none"></button>
</form>

