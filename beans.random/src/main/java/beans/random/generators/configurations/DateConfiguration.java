package beans.random.generators.configurations;

public class DateConfiguration {

    private DateStrategy strategy = DateStrategy.RANDOM;

    public DateStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(DateStrategy strategy) {
        if (strategy == null) {
            this.strategy = DateStrategy.RANDOM;
        } else {
            this.strategy = strategy;
        }
    }

    public static enum DateStrategy {

        RANDOM, YESTERDAY, NOW, TOMORROW
    }

}
