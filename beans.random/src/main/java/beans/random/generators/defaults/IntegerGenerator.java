package beans.random.generators.defaults;

import java.util.Random;

import beans.random.generators.Generator;

public class IntegerGenerator implements Generator<Integer> {

	final private IntegerConfiguration configuration;
	final private Random random = new Random();

	public IntegerGenerator() {
		this(new IntegerConfiguration());
	}

	public IntegerGenerator(IntegerConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("null configuration");
		this.configuration = configuration;
	}

	@Override
	public Integer getValue() {

		int min = configuration.getMin();
		int max = configuration.getMax();
		int value = 0;
		do {
			value = random.nextInt();
		} while (value < min || value > max);

		return value;
	}

	public static class IntegerConfiguration {

		private int min = Integer.MIN_VALUE;
		private int max = Integer.MAX_VALUE;

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			this.min = min;
		}

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}

	}

}
