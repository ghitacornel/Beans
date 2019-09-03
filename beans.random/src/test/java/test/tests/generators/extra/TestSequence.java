package test.tests.generators.extra;

import beans.random.generators.configurations.SequenceConfiguration;
import beans.random.generators.extra.SequenceGenerator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author cornel.ghita
 */
public class TestSequence {

    @Test
    public void test() {

        SequenceGenerator generator = new SequenceGenerator();

        long[] values = new long[10];
        for (int i = 0; i < values.length; i++) {
            values[i] = generator.getValue();
        }

        for (int i = 0; i < values.length; i++) {
            Assert.assertEquals(i + 1, values[i]);
        }

    }

    /**
     * test max value overflow
     */
    @Test(expected = SequenceGenerator.SequenceOverflowException.class)
    public void testOverflowException1() {

        SequenceConfiguration configuration = new SequenceConfiguration();
        configuration.setMax(10);
        configuration.setCycle(false);
        configuration.setIncrement(100);
        SequenceGenerator generator = new SequenceGenerator(configuration);

        generator.getValue();
    }

    /**
     * test max value overflow to negative value
     */
    @Test(expected = SequenceGenerator.SequenceOverflowException.class)
    public void testOverflowException2() {

        SequenceConfiguration configuration = new SequenceConfiguration();
        configuration.setCycle(false);
        configuration.setMax(Long.MAX_VALUE - 1);
        configuration.setIncrement(100);
        configuration.setStart(Long.MAX_VALUE - 10);
        SequenceGenerator generator = new SequenceGenerator(configuration);

        generator.getValue();
    }

}
