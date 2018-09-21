package com.smart.scw.restapi.service.impl;

import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.bean.TMemberCert;
import com.smart.scw.manager.dao.TCertMapper;
import com.smart.scw.restapi.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertServiceImpl implements CertService {

    @Autowired
    private TCertMapper certMapper;

    @Override
    public List<TCert> getCertsByAccountTypeId(Integer accountTypeId) {
        List<TCert> certs = certMapper.selectCertByAccountTypeId(accountTypeId);
        return certs;
    }


}
