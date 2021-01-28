package com.zw.admin.framework.domain.universal;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 属性储存
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Data
@ToString
public class Claim extends Snak implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date mod;

    private int cfd = 100;

    private HashMap<String, List<Snak>> qlfs;

    private List<Snak> refs;

    public Claim() {
        super();
    }

    public Claim(Object value) {
        super(value);
    }

    public Claim(Type type, Object value) {
        super(type, value);
    }

    public Claim(Snak snak) {
        this(snak.getTp(), snak.getVal());
    }

}
