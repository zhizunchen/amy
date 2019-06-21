package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Created by chenhe
 * @Date 2019-06-14 16:22
 * @Description TODO
 */
@ConfigurationProperties(prefix = "connection")
public class ConnectionPropertiesDomain {

    @Setter
    @Getter
    private String userName;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String[] remoteAddress;

    @Setter
    @Getter
    @NestedConfigurationProperty
    private Config config;

    @Override
    public String toString() {
        return "ConnectionPropertiesDomain{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", remoteAddress=" + Arrays.toString(remoteAddress) +
                ", config=" + JSON.toJSON(config) +
                '}';
    }
}
