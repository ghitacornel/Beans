package beans.random.configuration.ignore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration that holds all to be ignored {@link Class}es<br>
 * Fields/Setters with these {@link Class}es types will not have random values
 * generated for them
 *
 * @author cornel.ghita
 */
public class ConfigurationIgnoredClasses {

    /**
     * ignored class canonical names
     */
    final private Set<String> classes = new HashSet<>();

    /**
     * @return all classes that will be ignored
     */
    public Set<String> getClasses() {
        return classes;
    }

    /**
     * @param classCanonicalNames {@link Class} canonical names to be ignored
     */
    public void ignore(String... classCanonicalNames) {
        this.classes.addAll(Arrays.asList(classCanonicalNames));
    }

    /**
     * @param classes {@link Class}es to be ignored
     */
    public void ignore(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            this.classes.add(clazz.getCanonicalName());
        }
    }

}
