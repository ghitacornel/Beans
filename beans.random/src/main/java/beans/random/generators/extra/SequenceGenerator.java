package beans.random.generators.extra;

import beans.random.generators.Generator;
import beans.random.generators.configurations.SequenceConfiguration;

public class SequenceGenerator implements Generator<Long> {

    final private SequenceConfiguration configuration;
    private long currentValue;

    public SequenceGenerator() {
        this(new SequenceConfiguration());
    }

    public SequenceGenerator(SequenceConfiguration configuration) {
        this.configuration = configuration;
        this.currentValue = this.configuration.getStart();
    }

    @Override
    public Long getValue() {

        long nextValue = currentValue + configuration.getIncrement();

        // condition 'nextValue < currentValue' is used for cases when nextValue
        // is greater than
        // Long.MAX_VALUE, hence reset to a negative value smaller than the
        // current one
        if (nextValue > configuration.getMax() || nextValue < currentValue) {
            if (configuration.isCycle()) {
                nextValue = configuration.getMin();
            } else {
                throw new SequenceOverflowException();
            }
        }

        currentValue = nextValue;
        return currentValue;
    }

    @SuppressWarnings("serial")
    public static class SequenceOverflowException extends RuntimeException {

        public SequenceOverflowException() {
            super(
                    "Sequence maximum value reached and option 'no cycle' is active");
        }
    }
}
