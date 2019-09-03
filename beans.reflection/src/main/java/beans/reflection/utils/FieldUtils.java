package beans.reflection.utils;

import java.lang.reflect.Field;

final public class FieldUtils {

    private FieldUtils() {
        // helper
    }

    /**
     * set field to value on instance regardless of access rights
     *
     * @param field
     * @param value
     * @param instance
     */
    public static void setFieldValue(Field field, Object value, Object instance) {
        if (field.isAccessible()) {
            set(field, value, instance);
        } else {
            field.setAccessible(true);
            set(field, value, instance);
            field.setAccessible(false);
        }
    }

    /**
     * @param field
     * @param instance
     * @return value for field on instance regardless of access rights
     */
    public static Object getFieldValue(Field field, Object instance) {
        if (field.isAccessible()) {
            return getValue(field, instance);
        }
        Object result;
        field.setAccessible(true);
        result = getValue(field, instance);
        field.setAccessible(false);
        return result;

    }

    private static Object getValue(Field field, Object instance) {
        try {
            return field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException("Could not get field " + field
                    + " value on instance " + instance, e);
        }
    }

    private static void set(Field field, Object value, Object instance) {
        try {
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException("Could not set field " + field
                    + " to value " + value + " on instance " + instance, e);
        }
    }

}
