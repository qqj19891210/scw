package com.smart.scw.restapi.controller.cert;

import com.smart.scw.manager.bean.TCert;
import com.smart.scw.restapi.bean.ScwReturn;
import com.smart.scw.restapi.exception.CustomException.GetCertsFailException;
import com.smart.scw.restapi.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cert")
public class CertController {

    @Autowired
    private CertService certService;

    @RequestMapping("/getCert")
    public Map<String, Object> getCertByAccountTypeId(@RequestParam("atId") String accountTypeId) throws GetCertsFailException {
        Map<String, Object> map = new HashMap<>();
        List<TCert> certs = certService.getCertsByAccountTypeId(Integer.parseInt(accountTypeId));
        if (certs != null && certs.size() > 0) {
            ScwReturn<List<TCert>> scwReturn = ScwReturn.success("资质获取成功", certs, null);
            map.put("msg", scwReturn);
            return map;
        }
        throw new GetCertsFailException("资质获取失败");
    }

}
