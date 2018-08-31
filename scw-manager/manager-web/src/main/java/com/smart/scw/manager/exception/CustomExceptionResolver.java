package com.smart.scw.manager.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
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
        Map<String,Object> errors=new HashMap<>();
        if(e instanceof UnknownAccountException){
            errors.put("loginacct","账号不存在");
        } else if(e instanceof IncorrectCredentialsException){
            errors.put("userpswd","密码错误");
        } else {
            errors.put("error","未知错误");
        }
        view.setAttributesMap(errors);
        mv.setView(view);
        return mv;
    }
}
