package beans.reflection.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

final public class SettersUtils {

    private static final String SETTERS_PREFIX = "set";

    private SettersUtils() {
        // utility
    }

    public static Map<String, Method> getSetters(Class<?> clazz) {
        Map<String, Method> result = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            if (isSetter(method)) {
                result.put(extractSetterPropertyName(method), method);
            }
        }
        return result;
    }

    private static boolean isSetter(Method method) {

        if (!method.getName().startsWith(SETTERS_PREFIX)) {
            return false;
        }

        if (!method.getReturnType().equals(void.class)) {
            return false;
        }

        if (method.getParameterTypes().length != 1) {
            return false;
        }

        int modifiers = method.getModifiers();
        if (Modifier.isStatic(modifiers)) {
            return false;
        }

        if (!Modifier.isPublic(modifiers)) {
            return false;
        }

        if (method.getParameterTypes()[0].equals(Void.class)) {
            return false;
        }

        if (method.isVarArgs()) {
            return false;
        }

        return true;
    }

    public static String extractSetterPropertyName(Method method) {

        String name = method.getName();
        if (name.startsWith(SETTERS_PREFIX)) {
            return name.substring(SETTERS_PREFIX.length());
        }

        throw new RuntimeException("Setter name must start with '"
                + SETTERS_PREFIX + "'");
    }

}
