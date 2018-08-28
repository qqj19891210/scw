package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TMemberProjectFollow;
import java.util.List;

public interface TMemberProjectFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberProjectFollow record);

    TMemberProjectFollow selectByPrimaryKey(Integer id);

    List<TMemberProjectFollow> selectAll();

    int updateByPrimaryKey(TMemberProjectFollow record);
}