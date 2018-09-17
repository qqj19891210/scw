package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TAccountType;
import com.smart.scw.manager.dao.TAccountTypeMapper;
import com.smart.scw.manager.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private TAccountTypeMapper tAccountTypeMapper;

    @Override
    public List<TAccountType> getAllAccountType() {
        return tAccountTypeMapper.selectAll();
    }

}
