package com.zw.admin.system.config;

import com.zw.admin.framework.common.constant.GlobalVariable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Swagger配置类
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Configuration
@EnableSwagger2
public class Swagger2Configuration implements WebMvcConfigurer {

    @Value("${server.port}")
    private String ServerPort;

    @Value("${spring.application.name}")
    private String ServerName;

    @Value("${spring.cloud.nacos.discovery.namespace}")
    private String ServerNamespace;

    /**
     * 配置swagger中的docket实例
     * @return springfox.documentation.spring.web.plugins.Docket
     **/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组操作
                .groupName(ServerName)
                .select()
                //指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.zw.admin.system"))
                //过滤的路径
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * 配置ApiInfo的信息
     * @return springfox.documentation.service.ApiInfo
     **/
    private ApiInfo apiInfo() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new ApiInfoBuilder()
                .title(ServerName+"-API-"+isServerNamespace(ServerNamespace))
                .description("更新时间：" + df.format(new Date()))
                .contact(GlobalVariable.AUTHOR)
                .version(ServerName+"-"+isServerNamespace(ServerNamespace)+"-"+GlobalVariable.VERSION)
                .termsOfServiceUrl("http://localhost:"+ServerPort+"/swagger-ui.html  &  http://localhost:"+ServerPort+"/doc.html")
                .build();
    }
    /**
     * Swagger Bootstrap UI 配置
     * @param registry
     * @return void
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    /**
     * @return java.lang.String
     * 显示Swagger2标题来源
     * @param ServerNamespace Nacos命名空间
     **/
    public String isServerNamespace(String ServerNamespace) {
        switch (ServerNamespace) {
            case GlobalVariable.NAMESPACE_DEV:
                return "Dev";
            case GlobalVariable.NAMESPACE_TEST:
                return "Test";
            case GlobalVariable.NAMESPACE_PRE:
                return "PRE";
            case GlobalVariable.NAMESPACE_PROD:
                return "PROD";
            default:
                return "Unknow";
        }
    }
}
