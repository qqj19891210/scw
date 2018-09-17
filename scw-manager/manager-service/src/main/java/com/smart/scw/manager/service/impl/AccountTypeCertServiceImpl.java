package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TAccountTypeCert;
import com.smart.scw.manager.dao.TAccountTypeCertMapper;
import com.smart.scw.manager.service.AccountTypeCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeCertServiceImpl implements AccountTypeCertService {

    @Autowired
    private TAccountTypeCertMapper tAccountTypeCertMapper;

    @Override
    public List<TAccountTypeCert> getAll() {
        return tAccountTypeCertMapper.selectAll();
    }

}
