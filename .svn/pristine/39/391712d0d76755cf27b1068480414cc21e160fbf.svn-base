package com.sncfc.baseframework.pageplugin.util;

import java.lang.reflect.Field;

/**
 * ReflectHelpers
 *
 * @author zxy
 */
public class ReflectHelpers {

    private static Field getObjectField(Object obj, String fieldName) throws NoSuchFieldException {
        try {
            return obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException | SecurityException ignore) {
            return obj.getClass().getField(fieldName);
        }
    }

    public static Object getFieldValue(Object obj, String fieldName) throws ReflectiveOperationException {
        final Field field = getObjectField(obj, fieldName);
        if (field.isAccessible()) {
            return field.get(obj);
        }

        field.setAccessible(true);
        final Object value = field.get(obj);
        field.setAccessible(false);

        return value;
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) throws ReflectiveOperationException {
        final Field field = getObjectField(obj, fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }

    public static void copyObjectField(Object originObj, Object targetObj, String fieldName) {
        try {
            if (getObjectField(originObj, fieldName) != null) {
                setFieldValue(targetObj, fieldName, getFieldValue(originObj, fieldName));
            }
        } catch (ReflectiveOperationException ignore) {}
    }
}
