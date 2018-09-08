package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {

    int findTUserByLoginacct(String loginacct);

    int findTuserByEmail(String email);

    int insertTUser(TUser user);

    TUser selectTUserByLoginacct(String loginacct);

    List<TUser> selectAll();

    List<TUser> selectAllByCondition(@Param("search") String search);

    int deleteTUserById(@Param("ids") Integer ids);

    //批量删除
    int deleteTuserByIds(List<Integer> ids);

    TUser selectByEmail(String email);

    int updatePasswordByUserId(@Param("userid") Integer userid,@Param("password") String password,
                               @Param("salt") String salt);


}