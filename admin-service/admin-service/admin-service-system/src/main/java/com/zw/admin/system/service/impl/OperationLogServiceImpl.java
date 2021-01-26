package com.zw.admin.system.service.impl;

import com.zw.admin.framework.domain.entity.SysOperLog;
import com.zw.admin.system.mapper.OperationLogMapper;
import com.zw.admin.system.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 新增操作日志
     * @param operLog 操作日志对象
     * @return 结果
     */
    @Override
    public int insertOperlog(SysOperLog operLog) {
        return operationLogMapper.insertOperlog(operLog);
    }
}
