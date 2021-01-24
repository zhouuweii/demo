package com.zw.admin.gateway;


import com.alibaba.fastjson.JSONObject;
import com.zw.admin.framework.domain.model.LoginUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *
 * @author: ZhouWei
 * @create: 2021-01
 **/
@SpringBootTest(classes = GatewayApplication.class)
@RunWith(SpringRunner.class)
public class TempTest {

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> sops;

    @Test
    public void test()  {
        String userStr = sops.get("user_token:614cd674-2288-475e-8115-6da8ff2a0e2d");

        LoginUser student = JSONObject.parseObject(userStr,LoginUser.class);
        System.out.println(student);


    }
}
