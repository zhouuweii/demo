package com.zw.admin.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @description: 资源服务授权配置，访问此服务必须提供令牌
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String PUBLIC_KEY = "publicKey.txt";

    /**
     * 定义JwtTokenStore，使用jwt令牌
     * @param jwtAccessTokenConverter
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     **/
//    @Bean
//    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }
    /**
     * 定义JJwtAccessTokenConverter，使用jwt令牌
     * @param
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     **/
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        String publicKey = getPubKey();
//        converter.setVerifierKey(publicKey);
//        //不设置这个会出现 Cannot convert access token to JSON
//        converter.setVerifier(new RsaVerifier(publicKey));
//        return converter;
//    }
    /**
     * 获取非对称加密公钥 Key
     * @return java.lang.String 公钥 Key
     **/
//    private String getPubKey() {
//        Resource resource = new ClassPathResource(PUBLIC_KEY);
//        try {
//            InputStreamReader inputStreamReader = new
//                    InputStreamReader(resource.getInputStream());
//            BufferedReader br = new BufferedReader(inputStreamReader);
//            return br.lines().collect(Collectors.joining("\n"));
//        } catch (IOException ioe) {
//            return null;
//        }
//    }
    /**
     * Http安全配置，对每个到达系统的http请求链接进行校验
     * @param http
     * @return void
     **/
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //所有请求必须认证通过
        http.authorizeRequests()
                //下边的路径放行
                .antMatchers("/**").permitAll()
//                .antMatchers("/v2/api‐docs", "/swagger‐resources/configuration/ui",
//                        "/swagger‐resources","/swagger‐resources/configuration/security",
//                        "/swagger‐ui.html","/webjars/**",
//                        "/system/user/getUserExtend","/system/user/getSmsCode/{phone}",
//                        "/system/user/register/{code}"
//                        ).permitAll()
                .anyRequest().authenticated();

    }
}
