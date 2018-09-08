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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
                                   BindingResult bindingResult, HttpSession session,
                                   HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            result = userService.check(bindingResult, result);
            fastJsonJsonView.setAttributesMap(result);
            modelAndView.setView(fastJsonJsonView);
            return modelAndView;
        }
        boolean flag = user.isRememberMe();
        boolean isAuthenticated = userService.isAuthenticated(user);
        if (isAuthenticated) {
            result.put("success", "/scw/permission/main");
            user = userService.findTUserByLoginacct(user.getLoginacct());
            session.setAttribute(Constants.LOGIN_USER, user);
            if (flag) {
                Cookie cookie = new Cookie("JSESSIONID", session.getId());
                //一周内这个cookie都存在
                cookie.setMaxAge(3600 * 24 * 7);
                //springmvc处于安全考虑,只能设置当前路径的cookie
                cookie.setPath(session.getServletContext().getContextPath());
                response.addCookie(cookie);
            }
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

    //忘记密码
    @RequestMapping("/forgetPsw")
    public String forgetPsw() {
        return "user/forgetPsw";
    }

    @RequestMapping("/sendEmail")
    public ModelAndView sendEmail(@RequestParam("email") String email) {
        System.out.println(email);
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = userService.isSendEmail(email);
        //如果存在 发邮件说成功 ,否则也提示发送成功,但不发送邮件
        //收到邮箱地址,给邮箱发送重置连接
        modelAndView.setViewName("user/success");
        if (flag) {
            modelAndView.addObject("msg", "我们已经为您的邮箱成功发送了一封邮件");
        } else {
            modelAndView.addObject("msg", "我们已经为您的邮箱发送了一封邮件");
        }
        return modelAndView;
    }

    @RequestMapping("/getpwd")
    public String restPassword() {
        //来到输入密码的页面
        return "user/restPassword";
    }

    @RequestMapping("/updatePwd")
    public ModelAndView updatePwd(@RequestParam("password") String password, @RequestParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        //先按照令牌取找到用户
        boolean flag = userService.updatePasswordByUserId(password, token);
        if (flag) {
            modelAndView.addObject("msg", "密码重置成功,请重新登录");
        } else {
            modelAndView.addObject("msg", "密码重置连接失效,请重新造作");
        }
        //返回登录页面即可
        modelAndView.setViewName("user/success");
        return modelAndView;
    }

}
