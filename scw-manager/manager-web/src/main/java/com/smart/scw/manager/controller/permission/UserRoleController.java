package com.smart.scw.manager.controller.permission;

import com.alibaba.fastjson.JSON;
import com.smart.scw.manager.service.TUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/userRole")
public class UserRoleController {

    @Autowired
    private TUserRoleService tUserRoleService;

    @RequestMapping("/assignRole")
    @ResponseBody
    public String userRole(@RequestParam("rids") String rids,
                           @RequestParam("uid") Integer userId,
                           @RequestParam("opt") String opt){
        if("add".equals(opt)){
            //为某个用户添加一些角色
            tUserRoleService.addRoles(rids,userId);
            return JSON.toJSONString("分配成功");
        } else if("remove".equals(opt)){
            //移除角色
            tUserRoleService.deleteRoles(rids,userId);
            return JSON.toJSONString("移除成功");
        }
        return null;
    }

}
