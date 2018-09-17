package com.smart.scw.restapi.service.impl;

import com.smart.scw.manager.bean.TMember;
import com.smart.scw.manager.dao.TMemberMapper;
import com.smart.scw.restapi.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private TMemberMapper memberMapper;

    @Override
    public TMember addRegist(TMember tMember) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        String password = tMember.getUserpswd();
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1);
        password = simpleHash.toString();
        tMember.setUserpswd(password);
        tMember.setSalt(salt);
        tMember.setUsername(tMember.getLoginacct());
        //实名认证状态:0代表未实名认证,1代表实名认证
        tMember.setAuthstatus("0");
        //真实姓名:实名认证时保存
        tMember.setRealname("未实名");
        //用户类型(暂时不用) 0 普通会员 1 月费会员 2 年费会员
        tMember.setUsertype("0");
        //身份证号,账户类型(实名认证时做的)
        //账户类型,直接保存账户全名
        //保存进数据库
        int i = memberMapper.insert(tMember);
        //刚才就是按照这个对象给数据库插入值,希望获取到数据库分配的自增主键
        return tMember;
    }

    @Override
    public boolean isEmailExist(String email) {
        return memberMapper.selectByEmail(email)>0;
    }

    @Override
    public boolean isLoginacctExist(String loginacct) {
        return memberMapper.selectByLoginacct(loginacct)>0;
    }

    @Override
    public TMember findTMemberByLoginacct(String loginacct) {
        return memberMapper.selectTMemberByLoginacct(loginacct);
    }

    @Override
    public boolean isAuthenticated(TMember member) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(member.getLoginacct(), member.getUserpswd());
        subject.login(usernamePasswordToken);
        return subject.isAuthenticated();
    }

}
