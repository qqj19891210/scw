package com.smart.scw.restapi.service;

import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.bean.TMemberCert;

import java.util.List;

public interface CertService {

    //传入账户类型id
    List<TCert> getCertsByAccountTypeId(Integer accountTypeId);

}
