package com.smart.scw.manager.controller.permission;

import com.alibaba.fastjson.JSON;
import com.smart.scw.manager.service.TRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission/rolePermission")
public class RolePermissionController {

    @Autowired
    private TRolePermissionService tRolePermissionService;

    /**
     * 更新角色权限:
     * 1 先删除当前角色的权限
     * 2 再新增之前选中的所有权限
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateRolePermission(@RequestParam("pids") String pids,
                                       @RequestParam("rid") Integer rid){
        System.out.println("hehe");
        boolean flag=tRolePermissionService.updateRolePermission(pids,rid);
        System.out.println(flag);
        return JSON.toJSONString(flag);
    }

}
