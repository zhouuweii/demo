package com.zw.admin.system.mapper;

import com.zw.admin.framework.domain.entity.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Mapper
public interface OperationLogMapper {

    /**
     * 新增操作日志
     * @param operLog 操作日志对象
     * @return 结果
     */
    public int insertOperlog(SysOperLog operLog);
}
