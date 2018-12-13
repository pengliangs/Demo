package com.pl.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取以com.pl开头的配置项
 * author pengliang  2018-05-20 12:46
 */
@Data
@ConfigurationProperties(prefix = "com.pl.security")
public class SecurityProperties {

    private WebProperties webProperties = new WebProperties();
}
