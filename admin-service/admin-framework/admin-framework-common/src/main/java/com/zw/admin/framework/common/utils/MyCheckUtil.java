package com.zw.admin.framework.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 校验
 * @author: ZhouWei
 * @create: 2021-01
 **/
public class MyCheckUtil {

    /**
     * 判断对象中属性值是否全为空
     * @param object
     * @return boolean
     **/
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
