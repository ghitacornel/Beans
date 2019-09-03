package beans.random.generators.defaults;

import beans.random.generators.Generator;
import beans.random.generators.configurations.DateConfiguration;
import beans.random.generators.configurations.DateConfiguration.DateStrategy;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

public class SqlDateGenerator implements Generator<Date> {

    final private DateConfiguration configuration;
    final private Random random = new Random();

    public SqlDateGenerator() {
        this(new DateConfiguration());
    }

    public SqlDateGenerator(DateConfiguration configuration) {
        if (configuration == null)
            throw new IllegalArgumentException("null configuration");
        this.configuration = configuration;
    }

    @Override
    public Date getValue() {

        if (DateStrategy.RANDOM.equals(configuration.getStrategy())) {
            return new Date(random.nextLong());
        }

        Calendar instance = Calendar.getInstance();
        if (DateStrategy.YESTERDAY.equals(configuration.getStrategy())) {
            instance.add(Calendar.DAY_OF_YEAR, -1);
        }
        if (DateStrategy.TOMORROW.equals(configuration.getStrategy())) {
            instance.add(Calendar.DAY_OF_YEAR, 1);
        }

        // expect NOW as last possible setting
        return new Date(instance.getTimeInMillis());

    }

}
