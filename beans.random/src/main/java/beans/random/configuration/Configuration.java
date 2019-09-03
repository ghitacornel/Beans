package beans.random.configuration;

import beans.random.configuration.ignore.ConfigurationIgnoredClasses;
import beans.random.configuration.ignore.ConfigurationIgnoredFields;
import beans.random.configuration.ignore.ConfigurationIgnoredPackages;
import beans.random.configuration.overwrite.ConfigurationClassOverwrite;
import beans.random.configuration.overwrite.ConfigurationFieldOverwrite;

import java.util.Set;

public class Configuration extends ConfigurationContainer {

    final private ConfigurationIgnoredFields ignoredFields = new ConfigurationIgnoredFields();
    final private ConfigurationIgnoredClasses ignoredClasses = new ConfigurationIgnoredClasses();
    final private ConfigurationIgnoredPackages ignoredPackages = new ConfigurationIgnoredPackages();
    final private ConfigurationFieldOverwrite overwriteFields = new ConfigurationFieldOverwrite();
    final private ConfigurationClassOverwrite overwriteClasses = new ConfigurationClassOverwrite();
    private int containmentDepthLevel = Integer.MAX_VALUE;
    private int collectionsSize = 5;
    private InjectionStrategy injectionStrategy = InjectionStrategy.FIELD;
    private FieldGenerationStrategy fieldGenerationStrategy = FieldGenerationStrategy.IGNORE;
    private boolean raiseExceptionWhenNonPublicClassesEncountered = false;
    private boolean raiseExceptionWhenPublicNoArgumentConstructorIsNotFound = false;
    // TODO
    @SuppressWarnings("unused")
    private Set<Class<?>> uninstantiableClasses;
    // TODO
    @SuppressWarnings("unused")
    private Set<Class<?>> nonPublicClasses;

    public int getContainmentDepthLevel() {
        return containmentDepthLevel;
    }

    public Configuration setContainmentDepthLevel(int depthLevel) {
        this.containmentDepthLevel = depthLevel;
        return this;
    }

    public int getCollectionsSize() {
        return collectionsSize;
    }

    public void setCollectionsSize(int collectionsSize) {
        this.collectionsSize = collectionsSize;
    }

    public InjectionStrategy getInjectionStrategy() {
        return injectionStrategy;
    }

    public void setInjectionStrategy(InjectionStrategy strategy) {
        this.injectionStrategy = strategy;
    }

    public Configuration usePositiveNumbers() {

        getByteConfiguration().setMin((byte) 0);
        getShortConfiguration().setMin((short) 0);
        getIntegerConfiguration().setMin(0);
        getLongConfiguration().setMin(0);

        getBigIntegerConfiguration().setSignum(1);
        getBigDecimalConfiguration().setSignum(1);

        return this;
    }

    public Configuration useNegativeNumbers() {

        getByteConfiguration().setMax((byte) 0);
        getShortConfiguration().setMax((short) 0);
        getIntegerConfiguration().setMax(0);
        getLongConfiguration().setMax(0);

        getBigIntegerConfiguration().setSignum(-1);
        getBigDecimalConfiguration().setSignum(-1);

        return this;
    }

    public Configuration useStrictPositiveNumbers() {

        getByteConfiguration().setMin((byte) 1);
        getShortConfiguration().setMin((short) 1);
        getIntegerConfiguration().setMin(1);
        getLongConfiguration().setMin(1);

        getFloatConfiguration().setMin(0);
        getFloatConfiguration().setMax(1);
        getDoubleConfiguration().setMin(0);
        getDoubleConfiguration().setMax(1);

        getBigIntegerConfiguration().setSignum(1);
        getBigDecimalConfiguration().setSignum(1);

        return this;
    }

    public Configuration useStrictNegativeNumbers() {

        getByteConfiguration().setMax((byte) -1);
        getShortConfiguration().setMax((short) -1);
        getIntegerConfiguration().setMax(-1);
        getLongConfiguration().setMax(-1);

        getFloatConfiguration().setMin(-1);
        getFloatConfiguration().setMax(0);
        getDoubleConfiguration().setMin(-1);
        getDoubleConfiguration().setMax(0);

        getBigIntegerConfiguration().setSignum(-1);
        getBigDecimalConfiguration().setSignum(-1);

        return this;
    }

    public boolean raiseExceptionWhenNonPublicClassEncountered() {
        return raiseExceptionWhenNonPublicClassesEncountered;
    }

    public Configuration raiseExceptionWhenNonPublicClassEncountered(
            boolean raiseExceptionWhenNonPublicClassesEncountered) {
        this.raiseExceptionWhenNonPublicClassesEncountered = raiseExceptionWhenNonPublicClassesEncountered;
        return this;
    }

    public boolean raiseExceptionWhenPublicNoArgumentConstructorIsNotFound() {
        return raiseExceptionWhenPublicNoArgumentConstructorIsNotFound;
    }

    public Configuration raiseExceptionWhenPublicNoArgumentConstructorIsNotFound(
            boolean raiseExceptionWhenNoDefaultConstructorIsFound) {
        this.raiseExceptionWhenPublicNoArgumentConstructorIsNotFound = raiseExceptionWhenNoDefaultConstructorIsFound;
        return this;
    }

    public ConfigurationIgnoredFields getIgnoredFields() {
        return ignoredFields;
    }

    public ConfigurationIgnoredClasses getIgnoredClasses() {
        return ignoredClasses;
    }

    public ConfigurationIgnoredPackages getIgnoredPackages() {
        return ignoredPackages;
    }

    public ConfigurationFieldOverwrite getOverwriteFields() {
        return overwriteFields;
    }

    public ConfigurationClassOverwrite getOverwriteClass() {
        return overwriteClasses;
    }

    public FieldGenerationStrategy getFieldGenerationStrategy() {
        return fieldGenerationStrategy;
    }

    public void setFieldGenerationStrategy(
            FieldGenerationStrategy fieldGenerationStrategy) {
        this.fieldGenerationStrategy = fieldGenerationStrategy;
    }

}
