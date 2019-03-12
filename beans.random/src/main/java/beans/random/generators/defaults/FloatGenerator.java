package beans.random.generators.defaults;

import java.util.Random;

import beans.random.generators.Generator;

public class FloatGenerator implements Generator<Float> {

	final private FloatConfiguration configuration;
	final private Random random = new Random();

	public FloatGenerator() {
		this(new FloatConfiguration());
	}

	public FloatGenerator(FloatConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("null configuration");
		this.configuration = configuration;
	}

	@Override
	public Float getValue() {
		float min = configuration.getMin();
		float max = configuration.getMax();
		float value = min + random.nextFloat() * (max - min);
		return value;
	}

	public static class FloatConfiguration {

		private float min = 0;
		private float max = 1;

		public float getMin() {
			return min;
		}

		public void setMin(float min) {
			this.min = min;
		}

		public float getMax() {
			return max;
		}

		public void setMax(float max) {
			this.max = max;
		}

	}

}
