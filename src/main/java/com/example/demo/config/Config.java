package com.example.demo.config;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @Created by chenhe
 * @Date 2019-06-17 11:21
 * @Description NestedConfigurationProperty 引用测试
 */
public class Config {

    private String url;

    private Integer port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
