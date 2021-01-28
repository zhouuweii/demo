package com.zw.admin.framework.domain.model.graph;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

/**
 * @description: 图形实体
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class Graph {
    /**标签*/
    private Set<String> labels;
    /**节点*/
    private Set<Node> nodes;
    /**关系*/
    private Set<Relation> edges;

    public Graph() {
    }

    public Graph(Set<String> labels, Set<Node> nodes, Set<Relation> edges) {
        this.labels = labels;
        this.nodes = nodes;
        this.edges = edges;
    }
}
