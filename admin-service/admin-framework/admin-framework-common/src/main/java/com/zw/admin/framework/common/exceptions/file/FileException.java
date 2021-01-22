package com.zw.admin.framework.common.exceptions.file;

import com.zw.admin.framework.common.exceptions.BaseException;

/**
 * 文件信息异常类
 * @author: ZhouWei
 * @create: 2021-01
 */
public class FileException extends BaseException {

    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
