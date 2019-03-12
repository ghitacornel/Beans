package beans.reflection.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

final public class GettersUtils {

    private static final String GETTER_PREFIX_IS = "is";
    private static final String GETTER_PREFIX_GET = "get";

    private GettersUtils() {
        // utility
    }

    /**
     *
     * @param clazz
     * @return all getters mapped by property name
     */
    public static Map<String, Method> getGetters(Class<?> clazz) {
        Map<String, Method> result = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            if (isGetter(method)) {
                result.put(extractGetterPropertyName(method), method);
            }
        }
        return result;
    }

    private static boolean isGetter(Method method) {

        if (method.getParameterTypes().length != 0) {
            return false;
        }

        int modifiers = method.getModifiers();
        if (Modifier.isStatic(modifiers)) {
            return false;
        }

        if (!Modifier.isPublic(modifiers)) {
            return false;
        }

        if (!isGetterValidName(method)) {
            return false;
        }

        if (isGetClassMethod(method)) {
            return false;
        }

        if (method.getReturnType().equals(void.class)
                || method.getReturnType().equals(Void.class)) {
            return false;
        }

        return true;
    }

    private static boolean isGetterValidName(Method method) {

        String name = method.getName();

        if (name.startsWith(GETTER_PREFIX_IS)) {
            return method.getReturnType().equals(boolean.class);
        }

        if (name.startsWith(GETTER_PREFIX_GET)) {
            return !method.getReturnType().equals(boolean.class);
        }

        return false;
    }

    private static boolean isGetClassMethod(Method method) {
        return method.getName().equals("getClass")
                && method.getParameterTypes().length == 0
                && method.getReturnType().equals(Class.class);
    }

    public static String extractGetterPropertyName(Method method) {

        String name = method.getName();
        if (name.startsWith(GETTER_PREFIX_GET)) {
            return name.substring(GETTER_PREFIX_GET.length());
        } else if (name.startsWith(GETTER_PREFIX_IS)) {
            return name.substring(GETTER_PREFIX_IS.length());
        }

        throw new RuntimeException("Getter name must start with '"
                + GETTER_PREFIX_GET + "' or '" + GETTER_PREFIX_IS + "'");
    }

    public static Object invokeGetter(Method method, Object instance) {
        try {
            return method.invoke(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
