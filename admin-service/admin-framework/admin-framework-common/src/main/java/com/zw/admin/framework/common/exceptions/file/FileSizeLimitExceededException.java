package com.zw.admin.framework.common.exceptions.file;

/**
 * 文件名大小限制异常类
 * @author: ZhouWei
 * @create: 2021-01
 */
public class FileSizeLimitExceededException extends FileException {

    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }

}
