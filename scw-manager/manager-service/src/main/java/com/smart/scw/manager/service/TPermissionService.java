package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TPermission;

import java.util.List;

public interface TPermissionService {

    //获取到所有菜单,然后进行组装
    List<TPermission> getAllMenus();

    List<TPermission> getAll();

    List<TPermission> getRolePermission(Integer rid);

}
