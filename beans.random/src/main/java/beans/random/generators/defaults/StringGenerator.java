package beans.random.generators.defaults;

import beans.random.generators.Generator;
import beans.random.generators.configurations.CharacterStrategy;

import java.util.UUID;

public class StringGenerator implements Generator<String> {

    final private StringConfiguration configuration;

    public StringGenerator() {
        this(new StringConfiguration());
    }

    public StringGenerator(StringConfiguration configuration) {
        if (configuration == null)
            throw new IllegalArgumentException("null configuration");
        this.configuration = configuration;
    }

    @Override
    public String getValue() {

        String value = UUID.randomUUID().toString();

        int maxLength = configuration.getMaxLength();
        if (maxLength > 0) {
            if (maxLength < value.length()) {
                value = value.substring(0, maxLength);
            }
        }
        if (CharacterStrategy.UPPER.equals(configuration.getStrategy())) {
            return value.toUpperCase();
        }
        if (CharacterStrategy.LOWER.equals(configuration.getStrategy())) {
            return value.toLowerCase();
        }

        return value;
    }

    public static class StringConfiguration {

        private int maxLength = 0;
        private CharacterStrategy strategy = CharacterStrategy.ANY;

        public int getMaxLength() {
            return maxLength;
        }

        public StringConfiguration setMaxLength(int maxLength) {
            if (maxLength < 0) {
                this.maxLength = 0;
            } else {
                this.maxLength = maxLength;
            }
            return this;
        }

        public CharacterStrategy getStrategy() {
            return strategy;
        }

        public StringConfiguration useUpperCase() {
            strategy = CharacterStrategy.UPPER;
            return this;
        }

        public StringConfiguration useLowerCase() {
            strategy = CharacterStrategy.LOWER;
            return this;
        }

        public StringConfiguration useAnyCase() {
            strategy = CharacterStrategy.ANY;
            return this;
        }

    }

}
