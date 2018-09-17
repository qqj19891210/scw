package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TAccountType;
import com.smart.scw.manager.bean.TAccountTypeCert;
import com.smart.scw.manager.bean.TCert;

import java.util.List;

public interface TypeService {

    Boolean[][] getMatrix(Boolean[][] relations,
                          List<TAccountType> accountTypes,
                          List<TCert> cert, List<TAccountTypeCert> accountTypeCerts);

}
