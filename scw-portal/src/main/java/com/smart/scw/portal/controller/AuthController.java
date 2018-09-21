package com.smart.scw.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.project.HttpClientUtil;
import com.smart.project.MyStringUtils;
import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.bean.TMember;
import com.smart.scw.manager.constant.Constants;
import com.smart.scw.portal.bean.RestApiServer;
import com.smart.scw.portal.bean.ScwReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实名认证的控制器
 */

@Controller
@RequestMapping("/auth")
public class AuthController {

    //认证信息
    private final String AUTH_INFO = "auth_info";

    @Autowired
    private RestApiServer server;

    /**
     * 来到输入基本信息的页面
     *
     * @return
     */
    @RequestMapping("/apply")
    public String toApply() {
        return "member/apply";
    }

    /**
     * 来到选择资质页面,同时也完成了基本信息的录入
     *
     * @return
     */
    @RequestMapping("/apply-1")
    public String toApply1(TMember member, HttpSession session, ModelMap modelMap) throws UnsupportedEncodingException {
        //从session中拿到的对象是一个引用,所以不用更新后再放入session
        TMember auth = (TMember) session.getAttribute(AUTH_INFO);
        if (auth != null && !MyStringUtils.isEmpty(member.getRealname()) &&
                !MyStringUtils.isEmpty(member.getCardnum())
                && !MyStringUtils.isEmpty(member.getPhonenum())) {
            auth.setRealname(member.getRealname());
            auth.setCardnum(member.getCardnum());
            auth.setPhonenum(member.getPhonenum());
            //提交这些数据,发给api层
            String url = server.getRestApiURL() + "/scw-restapi/auth/baseInfo";
            Map<String, Object> map = new HashMap<>();
            //将auth转换为map
            map = JSON.parseObject(JSON.toJSONString(auth), Map.class);
            String response = HttpClientUtil.httpPostRequest(url, map);
            JSONObject object = JSON.parseObject(response);
            Object msg = object.get("msg");
            ScwReturn scwReturn = JSON.parseObject(String.valueOf(msg), ScwReturn.class);
            if (scwReturn.getCode() == 1) {
                //查出用户当前要用的账户类型的所有信息
                String accountTypeId = auth.getAccttypeid().toString();
                Map<String, Object> param = new HashMap<>();
                param.put("atId", accountTypeId);
                String certUrl = server.getRestApiURL() + "/scw-restapi/cert/getCert";
                String response1 = HttpClientUtil.httpPostRequest(certUrl, param);
                JSONObject object1 = JSON.parseObject(response1);
                Object msg1 = object1.get("msg");
                ScwReturn<List<TCert>> certs = JSON.parseObject(String.valueOf(msg1), ScwReturn.class);
                modelMap.addAttribute("certs", certs.getContent());
                //保存基本信息成功来到下一步
                return "member/apply-1";
            } else {
                return "member/apply";
            }
        } else {
            //返回基本信息的收集页面
            return "member/apply";
        }

    }

    /**
     * 来到填写邮箱页面
     *
     * @return
     */
    @RequestMapping("/apply-2")
    public String toApply2() {
        return "member/apply-2";
    }

    @RequestMapping("/apply-3")
    public String toApply3(@RequestParam("email") String email, HttpSession session) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        TMember member = (TMember) session.getAttribute(Constants.LOGIN_MEMBER);
        map.put("id", member.getId());
        map.put("email", email);
        map.put("username", member.getUsername());
        String response = HttpClientUtil.httpPostRequest(server.getRestApiURL() + "/scw-restapi/auth/sendEmail", map);
        JSONObject object = JSON.parseObject(response);
        Object msg = object.get("msg");
        ScwReturn<Object> scwReturn = JSON.parseObject(String.valueOf(msg), ScwReturn.class);
        System.out.println(scwReturn);
        if (scwReturn.getCode() == 1) {
            return "member/apply-3";
        }
        return "member/apply-2";
    }

    /**
     * 申请完成
     *
     * @return
     */
    @RequestMapping("/success")
    public String authSuccess(String code, HttpSession session, ModelMap modelMap) throws UnsupportedEncodingException {
        //收到验证码发给api层
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        TMember member = (TMember) session.getAttribute(Constants.LOGIN_MEMBER);
        map.put("memberid", member.getId());
        String response = HttpClientUtil.httpPostRequest(server.getRestApiURL() + "/scw-restapi/auth/vcode", map);
        JSONObject jsonObject = JSON.parseObject(response);
        Object msg = jsonObject.get("msg");
        ScwReturn<Object> scwReturn = JSON.parseObject(String.valueOf(msg), ScwReturn.class);
        if (scwReturn.getCode() == 1) {
            modelMap.addAttribute("msg", scwReturn.getMsg());
            return "member/success";
        }
        return "member/apply-3";
    }

    //完成账户类型选择,来到输入基本信息页面
    @RequestMapping("/checkType")
    public String finishAccountType() {
        return "redirect:/auth/apply";
    }

    /**
     * 来到认证申请的主页面
     *
     * @return
     */
    @RequestMapping("/authPage")
    public String toAuthPage(@RequestParam("atId") String accountTypeId, HttpSession session) {
        //收集登录用户信息的TMember
        TMember member = new TMember();
        TMember loginMember = (TMember) session.getAttribute(Constants.LOGIN_MEMBER);
        //从session中获取登录用户的id
        member.setId(loginMember.getId());
        member.setAccttypeid(Integer.parseInt(accountTypeId));
        session.setAttribute(AUTH_INFO, member);
        return "member/authPage";
    }

}
