package com.zw.admin.system.service.impl;

import com.zw.admin.framework.domain.entity.SysOperLog;
import com.zw.admin.system.mapper.OperationLogMapper;
import com.zw.admin.system.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 查询系统操作日志集合
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operationLogMapper.selectOperLogList(operLog);
    }

    /**
     * 批量删除系统操作日志
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds) {
        return operationLogMapper.deleteOperLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return operationLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        operationLogMapper.cleanOperLog();
    }
}
