package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TMemberCert;
import com.smart.scw.manager.dao.TMemberCertMapper;
import com.smart.scw.manager.service.MemberCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberCertServiceImpl implements MemberCertService {

    @Autowired
    private TMemberCertMapper memberCertMapper;

    @Override
    public List<TMemberCert> getCertsByTicket(String processInstanceId) {
        return memberCertMapper.getCertsByTicket(processInstanceId);
    }

}
