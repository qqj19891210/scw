package com.smart.scw.manager.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.smart.scw.manager.exception.CustomException.DeployFailException;
import com.smart.scw.manager.exception.CustomException.FileUploadException;
import com.smart.scw.manager.exception.CustomException.ShowProcessImgException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        ModelAndView mv = new ModelAndView();
        /*	使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常	*/
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> errors = new HashMap<>();
        if (e instanceof UnknownAccountException) {
            errors.put("loginacct", "账号不存在");
        } else if (e instanceof IncorrectCredentialsException) {
            errors.put("userpswd", "密码错误");
        } else if (e instanceof FileUploadException) {
            errors.put("fileUpdate", "文件上传失败");
        } else if (e instanceof DeployFailException) {
            if (e.getMessage().equals("上传文件不符合格式")) {
                errors.put("deployFail", e.getMessage());
            } else {
                errors.put("deployFail", "文件部署失败");
            }
        } else if (e instanceof ShowProcessImgException) {
            errors.put("showProcessImgFail", "文件部署图片显示失败");
        } else {
            errors.put("error", "未知错误");
        }
        view.setAttributesMap(errors);
        mv.setView(view);
        return mv;
    }

}
