package com.smart.scw.manager.controller.manager;

import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.service.CertService;
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

    @RequestMapping("/ctrl")
    public ModelAndView list() {
        ModelAndView modelAndView=new ModelAndView();
        // 1 在查出纵向要显示的标题
        List<TCert> certs = certService.getAllCert();
        modelAndView.addObject("certs",certs);
        // 2 横纵坐标状态
        modelAndView.setViewName("manager/servicemanager/type");
        return modelAndView;
    }

}
