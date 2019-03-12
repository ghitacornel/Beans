package beans.random.generators.defaults;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

import beans.random.generators.Generator;

public class BigDecimalGenerator implements Generator<BigDecimal> {

	final private BigDecimalConfiguration configuration;

	final private Random random = new Random();

	public BigDecimalGenerator() {
		this(new BigDecimalConfiguration());
	}

	public BigDecimalGenerator(BigDecimalConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("null configuration");
		this.configuration = configuration;
	}

	@Override
	public BigDecimal getValue() {

		BigDecimal value;

		MathContext mathContext = configuration.getMathContext();
		if (mathContext != null) {
			value = new BigDecimal(random.nextDouble(), mathContext);
		} else {
			value = new BigDecimal(random.nextDouble());
		}

		if (configuration.getSignum() < 0) {
			value = value.negate();
		}

		return value;
	}

	public static class BigDecimalConfiguration {

		private MathContext mathContext;
		private int signum;

		public MathContext getMathContext() {
			return mathContext;
		}

		public void setMathContext(MathContext mathContext) {
			this.mathContext = mathContext;
		}

		public int getSignum() {
			return signum;
		}

		public void setSignum(int signum) {
			this.signum = signum;
		}

	}
}
