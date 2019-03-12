package beans.reflection;

import beans.reflection.exceptions.NoPublicNoArgumentConstructorClassException;
import beans.reflection.utils.ClassUtils;
import beans.reflection.utils.GettersUtils;
import beans.reflection.utils.SettersUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ReflectionUtils {

    private ReflectionUtils() {
        // helper
    }

    public static <T> ResultHolder<T> createInstance(Class<T> clazz) {
        ResultHolder<T> resultHolder = new ResultHolder<>(clazz);
        try {
            resultHolder.instance = clazz.newInstance();
        } catch (Exception e) {
            resultHolder.exception = e;
        }
        return resultHolder;
    }

    /**
     *
     * @param <T>
     * @param enclosingInstance
     * @param clazz
     * @return an instance of the clazz which requires an enclosing other
     * instance
     */
    @SuppressWarnings("unchecked")
    public static <T> ResultHolder<T> createInnerInstance(Object enclosingInstance,
            Class<T> clazz) {
        ResultHolder<T> resultHolder = new ResultHolder<>(clazz);
        try {
            Constructor<T> noArgumentConstructor = ((Constructor<T>) ClassUtils
                    .findNoArgumentConstructorForInnerClass(
                            enclosingInstance.getClass(), clazz));
            if (noArgumentConstructor == null) {
                throw new NoPublicNoArgumentConstructorClassException(clazz);
            }
            resultHolder.instance = noArgumentConstructor
                    .newInstance(enclosingInstance);
        } catch (Exception e) {
            resultHolder.exception = e;
        }
        return resultHolder;
    }

    /**
     *
     * @param source
     * @param target
     * @return match between getters and a setters
     */
    public static Map<Method, Method> matchGettersWithSetters(Class<?> source,
            Class<?> target) {

        Map<String, Method> getters = GettersUtils.getGetters(source);
        Map<String, Method> setters = SettersUtils.getSetters(target);

        Map<Method, Method> result = new HashMap<>(getters.size());

        // match each getter with a setter
        for (Method getter : getters.values()) {

            // get setter matched by name
            Method setter = setters.get(GettersUtils
                    .extractGetterPropertyName(getter));

            // no setter matched by name found ?
            if (setter == null) {
                continue;
            }

            if (matchByType(getter, setter)) {
                result.put(getter, setter);
            } else {
                throw new RuntimeException("incompatible getter " + getter
                        + " matched by property name with setter " + setter);
            }

        }

        return result;
    }

    private static boolean matchByType(Method getter, Method setter) {

        Class<?> getterReturnType = getter.getReturnType();
        Class<?> setterParameterType = setter.getParameterTypes()[0];

        // 1. perfect match by type
        if (setterParameterType.isAssignableFrom(getterReturnType)) {
            return true;
        }

        // 2. match by using primitives and wrappers
        Class<?> wrappedGetterReturnType = getWrapperClass(getterReturnType);
        Class<?> wrappedSetterParameterType = getWrapperClass(setterParameterType);
        return wrappedSetterParameterType
                .isAssignableFrom(wrappedGetterReturnType);

    }

    private static Class<?> getWrapperClass(Class<?> clazz) {
        if (!clazz.isPrimitive()) {
            return clazz;
        }
        if (clazz.equals(boolean.class)) {
            return Boolean.class;
        }
        if (clazz.equals(char.class)) {
            return Character.class;
        }
        if (clazz.equals(byte.class)) {
            return Byte.class;
        }
        if (clazz.equals(short.class)) {
            return Short.class;
        }
        if (clazz.equals(int.class)) {
            return Integer.class;
        }
        if (clazz.equals(long.class)) {
            return Long.class;
        }
        if (clazz.equals(float.class)) {
            return Float.class;
        }
        if (clazz.equals(double.class)) {
            return Double.class;
        }
        if (clazz.equals(void.class)) {
            return Void.class;
        }
        throw new RuntimeException("unknown primitive class " + clazz);
    }

    /**
     *
     * @param source
     * @param target
     * @return true if source equals target using java reflection based on
     * getters, throws {@link RuntimeException} otherwise
     */
    public static boolean equalsReflection(Object source, Object target) {

        Map<String, Method> sourceGetters = GettersUtils.getGetters(source
                .getClass());
        Map<String, Method> targetGetters = GettersUtils.getGetters(target
                .getClass());

        if (!sourceGetters.keySet().containsAll(targetGetters.keySet())
                || !targetGetters.keySet().containsAll(sourceGetters.keySet())) {
            throw new RuntimeException("Getters do not fully match");
        }

        List<String> messages = new ArrayList<>();
        for (String key : sourceGetters.keySet()) {
            Method sourceGetter = sourceGetters.get(key);
            Method targetGetter = targetGetters.get(key);
            Object sourceValue = GettersUtils
                    .invokeGetter(sourceGetter, source);
            Object targetValue = GettersUtils
                    .invokeGetter(targetGetter, target);
            if (sourceValue == null && targetValue != null) {
                messages.add("Null source value retrieved using source getter "
                        + sourceGetter + "matched by not null target value = "
                        + targetValue + " retrieved using target getter "
                        + targetGetter);
            }
            if (!sourceValue.equals(targetValue)) {
                messages.add("Not equal values, source value = " + sourceValue
                        + " retrieved using source getter " + sourceGetter
                        + " different from target value = " + targetValue
                        + " retrieved using target getter " + targetGetter);
            }
        }

        if (!messages.isEmpty()) {
            throw new RuntimeException(messages.toString());
        }

        return true;
    }

}
