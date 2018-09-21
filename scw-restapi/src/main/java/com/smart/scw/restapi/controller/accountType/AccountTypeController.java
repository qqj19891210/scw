package com.smart.scw.restapi.controller.accountType;

import com.smart.scw.manager.bean.TAccountType;
import com.smart.scw.restapi.bean.ScwReturn;
import com.smart.scw.restapi.exception.CustomException.GetDataFailExcepiton;
import com.smart.scw.restapi.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accountType")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    /**
     * 将账户类型返回并显示
     */
    @RequestMapping("/show")
    public Map<String, Object> show() throws GetDataFailExcepiton {
        Map<String, Object> result = new HashMap<>();
        List<TAccountType> list = accountTypeService.getAll();
        if (list != null) {
            ScwReturn<List<TAccountType>> success = ScwReturn.success("账户类型信息获取成功", list, null);
            result.put("msg", success);
            return result;
        }
        throw new GetDataFailExcepiton("账户类型信息获取失败");
    }

}
