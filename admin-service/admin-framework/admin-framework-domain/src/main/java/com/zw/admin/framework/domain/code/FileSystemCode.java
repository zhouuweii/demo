package com.zw.admin.framework.domain.code;

import com.google.common.collect.ImmutableMap;
import com.zw.admin.framework.common.response.ResponseMain;
import lombok.ToString;

/**
 * @description: 文件类型数据响应代码
 * @author: ZhouWei
 * @create: 2020-12
 **/
@ToString
public enum FileSystemCode implements ResponseMain {

    //文件类型数据响应代码
    FS_INIT_ERROR(false,25001,"初始化FastDFS失败"),
    FS_UPLOAD_FILE_IS_NULL(false,25002,"上传文件为空！"),
    FS_UPLOAD_FILE_SERVER_FAIL(false,25003,"上传文件服务器失败！"),
    FS_DELETE_FILE_FAIL(false,25004,"文件删除失败！"),
    FS_DELETE_FILE_NOT_EXISTS(false,25006,"删除的文件不存在！"),
    FS_DELETE_FILE_DB_FAIL(false,25007,"删除文件信息失败！"),
    FS_DELETE_FILE_META_ERROR(false,25008,"上传文件的元信息请使用json格式！");
;

    /**操作是否成功*/
    private boolean success;

    /**操作代码*/
    private int code;

    /**提示信息*/
    private String message;

    private FileSystemCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, FileSystemCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, FileSystemCode> builder = ImmutableMap.builder();
        for (FileSystemCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
