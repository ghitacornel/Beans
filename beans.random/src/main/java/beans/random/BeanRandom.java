package beans.random;

import beans.random.configuration.Configuration;
import beans.reflection.ReflectionUtils;
import beans.reflection.ResultHolder;

final public class BeanRandom {

    private BeanRandom() {
        // helper
    }

    public static <T> T random(Class<T> clazz) {
        return random(clazz, new Configuration());
    }

    public static <T> T random(Class<T> clazz, Configuration configuration) {

        ResultHolder<T> resultHolder = ReflectionUtils.createInstance(clazz);

        if (resultHolder.exception != null) {
            throw new RuntimeException(resultHolder.exception);
        }

        T instance = resultHolder.instance;
        populate(instance, configuration);
        return instance;
    }

    public static void populate(Object instance) {
        BeanRandom.populate(instance, new Configuration());
    }

    public static void populate(Object instance, Configuration configuration) {
        new PopulateBean(configuration).populate(instance);
    }

}
