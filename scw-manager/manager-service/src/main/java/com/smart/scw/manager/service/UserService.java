package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TUser;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface UserService {

    boolean isLoginacctExist(String loginacct);

    boolean isEmailExist(String email);

    Map<String, Object> check(BindingResult bindingResult, Map<String, Object> map);

    void addRegist(TUser user);

    boolean isAuthenticated(TUser user);

    TUser findTUserByLoginacct(String loginacct);

    List<TUser> getAll();

    //按条件查询
    List<TUser> getAllByCondition(String search);

}
