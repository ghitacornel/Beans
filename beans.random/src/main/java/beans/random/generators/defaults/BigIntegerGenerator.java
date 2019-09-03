package beans.random.generators.defaults;

import beans.random.generators.Generator;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerGenerator implements Generator<BigInteger> {

    final private BigIntegerConfiguration configuration;
    final private Random random = new Random();

    public BigIntegerGenerator() {
        this(new BigIntegerConfiguration());
    }

    public BigIntegerGenerator(BigIntegerConfiguration configuration) {
        if (configuration == null)
            throw new IllegalArgumentException("null configuration");
        this.configuration = configuration;
    }

    @Override
    public BigInteger getValue() {

        BigInteger value = new BigInteger(configuration.getNumberOfBits(),
                random);

        if (configuration.getSignum() < 0)
            value = value.negate();

        return value;
    }

    public static class BigIntegerConfiguration {

        private int numberOfBits = 32;
        private int signum;

        public int getNumberOfBits() {
            return numberOfBits;
        }

        public void setNumberOfBits(int numberOfBits) {
            if (numberOfBits <= 0) {
                numberOfBits = 32;
            }
            this.numberOfBits = numberOfBits;
        }

        public int getSignum() {
            return signum;
        }

        public void setSignum(int signum) {
            this.signum = signum;
        }
    }

}
