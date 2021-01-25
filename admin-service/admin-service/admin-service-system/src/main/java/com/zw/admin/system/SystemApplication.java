package com.zw.admin.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统服务
 * @author: ZhouWei
 * @create: 2021-01
 **/
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.zw.admin.system.mapper")
@ComponentScan(basePackages = {"com.zw.admin"})
@SpringBootApplication()
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
