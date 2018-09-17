package com.smart.scw.restapi.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.smart.scw.restapi.bean.ScwReturn;
import com.smart.scw.restapi.exception.CustomException.EmailIsExistException;
import com.smart.scw.restapi.exception.CustomException.LoginacctIsExistException;
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
        Map<String, Object> msg = new HashMap<>();
        if (e instanceof LoginacctIsExistException) {
            errors.put("error", "用户名已存在");
        } else if (e instanceof EmailIsExistException) {
            errors.put("error", "邮箱已经存在");
        } else if (e instanceof UnknownAccountException) {
            errors.put("error", "账号错误");
        } else if (e instanceof IncorrectCredentialsException) {
            errors.put("error", "密码错误");
        }
        ScwReturn<Object> fail = ScwReturn.fail("用户注册失败", null, errors);
        msg.put("msg", fail);
        view.setAttributesMap(msg);
        mv.setView(view);
        return mv;
    }

}
