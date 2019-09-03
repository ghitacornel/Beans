package beans.random.configuration;

/**
 * If {@link InjectionStrategy#FIELD} is used then this strategy is taken into
 * consideration
 *
 * @author Cornel
 */
public enum FieldGenerationStrategy {

    /**
     * if fields already have a value then ignore it and generate a new one
     */
    IGNORE,

    /**
     * if fields already have a value then keep it
     */
    KEEP

}
