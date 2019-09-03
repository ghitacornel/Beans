package beans.random.generators.defaults;

import beans.random.generators.Generator;
import beans.random.generators.configurations.CharacterStrategy;

import java.util.Random;

public class CharacterGenerator implements Generator<Character> {

    final private CharacterConfiguration configuration;
    final private Random random = new Random();

    public CharacterGenerator() {
        this(new CharacterConfiguration());
    }

    public CharacterGenerator(CharacterConfiguration configuration) {
        if (configuration == null)
            throw new IllegalArgumentException("null configuration");
        this.configuration = configuration;
    }

    @Override
    public Character getValue() {
        char character = (char) random.nextInt();

        if (CharacterStrategy.UPPER.equals(configuration.getStrategy())) {
            return Character.toUpperCase(character);
        }
        if (CharacterStrategy.LOWER.equals(configuration.getStrategy())) {
            return Character.toLowerCase(character);
        }

        return character;
    }

    public static class CharacterConfiguration {

        private CharacterStrategy strategy = CharacterStrategy.ANY;

        public CharacterStrategy getStrategy() {
            return strategy;
        }

        public CharacterConfiguration useUpperCase() {
            strategy = CharacterStrategy.UPPER;
            return this;
        }

        public CharacterConfiguration useLowerCase() {
            strategy = CharacterStrategy.LOWER;
            return this;
        }

        public CharacterConfiguration useAnyCase() {
            strategy = CharacterStrategy.ANY;
            return this;
        }

    }

}
