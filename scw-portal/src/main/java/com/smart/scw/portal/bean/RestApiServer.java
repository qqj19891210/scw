package com.smart.scw.portal.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestApiServer {

    @Value("${restapi.server.ip}")
    private String restapiServer;

    @Value("${restapi.server.port}")
    private String restapiPort;

    public String getRestApiURL() {
        return "http://" + restapiServer + ":" + restapiPort;
    }

}
