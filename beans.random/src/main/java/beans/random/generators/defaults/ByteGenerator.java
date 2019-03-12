package beans.random.generators.defaults;

import java.util.Random;

import beans.random.generators.Generator;

public class ByteGenerator implements Generator<Byte> {

	final private ByteConfiguration configuration;
	final private Random random = new Random();

	public ByteGenerator() {
		this(new ByteConfiguration());
	}

	public ByteGenerator(ByteConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("null configuration");
		this.configuration = configuration;
	}

	@Override
	public Byte getValue() {

		byte min = configuration.getMin();
		byte max = configuration.getMax();
		byte value = 0;
		do {
			value = (byte) random.nextInt();
		} while (value < min || value > max);

		return value;
	}

	public static class ByteConfiguration {

		private byte min = Byte.MIN_VALUE;
		private byte max = Byte.MAX_VALUE;

		public byte getMin() {
			return min;
		}

		public void setMin(byte min) {
			this.min = min;
		}

		public byte getMax() {
			return max;
		}

		public void setMax(byte max) {
			this.max = max;
		}

	}
}
