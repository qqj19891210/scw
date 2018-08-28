package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.User;
import com.smart.scw.manager.dao.UserMapper;
import com.smart.scw.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
