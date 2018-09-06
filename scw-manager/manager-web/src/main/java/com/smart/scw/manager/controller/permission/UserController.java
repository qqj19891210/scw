package com.smart.scw.manager.controller.permission;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.constant.Constants;
import com.smart.scw.manager.service.UserService;
import com.smart.scw.manager.validationGroup.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/permission/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

    @RequestMapping("/regist")
    public String regist() {
        return "user/regist";
    }

    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * pageNumber默认显示第几页
     * pageSize每页显示多少用户
     * 权限管理/用户维护列表页面显示
     */
    @RequestMapping("/list")
    public ModelAndView users(@RequestParam(defaultValue = "1") Integer pageNumber,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(value = "searchParam", defaultValue = "") String search) {
        ModelAndView modelAndView = new ModelAndView();
        //使用分页展示所有用户
        PageHelper.startPage(pageNumber, pageSize);
        //查出所有用户(按条件查询)
        List<TUser> userList = userService.getAllByCondition(search);
        //去页面显示的数据
        PageInfo<TUser> pageInfo = new PageInfo<>(userList);
        modelAndView.setViewName("manager/permission/user");
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("searchParam", search);
        return modelAndView;
    }

    @RequestMapping("/isLoginacctExist")
    @ResponseBody
    public String isLoginacctExist(@RequestBody String loginacct) {
        boolean result = userService.isLoginacctExist(loginacct);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/isEmailExist")
    @ResponseBody
    public String isEmailExist(@RequestBody String email) {
        boolean result = userService.isEmailExist(email);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/registCheck")
    public String registCheck(@Validated(value = {UserGroup.class}) TUser user,
                              BindingResult bindingResult, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            result = userService.check(bindingResult, result);
            session.setAttribute("errors", result);
            return "redirect:/permission/user/regist";
        }
        userService.addRegist(user);
        session.setAttribute(Constants.LOGIN_USER, user);
        return "redirect:/permission/main";
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public ModelAndView loginCheck(@Validated(value = {UserGroup.class}) @RequestBody TUser user,
                                   BindingResult bindingResult, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            result = userService.check(bindingResult, result);
            fastJsonJsonView.setAttributesMap(result);
            modelAndView.setView(fastJsonJsonView);
            return modelAndView;
        }
        boolean isAuthenticated = userService.isAuthenticated(user);
        if (isAuthenticated) {
            result.put("success", "/scw/permission/main");
            user = userService.findTUserByLoginacct(user.getLoginacct());
            session.setAttribute(Constants.LOGIN_USER, user);
        }
        fastJsonJsonView.setAttributesMap(result);
        modelAndView.setView(fastJsonJsonView);
        return modelAndView;
    }

    @RequestMapping("/del")
    public String userDelete(@RequestParam(value = "ids", defaultValue = "") String ids) {
        if (!ids.trim().equals("")) {
            userService.deleteBatchOrSingle(ids);
        }
        return "redirect:/permission/user/list";
    }

}
