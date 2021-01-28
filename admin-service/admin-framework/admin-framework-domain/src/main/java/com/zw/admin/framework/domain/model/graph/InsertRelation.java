package com.zw.admin.framework.domain.model.graph;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @description: 新增关系
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class InsertRelation {
    /**编号*/
    private Long id;
    /**开始节点id*/
    private String starEid;
    /**结束节点类型*/
    private String endEid;
    /**关系*/
    private String relation;
    /**关系属性信息*/
    private Map<String, Object> property;

    public InsertRelation() {
    }

    public InsertRelation(long id) {
    }

}
