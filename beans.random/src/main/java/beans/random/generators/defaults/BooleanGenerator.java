package beans.random.generators.defaults;

import java.util.Random;

import beans.random.generators.Generator;

public class BooleanGenerator implements Generator<Boolean> {

    final private Random random = new Random();

    @Override
    public Boolean getValue() {
        return random.nextBoolean();
    }
}
