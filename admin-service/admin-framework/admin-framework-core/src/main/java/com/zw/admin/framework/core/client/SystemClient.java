package com.zw.admin.framework.core.client;

import com.zw.admin.framework.common.constant.ServiceNameConstants;
import com.zw.admin.framework.common.response.R;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.domain.entity.SysOperLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 远程调用接口
 * @author: ZhouWei
 * @create: 2021-01
 **/
@FeignClient(ServiceNameConstants.SYSTEM_SERVICE)
public interface SystemClient {

    /**
     * 保存系统日志
     * @param sysOperLog 日志实体
     * @return 结果
     */
    @PostMapping("/operlog")
    R<Boolean> saveLog(@RequestBody SysOperLog sysOperLog);

    /**
     * 获取用户详细信息
     * @param username 用户名
     * @return 用户详细信息
     **/
    @GetMapping("/user/getInfo/{username}")
    public ResultData getInfo(@PathVariable("username") String username);
}
