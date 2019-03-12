package test.tests.beans.overwrites.classes;

import org.junit.Assert;
import org.junit.Test;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;
import beans.random.generators.Generator;

public class TestOverwrites {

    public static class Bean {

        Integer id;

        Integer generated;

        Integer witness;

    }

    @Test
    public void testOk() {

        Configuration configuration = new Configuration();
        configuration.useStrictNegativeNumbers();
        Bean bean = BeanRandom.random(Bean.class, configuration);

        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.id);
        Assert.assertTrue(bean.id < 0);
        Assert.assertNotNull(bean.generated);
        Assert.assertTrue(bean.generated < 0);
        Assert.assertNotNull(bean.witness);
        Assert.assertTrue(bean.witness < 0);

    }

    @Test
    public void testOverwritesClass() {

        Configuration configuration = new Configuration();
        configuration.useStrictNegativeNumbers();

        configuration.getOverwriteClass().register(Integer.class,
                new Generator<Integer>() {

                    @Override
                    public Integer getValue() {
                        return 2;
                    }
                });

        Bean bean = BeanRandom.random(Bean.class, configuration);

        Assert.assertNotNull(bean);

        Assert.assertNotNull(bean.id);
        Assert.assertEquals(new Integer(2), bean.id);

        Assert.assertNotNull(bean.generated);
        Assert.assertEquals(new Integer(2), bean.generated);

        Assert.assertNotNull(bean.witness);
        Assert.assertEquals(new Integer(2), bean.witness);

    }

}
