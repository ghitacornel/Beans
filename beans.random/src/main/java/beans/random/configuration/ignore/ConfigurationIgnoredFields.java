package beans.random.configuration.ignore;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Configuration that holds all to be ignored {@link Field}s<br>
 * Fields to be ignored can be specified by name for all {@link Class}es<br>
 * Fields to be ignored can be specified by name for certain {@link Class}es<br>
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

    /**
     *
     * @return if final fields are to be ignored or not
     */
    public boolean isIgnoreFinals() {
        return ignoreFinals;
    }

    /**
     *
     * @param affectFinals specify if for final fields random values are to be
     * generated or not
     */
    public void setIgnoreFinals(boolean affectFinals) {
        this.ignoreFinals = affectFinals;
    }

    /**
     *
     * @return a set with all field names to be ignored for all classes
     */
    public Set<String> getFields() {
        return fields;
    }

    /**
     *
     * @return a map that links a class canonical name with a set of fields to
     * be ignored for it
     */
    public Map<String, Set<String>> getClassFields() {
        return classFields;
    }

    /**
     *
     * @param fieldNames class fields to be ignored by name
     */
    public void ignore(String... fieldNames) {
        this.fields.addAll(Arrays.asList(fieldNames));
    }

    /**
     *
     * @param classCanonicalName class canonical name with fields to be ignored
     * @param fieldName class field name to be ignored
     */
    public void ignoreFieldForClass(String classCanonicalName, String fieldName) {
        Set<String> set = getClassFields().get(classCanonicalName);
        if (set == null) {
            set = new HashSet<>();
            getClassFields().put(classCanonicalName, set);
        }
        set.add(fieldName);
    }

    /**
     *
     * @param clazz class with fields to be ignored
     * @param fieldName class field name to be ignored
     */
    public void ignoreFieldForClass(Class<?> clazz, String fieldName) {
        ignoreFieldForClass(clazz.getCanonicalName(), fieldName);
    }

}
