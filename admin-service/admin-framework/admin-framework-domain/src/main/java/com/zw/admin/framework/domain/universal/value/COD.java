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
public class COD implements Serializable {

    private static final long serialVersionUID = 1L;
    private double lat;
    private double lon;

    public COD() {
    }

    public COD(double latitude, double longitude) {
        super();
        this.lat = latitude;
        this.lon = longitude;
    }

}
