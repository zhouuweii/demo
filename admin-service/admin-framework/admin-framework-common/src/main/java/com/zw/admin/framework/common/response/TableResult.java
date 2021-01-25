package com.zw.admin.framework.common.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 表格数据响应结果集
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class TableResult<T> implements Serializable {

    private long total;

    private List<T> rows;

    public TableResult() {
    }

    public TableResult(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }
}
