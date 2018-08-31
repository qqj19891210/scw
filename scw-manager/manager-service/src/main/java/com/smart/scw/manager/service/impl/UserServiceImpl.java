package com.smart.scw.manager.service.impl;

import com.smart.project.MyStringUtils;
import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.dao.TUserMapper;
import com.smart.scw.manager.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public boolean isLoginacctExist(String loginacct) {
        return tUserMapper.findTUserByLoginacct(loginacct) == 1;
    }

    @Override
    public boolean isEmailExist(String email) {
        return tUserMapper.findTuserByEmail(email) == 1;
    }

    @Override
    public Map<String, Object> registCheck(BindingResult bindingResult, Map<String, Object> map) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }

    @Override
    public boolean addRegist(TUser user) {
        String password=user.getUserpswd();
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        SimpleHash simpleHash=new SimpleHash("MD5",password,salt,1);
        password=simpleHash.toString();
        user.setUserpswd(password);
        user.setSalt(salt);
        //设置昵称和创建时间
        //昵称默认是loginacct
        user.setUsername(user.getLoginacct());
        //创建时间默认是String类型
        user.setCreatetime(MyStringUtils.formatDate(new Date()));
        int i=0;
        try{
            i=tUserMapper.insertTUser(user);
        }catch(Exception e){
            //添加失败的原因就是用户重复
            return false;
        }
        return i==1;
    }

}
