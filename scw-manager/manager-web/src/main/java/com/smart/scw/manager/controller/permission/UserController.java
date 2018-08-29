package com.smart.scw.manager.controller.permission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission/user")
public class UserController {

    @RequestMapping("/regist")
    public String regist() {
        return "user/regist";
    }

    @RequestMapping("/main")
    public String main() {
        return "manager/main";
    }

}
