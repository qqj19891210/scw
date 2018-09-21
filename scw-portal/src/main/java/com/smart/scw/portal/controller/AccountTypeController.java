package com.smart.scw.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart.project.HttpClientUtil;
import com.smart.scw.manager.bean.TAccountType;
import com.smart.scw.portal.bean.RestApiServer;
import com.smart.scw.portal.bean.ScwReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/accountType")
public class AccountTypeController {

    @Autowired
    private RestApiServer server;

    @RequestMapping("/show")
    @ResponseBody
    public ScwReturn<List<TAccountType>> showAccountType(){
        String url=server.getRestApiURL()+"/scw-restapi/accountType/show";
        String response = HttpClientUtil.httpPostRequest(url);
        JSONObject jsonObject = JSON.parseObject(response);
        Object msg = jsonObject.get("msg");
        ScwReturn<List<TAccountType>> scwReturn=JSON.parseObject(String.valueOf(msg),ScwReturn.class);
        return scwReturn;
    }

}
