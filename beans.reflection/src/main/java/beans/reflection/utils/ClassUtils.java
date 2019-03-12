package beans.reflection.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ClassUtils {

    private ClassUtils() {
        // helper
    }

    /**
     *
     * @param clazz
     * @return all declared fields
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        do {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return fields;
    }

    public static Constructor<?> findNoArgumentConstructorForInnerClass(
            Class<?> enclosingClass, Class<?> innerClass) {
        for (Constructor<?> constructor : innerClass.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == 1
                    && constructor.getParameterTypes()[0]
                    .equals(enclosingClass)) {
                return constructor;
            }
        }
        return null;
    }

    /**
     *
     * @param clazz
     * @return true if clazz is public, false otherwise
     */
    public static boolean isPublic(Class<?> clazz) {
        return Modifier.isPublic(clazz.getModifiers());
    }

    /**
     * @param clazz
     * @return true if this clazz has a public no argument constructor
     */
    public static boolean hasPublicNoArgumentConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (Modifier.isPublic(constructor.getModifiers())
                    && constructor.getParameterTypes().length == 0) {
                return true;
            }
        }
        return false;
    }

}
