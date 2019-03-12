package beans.random.generators.defaults;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import beans.random.generators.Generator;
import beans.random.generators.configurations.DateConfiguration;
import beans.random.generators.configurations.DateConfiguration.DateStrategy;

public class SqlTimestampGenerator implements Generator<Timestamp> {

	final private DateConfiguration configuration;
	final private Random random = new Random();

	public SqlTimestampGenerator() {
		this(new DateConfiguration());
	}

	public SqlTimestampGenerator(DateConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("null configuration");
		this.configuration = configuration;
	}

	@Override
	public Timestamp getValue() {

		if (DateStrategy.RANDOM.equals(configuration.getStrategy())) {
			return new Timestamp(random.nextLong());
		}

		Calendar instance = Calendar.getInstance();
		if (DateStrategy.YESTERDAY.equals(configuration.getStrategy())) {
			instance.add(Calendar.DAY_OF_YEAR, -1);
		}
		if (DateStrategy.TOMORROW.equals(configuration.getStrategy())) {
			instance.add(Calendar.DAY_OF_YEAR, 1);
		}

		// expect NOW as last possible setting
		return new Timestamp(instance.getTimeInMillis());

	}

}
