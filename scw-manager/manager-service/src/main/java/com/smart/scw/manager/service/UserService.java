package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TUser;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface UserService {

    boolean isLoginacctExist(String loginacct);

    boolean isEmailExist(String email);

    Map<String,Object> registCheck(BindingResult bindingResult,Map<String,Object> map);

    boolean addRegist(TUser user);

}
