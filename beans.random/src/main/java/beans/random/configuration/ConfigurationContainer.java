package beans.random.configuration;

import beans.random.generators.configurations.DateConfiguration;
import beans.random.generators.configurations.SequenceConfiguration;
import beans.random.generators.defaults.BigDecimalGenerator.BigDecimalConfiguration;
import beans.random.generators.defaults.BigIntegerGenerator.BigIntegerConfiguration;
import beans.random.generators.defaults.ByteGenerator.ByteConfiguration;
import beans.random.generators.defaults.CharacterGenerator.CharacterConfiguration;
import beans.random.generators.defaults.DoubleGenerator.DoubleConfiguration;
import beans.random.generators.defaults.FloatGenerator.FloatConfiguration;
import beans.random.generators.defaults.IntegerGenerator.IntegerConfiguration;
import beans.random.generators.defaults.LongGenerator.LongConfiguration;
import beans.random.generators.defaults.ShortGenerator.ShortConfiguration;
import beans.random.generators.defaults.StringGenerator.StringConfiguration;

class ConfigurationContainer {

    final private ByteConfiguration byteConfiguration = new ByteConfiguration();
    final private DoubleConfiguration doubleConfiguration = new DoubleConfiguration();
    final private FloatConfiguration floatConfiguration = new FloatConfiguration();
    final private IntegerConfiguration integerConfiguration = new IntegerConfiguration();
    final private LongConfiguration longConfiguration = new LongConfiguration();
    final private ShortConfiguration shortConfiguration = new ShortConfiguration();

    final private CharacterConfiguration characterConfiguration = new CharacterConfiguration();
    final private StringConfiguration stringConfiguration = new StringConfiguration();
    final private DateConfiguration dateConfiguration = new DateConfiguration();
    final private BigIntegerConfiguration bigIntegerConfiguration = new BigIntegerConfiguration();
    final private BigDecimalConfiguration bigDecimalConfiguration = new BigDecimalConfiguration();
    final private SequenceConfiguration sequenceConfiguration = new SequenceConfiguration();

    public ByteConfiguration getByteConfiguration() {
        return byteConfiguration;
    }

    public DoubleConfiguration getDoubleConfiguration() {
        return doubleConfiguration;
    }

    public FloatConfiguration getFloatConfiguration() {
        return floatConfiguration;
    }

    public IntegerConfiguration getIntegerConfiguration() {
        return integerConfiguration;
    }

    public LongConfiguration getLongConfiguration() {
        return longConfiguration;
    }

    public ShortConfiguration getShortConfiguration() {
        return shortConfiguration;
    }

    public CharacterConfiguration getCharacterConfiguration() {
        return characterConfiguration;
    }

    public StringConfiguration getStringConfiguration() {
        return stringConfiguration;
    }

    public DateConfiguration getDateConfiguration() {
        return dateConfiguration;
    }

    public BigIntegerConfiguration getBigIntegerConfiguration() {
        return bigIntegerConfiguration;
    }

    public BigDecimalConfiguration getBigDecimalConfiguration() {
        return bigDecimalConfiguration;
    }

    public SequenceConfiguration getSequenceConfiguration() {
        return sequenceConfiguration;
    }

}
