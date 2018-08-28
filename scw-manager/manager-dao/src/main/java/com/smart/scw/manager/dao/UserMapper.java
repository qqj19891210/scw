package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.User;
import java.util.List;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

}