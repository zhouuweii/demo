package com.zw.admin.system.service;

import com.zw.admin.framework.domain.entity.SysOperLog;

/**
 * 操作日志
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface OperationLogService {

    /**
     * 新增操作日志
     * @param operLog 操作日志对象
     * @return 结果
     */
    public int insertOperlog(SysOperLog operLog);
}
