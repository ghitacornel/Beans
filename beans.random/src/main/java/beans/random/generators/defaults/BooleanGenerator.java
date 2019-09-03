package beans.random.generators.defaults;

import beans.random.generators.Generator;

import java.util.Random;

public class BooleanGenerator implements Generator<Boolean> {

    final private Random random = new Random();

    @Override
    public Boolean getValue() {
        return random.nextBoolean();
    }
}
