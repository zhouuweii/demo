package com.zw.admin.framework.domain.universal;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 属性储存类型
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Data
@ToString
public class Snak implements Serializable {

    public static enum Type {
        /**字符串*/        STR,
        /**数值*/             NUM,
        /**多语言文本*/      MTXT,
        /**字符串*/          AMT,
        /**时间*/            TIME,
        /**实体表示*/        EID,
        /**地理坐标*/        COD,
        /**文本*/          TEXT
    }

    private Type tp;
    private Object val;
    private String prop;
    private Gene src;

    public Snak() {}

    public Snak(Object value) {
        this.val = value;
    }

    public Snak(Type type, Object value) {
        this.tp = type;
        this.val = value;
    }

}
