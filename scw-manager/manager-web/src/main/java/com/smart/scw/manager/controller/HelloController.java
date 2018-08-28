package com.smart.scw.manager.controller;

import com.smart.scw.manager.bean.User;
import com.smart.scw.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public ModelAndView hello(@RequestParam(value = "id",defaultValue = "1") Integer id, ModelAndView modelAndView){
        User user = userService.getUserById(id);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
