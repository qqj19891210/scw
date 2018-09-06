package com.smart.scw.manager.controller.permission;

import com.smart.scw.manager.bean.TPermission;
import com.smart.scw.manager.constant.Constants;
import com.smart.scw.manager.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private TPermissionService tPermissionService;

    @RequestMapping("/main")
    public String main(HttpSession session) {
        Object object = session.getAttribute(Constants.LOGIN_USER);
        if (object == null) {
            return "redirect:/permission/user/login";
        }
        /**
         * 将查到的菜单放入session中,菜单这些数据没必要每次来到本页
         * 都调用service方法进行查询
         * 放入session中,当前用户这次回话会一直使用,而不用查询数据库
         */
        if (session.getAttribute(Constants.USER_MENUS) == null) {
            List<TPermission> menus = tPermissionService.getAllMenus();
            session.setAttribute(Constants.USER_MENUS, menus);
        }
        return "manager/main";
    }

    //返回所有权限的json数据
    @RequestMapping("/json")
    @ResponseBody
    public List<TPermission> getAllPermission() {
        return tPermissionService.getAll();
    }

    //获取角色对应的权限
    @RequestMapping("/rolePermission/{rid}")
    @ResponseBody
    public List<TPermission> getRolePermission(@PathVariable("rid") Integer rid) {
        return tPermissionService.getRolePermission(rid);
    }

}
