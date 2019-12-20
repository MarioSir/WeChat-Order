package com.han.order.server.controller;

import com.han.order.server.config.RefreshScopeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshScopeConfigController {
    @Autowired
    private RefreshScopeConfig refreshScopeConfig;
    @GetMapping("getRefreshScopeConfig")
    public String getRefreshScopeConfig(){
        return refreshScopeConfig.getId()+"--------------"+refreshScopeConfig.getName();
    }

}
