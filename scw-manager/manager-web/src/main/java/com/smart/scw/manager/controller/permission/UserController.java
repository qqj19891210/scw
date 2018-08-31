package com.smart.scw.manager.controller.permission;

import com.alibaba.fastjson.JSON;
import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.service.UserService;
import com.smart.scw.manager.validationGroup.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/permission/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public String regist() {
        return "user/regist";
    }

    @RequestMapping("/main")
    public String main() {
        return "manager/main";
    }

    @RequestMapping("/isLoginacctExist")
    @ResponseBody
    public String isLoginacctExist(@RequestBody String loginacct){
        boolean result=userService.isLoginacctExist(loginacct);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/isEmailExist")
    @ResponseBody
    public String isEmailExist(@RequestBody String email){
        boolean result=userService.isEmailExist(email);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/registCheck")
    public ModelAndView registCheck(@Validated(value = {UserGroup.class}) TUser user, BindingResult bindingResult){
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> result=new HashMap<>();
        if(bindingResult.hasErrors()){
            result=userService.registCheck(bindingResult,result);
            modelAndView.setViewName("user/regist");
            modelAndView.addObject("errors",result);
            return modelAndView;
        }
        userService.addRegist(user);
        return null;
    }

}
