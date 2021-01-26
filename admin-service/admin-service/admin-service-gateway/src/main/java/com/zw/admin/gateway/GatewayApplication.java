package com.zw.admin.gateway;

import com.zw.admin.framework.api.service.RemoteLogService;
import com.zw.admin.framework.core.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关服务
 * @author: ZhouWei
 * @create: 2021-01
 **/
@EnableRyFeignClients
@EnableFeignClients(clients = RemoteLogService.class)
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.zw.admin"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

}
