package com.smart.scw.restapi.service;

import com.smart.scw.manager.bean.TMember;

public interface MemberService {

    /**
     * 会员注册,返回注册好的用户
     * @param tMember
     * @return
     */
    TMember addRegist(TMember tMember);

    boolean isEmailExist(String email);

    boolean isLoginacctExist(String loginacct);

    TMember findTMemberByLoginacct(String loginacct);

    public boolean isAuthenticated(TMember member);
}
