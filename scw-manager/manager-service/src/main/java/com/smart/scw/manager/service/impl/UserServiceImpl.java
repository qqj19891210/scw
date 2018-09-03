package com.smart.scw.manager.service.impl;

import com.smart.project.MyStringUtils;
import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.dao.TUserMapper;
import com.smart.scw.manager.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

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
    public Map<String, Object> check(BindingResult bindingResult, Map<String, Object> map) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }

    @Override
    public void addRegist(TUser user) {
        String password = user.getUserpswd();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1);
        password = simpleHash.toString();
        user.setUserpswd(password);
        user.setSalt(salt);
        //设置昵称和创建时间
        //昵称默认是loginacct
        user.setUsername(user.getLoginacct());
        //创建时间默认是String类型
        user.setCreatetime(MyStringUtils.formatDate(new Date()));
        tUserMapper.insertTUser(user);
    }

    @Override
    public boolean isAuthenticated(TUser user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getLoginacct(), user.getUserpswd());
        subject.login(usernamePasswordToken);
        return subject.isAuthenticated();
    }

    @Override
    public TUser findTUserByLoginacct(String loginacct) {
        return tUserMapper.selectTUserByLoginacct(loginacct);
    }

    @Override
    public List<TUser> getAll() {
        return tUserMapper.selectAll();
    }

    @Override
    public List<TUser> getAllByCondition(String search) {
        return tUserMapper.selectAllByCondition(search);
    }

    @Override
    public void deleteBatchOrSingle(String ids) {
        if (ids.contains(",")) {
            //进行批量删除
            List<Integer> list=new ArrayList<>();
            String[] split = ids.split(",");
            for(String s:split){
                list.add(Integer.parseInt(s));
            }
            tUserMapper.deleteTuserByIds(list);
        } else {
            //按照id进行删除
            tUserMapper.deleteTUserById(Integer.parseInt(ids));
        }
    }

}
