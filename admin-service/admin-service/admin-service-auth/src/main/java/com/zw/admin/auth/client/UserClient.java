package com.zw.admin.auth.client;

import com.zw.admin.framework.common.constant.ServiceNameConstants;
import com.zw.admin.framework.common.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用接口
 * @author: ZhouWei
 * @create: 2021-01
 **/
@FeignClient(ServiceNameConstants.SYSTEM_SERVICE)
public interface UserClient {

    /**
     * 获取用户详细信息
     * @param username 用户名
     * @return 用户详细信息
     **/
    @GetMapping("/user/getInfo/{username}")
    public ResultData getInfo(@PathVariable("username") String username);

}
