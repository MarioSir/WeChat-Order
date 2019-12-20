package com.han.order.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "refreshscopeconfig")
@RefreshScope
public class RefreshScopeConfig {
    private int id;
    private String name;
}
