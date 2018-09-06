package com.smart.scw.manager.controller.permission;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smart.scw.manager.bean.TRole;
import com.smart.scw.manager.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/permission/role")
public class RoleController {

    @Autowired
    private TRoleService tRoleService;

    @RequestMapping("/toAssignRolePage")
    public ModelAndView toAssignRolePage(@RequestParam("id") Integer userId) {
        ModelAndView modelAndView = new ModelAndView();
        //1 查出所有权限
        List<TRole> roles = tRoleService.getAllRoles();
        //2 查出当前用户拥有的权限
        List<TRole> userRoles = tRoleService.getUserRoles(userId);
        //3 用户未分配的权限
        /**
         * 先将所有权限的list复制一份(注意初始长度)
         * 然后取两个集合的差集即可
         */
        List<TRole> unroles = new LinkedList<>(Arrays.asList(new TRole[roles.size()]));
        unroles = tRoleService.unRoles(roles, unroles, userRoles);
        modelAndView.setViewName("manager/permission/assignRole");
        modelAndView.addObject("unroles", unroles);
        modelAndView.addObject("userRoles", userRoles);
        return modelAndView;
    }

    @RequestMapping("/list") //角色列表页面
    public ModelAndView toRoleList(@RequestParam(defaultValue = "1") Integer pageNumber,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "searchParam",defaultValue = "") String searchParam) {
        ModelAndView modelAndView=new ModelAndView();
        PageHelper.startPage(pageNumber,pageSize);
        List<TRole> roles=tRoleService.getAllByCondition(searchParam);
        PageInfo<TRole> pageInfo=new PageInfo<>(roles);
        modelAndView.setViewName("manager/permission/role");
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("searchParam",searchParam);
        return modelAndView ;
    }

}
