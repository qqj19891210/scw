package com.smart.scw.manager.controller.manager;

import com.smart.scw.manager.bean.TAccountType;
import com.smart.scw.manager.bean.TAccountTypeCert;
import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.service.AccountTypeCertService;
import com.smart.scw.manager.service.AccountTypeService;
import com.smart.scw.manager.service.CertService;
import com.smart.scw.manager.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/serviceCtrl/type")
public class TypeController {

    @Autowired
    private CertService certService;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private AccountTypeCertService accountTypeCertService;

    @Autowired
    private TypeService typeService;

    @RequestMapping("/ctrl")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        // 1先从数据库中查出横向表格的数据
        List<TAccountType> accountTypes = accountTypeService.getAllAccountType();
        modelAndView.addObject("accountTypes", accountTypes);
        // 2 在查出纵向要显示的标题
        List<TCert> certs = certService.getAllCert();
        modelAndView.addObject("certs", certs);
        /**
         * 3 横纵坐标状态,做一个二维矩阵
         * 一次数据库查询,组装一个二维矩阵
         * 查出账户和资质的对应关系
         */
        List<TAccountTypeCert> accountTypeCerts = accountTypeCertService.getAll();
        Boolean[][] relations = new Boolean[certs.size()][accountTypes.size()];
        relations=typeService.getMatrix(relations,accountTypes,certs,accountTypeCerts);
        modelAndView.addObject("relations",relations);
        modelAndView.setViewName("manager/servicemanager/type");
        return modelAndView;
    }

}
