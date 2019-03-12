package beans.random.configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Configuration class with setting related to ignored {@link Field}s
 *
 * @author cornel.ghita
 *
 */
public class ConfigurationIgnoredFields {

    /**
     * ignore final fields ?
     */
    private boolean ignoreFinals = true;

    /**
     * ignored fields by name regardless of class
     */
    final private Set<String> fields = new HashSet<>();

    /**
     * ignored class field names
     */
    final private Map<String, Set<String>> classFields = new HashMap<>();

    public boolean isIgnoreFinals() {
        return ignoreFinals;
    }

    public void setIgnoreFinals(boolean affectFinals) {
        this.ignoreFinals = affectFinals;
    }

    public Set<String> getFields() {
        return fields;
    }

    public Map<String, Set<String>> getClassFields() {
        return classFields;
    }

    public void ignore(String... fieldNames) {
        this.fields.addAll(Arrays.asList(fieldNames));
    }

    public void ignoreFieldForClass(String classCanonicalName, String fieldName) {
        Set<String> set = getClassFields().get(classCanonicalName);
        if (set == null) {
            set = new HashSet<>();
            getClassFields().put(classCanonicalName, set);
        }
        set.add(fieldName);
    }

    public void ignoreFieldForClass(Class<?> clazz, String fieldName) {
        ignoreFieldForClass(clazz.getCanonicalName(), fieldName);
    }

}
