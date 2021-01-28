package com.zw.admin.framework.domain.universal.value;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @description:
 * @author: ZhouWei
 * @create: 2020-12
 **/
@Data
@ToString
public class MTXT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Transient
    public static final String UKN = "UKN";
    @Transient
    public static final String en = "en", en_gb = "en-gb", en_us = "en-us", en_ca = "en-ca";
    @Transient
    public static final String zh_cn = "zh-cn", zh = "zh", zh_hans = "zh-hans", zh_chs = "zh-chs";
    @Transient
    public static final String zh_hant = "zh-hant", zh_cht = "zh-cht", zh_tw = "zh-tw";
    @Transient
    public static final String ru = "ru", ja = "ja", fr = "fr", ar = "ar", es = "es", pt = "pt";

    private String lan;
    @Field(value = "val")
    private String vl;

    public MTXT() {
        this.lan = UKN;
    }

    public MTXT(String value) {
        this.lan = UKN;
        this.vl = value;
    }

    public MTXT(String language, String value) {
        this.lan = language == null ? UKN : language;
        this.vl = value;
    }

}
