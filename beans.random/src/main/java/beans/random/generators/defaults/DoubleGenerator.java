package beans.random.generators.defaults;

import beans.random.generators.Generator;

import java.util.Random;

public class DoubleGenerator implements Generator<Double> {

    final private DoubleConfiguration configuration;
    final private Random random = new Random();

    public DoubleGenerator() {
        this(new DoubleConfiguration());
    }

    public DoubleGenerator(DoubleConfiguration configuration) {
        if (configuration == null)
            throw new IllegalArgumentException("null configuration");
        this.configuration = configuration;
    }

    @Override
    public Double getValue() {
        double min = configuration.getMin();
        double max = configuration.getMax();
        double value = min + random.nextDouble() * (max - min);
        return value;
    }

    public static class DoubleConfiguration {

        private double min = 0;
        private double max = 1;

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

    }
}
