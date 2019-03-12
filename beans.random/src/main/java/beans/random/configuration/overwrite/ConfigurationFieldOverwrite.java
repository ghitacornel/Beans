package beans.random.configuration.overwrite;

import java.util.HashMap;
import java.util.Map;

import beans.random.generators.Generator;

/**
 * Configuration that holds custom field generators
 *
 * @author cornel.ghita
 *
 */
public class ConfigurationFieldOverwrite {

    /**
     * generator per field name (regardless of class)
     */
    final private Map<String, Generator<?>> fieldGenerators = new HashMap<>();

    /**
     * generator per class field name
     */
    final private Map<String, Map<String, Generator<?>>> classFieldGenerators = new HashMap<>();

    /**
     *
     * @return a map between field names and custom generators
     */
    public Map<String, Generator<?>> getFieldGenerators() {
        return fieldGenerators;
    }

    /**
     *
     * @return a map between a class canonical name and a map holding custom
     * generators for class field names
     */
    public Map<String, Map<String, Generator<?>>> getClassFieldsGenerators() {
        return classFieldGenerators;
    }

    /**
     *
     * @param fieldName a field name
     * @param generator a custom generator for the given field name
     */
    public void register(String fieldName, Generator<?> generator) {
        fieldGenerators.put(fieldName, generator);
    }

    /**
     *
     * @param classCanonicalName a class canonical name
     * @param fieldName a field name part of the given class
     * @param generator a custom generator for the field name
     */
    public void register(String classCanonicalName, String fieldName,
            Generator<?> generator) {
        Map<String, Generator<?>> map = classFieldGenerators
                .get(classCanonicalName);
        if (map == null) {
            map = new HashMap<>();
            classFieldGenerators.put(classCanonicalName, map);
        }
        map.put(fieldName, generator);
    }

    /**
     *
     * @param clazz a class
     * @param fieldName a field name part of the given class
     * @param generator a custom generator for the field name
     */
    public void register(Class<?> clazz, String fieldName,
            Generator<?> generator) {
        register(clazz.getCanonicalName(), fieldName, generator);
    }
}
