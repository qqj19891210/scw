package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TUser;

public interface TUserMapper {

    int findTUserByLoginacct(String loginacct);

    int findTuserByEmail(String email);

    int insertTUser(TUser user);

    TUser selectTUserByLoginacct(String loginacct);

}