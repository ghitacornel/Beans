package beans.random.configuration;

import java.util.HashMap;
import java.util.Map;

import beans.random.generators.Generator;

public class ConfigurationFieldsOverwrite {

    /**
     * generator per class name
     */
    final private Map<String, Generator<?>> classGenerators = new HashMap<>();

    /**
     * generator per field name (regardless of class)
     */
    final private Map<String, Generator<?>> fieldGenerators = new HashMap<>();

    /**
     * generator per class field name
     */
    final private Map<String, Map<String, Generator<?>>> classFieldGenerators = new HashMap<>();

    public Map<String, Generator<?>> getClassGenerators() {
        return classGenerators;
    }

    public Map<String, Generator<?>> getFieldGenerators() {
        return fieldGenerators;
    }

    public Map<String, Map<String, Generator<?>>> getClassFieldsGenerators() {
        return classFieldGenerators;
    }

    public void registerClassGenerator(String classCanonicalName,
            Generator<?> generator) {
        classGenerators.put(classCanonicalName, generator);
    }

    public void registerClassGenerator(Class<?> clazz, Generator<?> generator) {
        registerClassGenerator(clazz.getCanonicalName(), generator);
    }

    public void registerFieldGenerator(String fieldName, Generator<?> generator) {
        fieldGenerators.put(fieldName, generator);
    }

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

    public void register(Class<?> clazz, String fieldName,
            Generator<?> generator) {
        register(clazz.getCanonicalName(), fieldName, generator);
    }
}
