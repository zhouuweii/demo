package com.zw.admin.framework.domain.universal.value;

import com.zw.admin.framework.domain.universal.value.helper.EntityValueHelper;
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
public class TIME implements Serializable {

    private static final long serialVersionUID = 1L;
    private String time;
    private int zone;
    private int prs;
    private String cal;

    public TIME() {
    }

    public TIME(String time) {
        this.time = EntityValueHelper.format(time);
    }

    public TIME(String time, int zone, int prs, String cal) {
        this.time = time;
        this.zone = zone;
        this.prs = prs;
        this.cal = cal;
    }

}
