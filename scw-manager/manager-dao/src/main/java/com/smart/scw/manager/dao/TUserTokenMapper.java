package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TUserToken;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserTokenMapper {

    List<TUserToken> selectTUserTokenByUserId(@Param("userid") Integer userid);

    int updateTokenByUserId(@Param("userid") Integer userid,@Param("token") String token);

    int insert(@Param("userid") Integer userid,@Param("token") String token);

    Integer selectUserIdByToken(String token);

}