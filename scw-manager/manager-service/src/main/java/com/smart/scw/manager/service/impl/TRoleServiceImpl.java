package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TRole;
import com.smart.scw.manager.dao.TRoleMapper;
import com.smart.scw.manager.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TRoleServiceImpl implements TRoleService {

    @Autowired
    private TRoleMapper tRoleMapper;

    @Override
    public List<TRole> getAllRoles() {
        return tRoleMapper.selectAll();
    }

    @Override
    public List<TRole> getUserRoles(Integer userId) {
        return tRoleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<TRole> unRoles(List<TRole> roles,List<TRole> copy,List<TRole> userRoles) {
        Collections.copy(copy,roles);
        if(userRoles!=null){
            copy.removeAll(userRoles);
        }
        return copy;
    }

    @Override
    public List<TRole> getAllByCondition(String search) {
        return tRoleMapper.selectAllByCondition(search);
    }

}
