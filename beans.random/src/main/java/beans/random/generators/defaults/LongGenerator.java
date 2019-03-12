package beans.random.generators.defaults;

import java.util.Random;

import beans.random.generators.Generator;

public class LongGenerator implements Generator<Long> {

	final private LongConfiguration configuration;
	final private Random random = new Random();

	public LongGenerator() {
		this(new LongConfiguration());
	}

	public LongGenerator(LongConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("null configuration");
		this.configuration = configuration;
	}

	@Override
	public Long getValue() {

		long min = configuration.getMin();
		long max = configuration.getMax();
		long value = 0;
		do {
			value = random.nextLong();
		} while (value < min || value > max);

		return value;
	}

	public static class LongConfiguration {

		private long min = Long.MIN_VALUE;
		private long max = Long.MAX_VALUE;

		public long getMin() {
			return min;
		}

		public void setMin(long min) {
			this.min = min;
		}

		public long getMax() {
			return max;
		}

		public void setMax(long max) {
			this.max = max;
		}

	}
}
