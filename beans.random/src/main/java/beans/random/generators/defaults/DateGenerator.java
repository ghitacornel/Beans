package beans.random.generators.defaults;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import beans.random.generators.Generator;
import beans.random.generators.configurations.DateConfiguration;
import beans.random.generators.configurations.DateConfiguration.DateStrategy;

public class DateGenerator implements Generator<Date> {

	final private DateConfiguration configuration;
	final private Random random = new Random();

	public DateGenerator() {
		this(new DateConfiguration());
	}

	public DateGenerator(DateConfiguration configuration) {
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
		return instance.getTime();

	}

}
