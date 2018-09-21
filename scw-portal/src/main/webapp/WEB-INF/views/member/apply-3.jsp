<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form role="form" class="submitBtn" style="margin-top:20px;">
    <div class="form-group">
        <label for="exampleInputEmail1">验证码</label>
        <input type="text" name="code" class="form-control" id="exampleInputEmail1" placeholder="请输入你邮箱中接收到的验证码">
    </div>
    <button type="button" onclick="javascript:;" class="btn btn-primary unuse_btn">重新发送验证码</button>
    <button type="button" url="/scw-portal/auth/success" class="btn btn-success unuse_btn">
        申请完成
    </button>
    <button class="btn btn-default unuse_btn" url="/scw-portal/auth/apply-3" style="display: none"></button>
</form>



