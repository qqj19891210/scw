package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.dao.TRolePermissionMapper;
import com.smart.scw.manager.service.TRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TRolePermissionServiceImpl implements TRolePermissionService {

    @Autowired
    private TRolePermissionMapper tRolePermissionMapper;

    @Override
    public boolean updateRolePermission(String pids, Integer rid) {
        //先删除当前角色的所有权限
        tRolePermissionMapper.deletePermissionByRoleId(rid);
        //新增选中的角色的权限
        String[] split = pids.split(",");
        if (split != null && split.length > 0) {
            List<Integer> pidList = new ArrayList<>();
            for (String s : split) {
                pidList.add(Integer.parseInt(s));
            }
            tRolePermissionMapper.insertRoleAndPermission(pidList,rid);
            return true;
        }
        return false;
    }

}
