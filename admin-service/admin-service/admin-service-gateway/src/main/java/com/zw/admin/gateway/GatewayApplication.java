package com.zw.admin.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关服务
 * @author: ZhouWei
 * @create: 2021-01
 **/
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.zw.admin"})
@SpringBootApplication()
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

}
