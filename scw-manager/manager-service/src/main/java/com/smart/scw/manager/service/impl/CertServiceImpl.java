package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.dao.TCertMapper;
import com.smart.scw.manager.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertServiceImpl implements CertService {

    @Autowired
    private TCertMapper certMapper;

    @Override
    public List<TCert> getAllCert() {
        return certMapper.selectAll();
    }

}
