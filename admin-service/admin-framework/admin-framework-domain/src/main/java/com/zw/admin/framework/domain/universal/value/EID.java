package com.zw.admin.framework.domain.universal.value;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Data
@ToString
public class EID implements Serializable {

    private String label;
    private String eid;

    public EID() {
    }

    public EID(String label, String eid) {
        this.label = label;
        this.eid = eid;
    }

}
