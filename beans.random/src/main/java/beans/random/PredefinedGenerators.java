package beans.random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import beans.random.configuration.Configuration;
import beans.random.generators.Generator;
import beans.random.generators.defaults.BigDecimalGenerator;
import beans.random.generators.defaults.BigIntegerGenerator;
import beans.random.generators.defaults.BooleanGenerator;
import beans.random.generators.defaults.ByteGenerator;
import beans.random.generators.defaults.CharacterGenerator;
import beans.random.generators.defaults.DateGenerator;
import beans.random.generators.defaults.DoubleGenerator;
import beans.random.generators.defaults.EnumGenerator;
import beans.random.generators.defaults.FloatGenerator;
import beans.random.generators.defaults.IntegerGenerator;
import beans.random.generators.defaults.LongGenerator;
import beans.random.generators.defaults.ShortGenerator;
import beans.random.generators.defaults.SqlDateGenerator;
import beans.random.generators.defaults.SqlTimestampGenerator;
import beans.random.generators.defaults.StringGenerator;

/**
 * Utility {@link Class} used for generating simple values.<br>
 * Use this class to generate 'primitive' or wrapper like instances.
 *
 * @author cornel.ghita
 *
 */
public final class PredefinedGenerators {

	/**
	 * {@link Map} with registered {@link Class}es and their corresponding
	 * {@link Generator}s
	 */
	final private Map<Class<?>, Generator<?>> generators = new HashMap<>();

	/**
	 * for {@link Enum} this custom {@link Generator} is registered
	 */
	final private static EnumGenerator enumGenerator = new EnumGenerator();

	public PredefinedGenerators(Configuration configuration) {

		// wrappers
		{
			generators.put(Boolean.class, new BooleanGenerator());
			generators.put(boolean.class, new BooleanGenerator());

			generators.put(Byte.class,
					new ByteGenerator(configuration.getByteConfiguration()));
			generators.put(byte.class,
					new ByteGenerator(configuration.getByteConfiguration()));

			generators.put(Short.class,
					new ShortGenerator(configuration.getShortConfiguration()));
			generators.put(short.class,
					new ShortGenerator(configuration.getShortConfiguration()));

			generators.put(
					Integer.class,
					new IntegerGenerator(configuration
							.getIntegerConfiguration()));
			generators.put(
					int.class,
					new IntegerGenerator(configuration
							.getIntegerConfiguration()));

			generators.put(Long.class,
					new LongGenerator(configuration.getLongConfiguration()));
			generators.put(long.class,
					new LongGenerator(configuration.getLongConfiguration()));

			generators.put(Float.class,
					new FloatGenerator(configuration.getFloatConfiguration()));
			generators.put(float.class,
					new FloatGenerator(configuration.getFloatConfiguration()));

			generators
					.put(Double.class,
							new DoubleGenerator(configuration
									.getDoubleConfiguration()));
			generators
					.put(double.class,
							new DoubleGenerator(configuration
									.getDoubleConfiguration()));

			generators.put(Character.class, new CharacterGenerator(
					configuration.getCharacterConfiguration()));
			generators.put(
					char.class,
					new CharacterGenerator(configuration
							.getCharacterConfiguration()));
		}

		// objects
		{
			generators
					.put(String.class,
							new StringGenerator(configuration
									.getStringConfiguration()));
			generators.put(BigInteger.class, new BigIntegerGenerator(
					configuration.getBigIntegerConfiguration()));
			generators.put(BigDecimal.class, new BigDecimalGenerator(
					configuration.getBigDecimalConfiguration()));
			generators.put(java.util.Date.class, new DateGenerator(
					configuration.getDateConfiguration()));
			generators.put(java.sql.Date.class, new SqlDateGenerator(
					configuration.getDateConfiguration()));
			generators.put(java.sql.Timestamp.class, new SqlTimestampGenerator(
					configuration.getDateConfiguration()));
		}

	}

	/**
	 *
	 * @param clazz
	 * @return a random value generated for the given {@link Class} or null if
	 *         no {@link Generator} is registered for it
	 */
	public Object generate(Class<?> clazz) {
		if (clazz.isEnum()) {
			return enumGenerator.getValue(clazz);
		}
		Generator<?> generator = generators.get(clazz);
		if (generator == null) {
			return null;
		}
		return generator.getValue();
	}

}
