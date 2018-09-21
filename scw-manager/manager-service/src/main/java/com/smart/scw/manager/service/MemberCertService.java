package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TMemberCert;

import java.util.List;

public interface MemberCertService {

    List<TMemberCert> getCertsByTicket(String processInstanceId);

}
