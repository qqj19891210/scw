package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TAccountType;
import com.smart.scw.manager.bean.TAccountTypeCert;
import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Override
    public Boolean[][] getMatrix(Boolean[][] relations, List<TAccountType> accountTypes,
                                 List<TCert> certs, List<TAccountTypeCert> accountTypeCerts) {
        for (int i = 0; i < relations.length; i++) {
            for (int j = 0; j < relations[i].length; j++) {
                //拿出当前资质
                TCert cert = certs.get(i);
                //拿出账户类型
                TAccountType accountType = accountTypes.get(j);
                //对照是否有这个资质对应的类型
                for (TAccountTypeCert accountTypeCert : accountTypeCerts) {
                    relations[i][j] = accountTypeCert.getCertid() == cert.getId() && accountTypeCert.getAccttypeid() == accountType.getId();
                    //只要确定成功就跳出
                    if (relations[i][j]) {
                        break;
                    }
                }
            }
        }
        return relations;
    }

}
