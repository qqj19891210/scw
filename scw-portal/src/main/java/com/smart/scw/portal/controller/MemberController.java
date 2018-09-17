package com.smart.scw.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.project.HttpClientUtil;
import com.smart.scw.manager.bean.TMember;
import com.smart.scw.portal.bean.ScwReturn;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 帮我们取调用restapi层的接口
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Value("${restapi.server.ip}")
    private String restapiServer;

    @Value("${restapi.server.port}")
    private String restapiPort;

    private String getRestApiURL() {
        return "http://" + restapiServer + ":" + restapiPort;
    }

    @RequestMapping("/regist")
    public String regist() {
        return "member/regist";
    }

    @RequestMapping("/index")
    public String index() {
        return "member/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "member/login";
    }

    //会员中心页面
    @RequestMapping("/main")
    public String toMemberCenter() {
        return "member/member";
    }

    //实名认证页面
    @RequestMapping("/auth")
    public String toAuthPage() {
        return "member/accttype";
    }

    @RequestMapping("/registCheck")
    public String registCheck(TMember member) throws UnsupportedEncodingException {
        //需要利用http工具取模拟发调用的请求
        //httpclient使用java代码来模拟发送请求
        //能收到api调用后产生的json数据
        //应该发送请求进行注册:java代码发送请求
        String url = getRestApiURL() + "/scw-restapi/member/regist";
        //构建请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("loginacct", member.getLoginacct());
        params.put("userpswd", member.getUserpswd());
        params.put("email", member.getEmail());
        //响应内容
        String response = HttpClientUtil.httpPostRequest(url, params);
        //根据json字符串,逆向出之前的对象
        JSONObject object = JSON.parseObject(response);
        Object msg = object.get("msg");
        ScwReturn<TMember> scwReturn = JSON.parseObject(String.valueOf(msg), ScwReturn.class);
        //判断注册成功来到登录页面
        if (scwReturn.getCode() == 1) {
            return "redirect:/member/login";
        } else {
            //注册失败来到注册页面进行回显
            return "redirect:/member/regist";
        }
    }

    @RequestMapping("/isLoginacctExist")
    @ResponseBody
    public String isLoginacctExist(@RequestBody String loginacct) throws UnsupportedEncodingException {
        String url = getRestApiURL() + "/scw-restapi/member/isLoginacctExist";
        Map<String, Object> params = new HashMap<>();
        params.put("loginacct", loginacct);
        String response = HttpClientUtil.httpPostRequest(url, params);
        JSONObject object = JSON.parseObject(response);
        Object msg = object.get("msg");
        ScwReturn<TMember> scwReturn = JSON.parseObject(String.valueOf(msg), ScwReturn.class);
        if (scwReturn.getCode() == 0) {
            return JSON.toJSONString(scwReturn.getExt());
        }
        return null;
    }

    @RequestMapping("/isEmailExist")
    @ResponseBody
    public String isEmailExist(@RequestBody String email) throws UnsupportedEncodingException {
        String url = getRestApiURL() + "/scw-restapi/member/isEmailExist";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        String response = HttpClientUtil.httpPostRequest(url, params);
        JSONObject object = JSON.parseObject(response);
        Object msg = object.get("msg");
        ScwReturn<TMember> scwReturn = JSON.parseObject(String.valueOf(msg), ScwReturn.class);
        if (scwReturn.getCode() == 0) {
            return JSON.toJSONString(scwReturn.getExt());
        }
        return null;
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public ScwReturn<TMember> loginCheck(@RequestBody TMember member) throws UnsupportedEncodingException {
        Map<String, Object> params = new HashMap<>();
        params.put("loginacct", member.getLoginacct());
        params.put("userpswd", member.getUserpswd());
        String response = HttpClientUtil.httpPostRequest(getRestApiURL() + "/scw-restapi/member/login", params);
        JSONObject jsonObject = JSON.parseObject(response);
        Object msg = jsonObject.get("msg");
        ScwReturn<TMember> scwReturn = JSON.parseObject(String.valueOf(msg), ScwReturn.class);
        return scwReturn;
    }

}
