package com.zw.admin.framework.core.service;

import com.zw.admin.framework.api.service.RemoteLogService;
import com.zw.admin.framework.domain.entity.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 * @author: ZhouWei
 * @create: 2021-01
 */
@Service
public class AsyncLogService {

    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 异步保存系统日志记录
     * @param sysOperLog 日志记录
     * @return void
     **/
    @Async
    public void saveSysLog(SysOperLog sysOperLog)
    {
        remoteLogService.saveLog(sysOperLog);
    }
}
