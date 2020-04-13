package com.sncfc.baseframework.pageplugin.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库方言枚举
 *
 * @author zxy
 */
public enum Dialect {

    /*** Oracle */
    ORACLE("oracle"),
    /*** MySQL */
    MYSQL("mysql");

    private final String value;

    private static final Map<String, Dialect> MAPPING = new HashMap<>();

    static {
        for (Dialect e : Dialect.values()) {
            MAPPING.put(e.value, e);
        }
    }

    Dialect(String value) {
        this.value = value;
    }

    public static Dialect getEnum(String value) {
        return MAPPING.get(value.toLowerCase());
    }
}
