package com.zw.admin.framework.domain.model.graph;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @description: 关系，对应Neo4J返回格式
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class Relation {
    /**编号*/
    private Long id;
    /**开始节点id*/
    private Long start;
    /**结束节点类型*/
    private Long end;
    /**关系*/
    private String type;
    /**关系属性信息*/
    private Map<String, Object> property;

    public Relation() {
    }

    public Relation(Long id, Long start, Long end, String type, Map<String, Object> property) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.type = type;
        this.property = property;
    }
}
