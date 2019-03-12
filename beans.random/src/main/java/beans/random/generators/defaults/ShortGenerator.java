package beans.random.generators.defaults;

import java.util.Random;

import beans.random.generators.Generator;

public class ShortGenerator implements Generator<Short> {

	final private ShortConfiguration configuration;
	final private Random random = new Random();

	public ShortGenerator() {
		this(new ShortConfiguration());
	}

	public ShortGenerator(ShortConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("null configuration");
		this.configuration = configuration;
	}

	@Override
	public Short getValue() {

		short min = configuration.getMin();
		short max = configuration.getMax();
		short value = 0;
		do {
			value = (short) random.nextInt();
		} while (value < min || value > max);

		return value;
	}

	public static class ShortConfiguration {

		private short min = Short.MIN_VALUE;
		private short max = Short.MAX_VALUE;

		public short getMin() {
			return min;
		}

		public void setMin(short min) {
			this.min = min;
		}

		public short getMax() {
			return max;
		}

		public void setMax(short max) {
			this.max = max;
		}

	}

}
