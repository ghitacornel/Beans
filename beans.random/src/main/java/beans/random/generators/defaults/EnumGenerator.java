package beans.random.generators.defaults;

import java.util.Random;

public class EnumGenerator {

    final private Random random = new Random();

    public Object getValue(Class<?> clazz) {
        Object[] objects = clazz.getEnumConstants();
        return objects[Math.abs(random.nextInt()) % objects.length];
    }

}
