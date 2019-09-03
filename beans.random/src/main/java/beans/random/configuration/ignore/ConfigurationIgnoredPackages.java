package beans.random.configuration.ignore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration that holds all to be ignored packages by name<br>
 * If a field/setter will be of a certain type which is defined in an ignored
 * package then no random value will be generated for it<br>
 * Ignoring a package will not ignore all it's sub packages
 *
 * @author cornel.ghita
 */
public class ConfigurationIgnoredPackages {

    /**
     * ignored package names
     */
    final private Set<String> packages = new HashSet<>();

    /**
     * @return all package names that will be ignored
     */
    public Set<String> getPackages() {
        return packages;
    }

    /**
     * @param packageNames package names to be ignored
     */
    public void ignore(String... packageNames) {
        this.packages.addAll(Arrays.asList(packageNames));
    }

}
