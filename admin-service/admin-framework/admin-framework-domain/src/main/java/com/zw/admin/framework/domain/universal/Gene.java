package com.zw.admin.framework.domain.universal;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 数据基因/出处
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Data
@ToString
public class Gene implements Serializable {

    private String src;
    private String name;
    private String color;
    private String url;
    private Object timestamp;

    public Gene() {
    }

    public Gene(String src, String name, Object timestamp) {
        this.src = src;
        this.name = name;
        this.timestamp = timestamp;
    }

    public Gene(String src, String name, String color, Object timestamp) {
        this.name = name;
        this.color = color;
        this.src = src;
        this.timestamp = timestamp;
    }

}
