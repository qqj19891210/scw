package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.dao.TUserRoleMapper;
import com.smart.scw.manager.service.TUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TUserRoleServiceImpl implements TUserRoleService {

    @Autowired
    private TUserRoleMapper tUserRoleMapper;

    @Override
    public void addRoles(String ids, Integer userId) {
        List<Integer> idList = new ArrayList<>();
        if (ids.contains(",")) {
            idList = toList(ids, idList);
        } else {
            idList.add(Integer.parseInt(ids));
        }
        if (idList != null && userId != null) {
           tUserRoleMapper.insertRoleByIds(idList,userId);
        }
    }

    @Override
    public void deleteRoles(String ids, Integer userId) {
        List<Integer> idList = new ArrayList<>();
        if (ids.contains(",")) {
            idList = toList(ids, idList);
        } else {
            idList.add(Integer.parseInt(ids));
        }
        if (idList != null && userId != null) {
            tUserRoleMapper.deleteRoleByIds(idList, userId);
        }
    }

    private List<Integer> toList(String ids, List<Integer> list) {
        String[] split = ids.split(",");
        for (String s : split) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }
}

