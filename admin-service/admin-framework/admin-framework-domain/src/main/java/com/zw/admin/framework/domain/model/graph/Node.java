package com.zw.admin.framework.domain.model.graph;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @description: 节点，对应Neo4J返回格式
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class Node {
    /**编号*/
    private Long id;
    /**类型*/
    private String label;
    /**属性信息*/
    private Map<String, Object> property;

    public Node() {
    }

    public Node(Long id) {
        this.id = id;
    }

    public Node(Long id, String label, Map<String, Object> property) {
        this.id = id;
        this.label = label;
        this.property = property;
    }
}
