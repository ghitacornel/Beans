package beans.random.configuration.overwrite;

import beans.random.generators.Generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration that holds custom class generators
 *
 * @author cornel.ghita
 */
public class ConfigurationClassOverwrite {

    /**
     * generator per class name
     */
    final private Map<String, Generator<?>> generators = new HashMap<>();

    /**
     * @return a map between class canonical names and custom generators
     */
    public Map<String, Generator<?>> getGenerators() {
        return generators;
    }

    /**
     * @param classCanonicalName a class canonical name
     * @param generator          a custom generator for the given classCanonicalName
     */
    public void overwrite(String classCanonicalName, Generator<?> generator) {
        generators.put(classCanonicalName, generator);
    }

    /**
     * @param clazz
     * @param generator a custom generator for the given class
     */
    public void register(Class<?> clazz, Generator<?> generator) {
        overwrite(clazz.getCanonicalName(), generator);
    }

}
