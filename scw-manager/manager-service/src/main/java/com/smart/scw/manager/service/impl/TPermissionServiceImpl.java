package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TPermission;
import com.smart.scw.manager.dao.TPermissionMapper;
import com.smart.scw.manager.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TPermissionServiceImpl implements TPermissionService {

    @Autowired
    private TPermissionMapper tPermissionMapper;

    @Override
    public List<TPermission> getAllMenus() {
        List<TPermission> all = tPermissionMapper.selectAllMenus();
        List<TPermission> parents = new ArrayList<>();
        Map<Integer, TPermission> result = new HashMap<>();
        for (TPermission tPermission : all) {
            result.put(tPermission.getId(), tPermission);
        }
        for (TPermission tPermission : all) {
            if (tPermission.getPid() == 0) {
                parents.add(tPermission);
            } else {
                TPermission parent = result.get(tPermission.getPid());
                List<TPermission> children = parent.getChildren();
                if (children != null) {
                    children.add(tPermission);
                } else {
                    children = new ArrayList<>();
                    children.add(tPermission);
                    parent.setChildren(children);
                }
            }
        }
        return parents;
    }

    @Override
    public List<TPermission> getAll() {
        return tPermissionMapper.selectAllMenus();
    }

    @Override
    public List<TPermission> getRolePermission(Integer rid) {
        return tPermissionMapper.getRolePermission(rid);
    }

}
