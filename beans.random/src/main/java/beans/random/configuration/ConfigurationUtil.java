package beans.random.configuration;

import beans.random.exceptions.NonPublicClassEncounteredException;
import beans.random.exceptions.WrappedInstantiationException;
import beans.reflection.ResultHolder;
import beans.reflection.exceptions.NoPublicNoArgumentConstructorClassException;
import beans.reflection.utils.ClassUtils;

public final class ConfigurationUtil {

    private ConfigurationUtil() {
        // helper
    }

    public static void process(ResultHolder<?> resultHolder, Configuration configuration) {

        Exception e = resultHolder.exception;

        if (e == null) {
            return;
        }

        if (!ClassUtils.isPublic(resultHolder.clazz)) {
            if (configuration.raiseExceptionWhenNonPublicClassEncountered()) {
                throw new NonPublicClassEncounteredException(resultHolder.clazz);
            } else {
                return;
            }
        }

        if (!ClassUtils.hasPublicNoArgumentConstructor(resultHolder.clazz)) {
            if (configuration
                    .raiseExceptionWhenPublicNoArgumentConstructorIsNotFound()) {
                throw new NoPublicNoArgumentConstructorClassException(
                        resultHolder.clazz);
            } else {
                return;
            }
        }

        throw new WrappedInstantiationException(e);

    }

}
