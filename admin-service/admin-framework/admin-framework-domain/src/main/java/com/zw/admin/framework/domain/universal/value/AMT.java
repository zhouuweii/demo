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
public class AMT implements Serializable {

    private static final long serialVersionUID = 1L;
    private String amt;
    private String unt;
    private String up;
    private String low;

    public AMT() {
        super();
    }

    public AMT(String amount) {
        super();
        this.amt = amount;
    }

    public AMT(String amount, String unit) {
        super();
        this.amt = amount;
        this.unt = unit;
    }

    public AMT(String amt, String unt, String up, String low) {
        this.amt = amt;
        this.unt = unt;
        this.up = up;
        this.low = low;
    }

}
