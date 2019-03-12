package beans.random.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration class with setting related to ignored {@link Class}es
 *
 * @author cornel.ghita
 *
 */
public class ConfigurationIgnoredClasses {

    /**
     * ignored class canonical names
     */
    final private Set<String> classes = new HashSet<>();

    public Set<String> getClasses() {
        return classes;
    }

    public void ignore(String... classCanonicalNames) {
        this.classes.addAll(Arrays.asList(classCanonicalNames));
    }

    public void ignore(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            this.classes.add(clazz.getCanonicalName());
        }
    }

}
