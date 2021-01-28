package com.zw.admin.auth;

import com.zw.admin.framework.core.annotation.EnableCustomConfig;
import com.zw.admin.framework.core.annotation.EnableRyFeignClients;
import com.zw.admin.framework.core.client.SystemClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 认证服务
 * @author: ZhouWei
 * @create: 2021-01
 **/
@EnableCustomConfig
@EnableRyFeignClients
@EnableFeignClients(basePackages = {"com.zw.admin"})
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.zw.admin"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
