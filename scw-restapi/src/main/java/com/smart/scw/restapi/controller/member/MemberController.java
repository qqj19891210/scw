package com.smart.scw.restapi.controller.member;

import com.smart.scw.manager.bean.TMember;
import com.smart.scw.restapi.bean.ScwReturn;
import com.smart.scw.restapi.exception.CustomException.EmailIsExistException;
import com.smart.scw.restapi.exception.CustomException.LoginacctIsExistException;
import com.smart.scw.restapi.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 负责产生所有数据
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/regist")
    public Map<String,Object> regist(TMember member) throws Exception {
        //返回自增主键后,如果id不等于空,证明保存成功
        Map<String,Object> result=new HashMap<>();
        TMember tMember = memberService.addRegist(member);
        if (tMember.getId() != null) {
            ScwReturn<TMember> success = ScwReturn.success("用户注册成功", tMember, null);
            result.put("msg",success);
            return result;
        }else{
            ScwReturn<Object> fail = ScwReturn.fail("用户注册失败", null, null);
            result.put("msg",fail);
            return result;
        }
    }

    @RequestMapping("/isLoginacctExist")
    public String isLoginacctExist(String loginacct) throws LoginacctIsExistException {
        boolean result = memberService.isLoginacctExist(loginacct);
        if(result){
            throw new LoginacctIsExistException();
        }
        return "";
    }

    @RequestMapping("/isEmailExist")
    public String isEmailExist(String email) throws EmailIsExistException {
        boolean result = memberService.isEmailExist(email);
        if(result){
            throw new EmailIsExistException();
        }
        return "";
    }

    @RequestMapping("/login")
    public Map<String,Object> login(TMember member){
        boolean result=memberService.isAuthenticated(member);
        Map<String,Object> map=new HashMap<>();
        ScwReturn<TMember> scwReturn=null;
        if(result){
            TMember loginMember=memberService.findTMemberByLoginacct(member.getLoginacct());
            scwReturn=ScwReturn.success("登录成功",loginMember,null);
        }
        map.put("msg",scwReturn);
        return map;
    }

}
