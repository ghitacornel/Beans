package test.tests.beans.overwrites.fields;

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
    public void testOverwritesField() {

        Configuration configuration = new Configuration();
        configuration.useStrictNegativeNumbers();

        configuration.getOverwriteFields().register("id",
                new Generator<Integer>() {

                    @Override
                    public Integer getValue() {
                        return 1;
                    }
                });

        Bean bean = BeanRandom.random(Bean.class, configuration);

        Assert.assertNotNull(bean);

        Assert.assertNotNull(bean.id);
        Assert.assertEquals(new Integer(1), bean.id);

        Assert.assertNotNull(bean.generated);
        Assert.assertTrue(bean.generated < 0);

        Assert.assertNotNull(bean.witness);
        Assert.assertTrue(bean.witness < 0);

    }

    @Test
    public void testOverwritesClassField() {

        Configuration configuration = new Configuration();
        configuration.useStrictNegativeNumbers();

        configuration.getOverwriteFields().register(Bean.class, "generated",
                new Generator<Integer>() {

                    @Override
                    public Integer getValue() {
                        return 3;
                    }
                });

        Bean bean = BeanRandom.random(Bean.class, configuration);

        Assert.assertNotNull(bean);

        Assert.assertNotNull(bean.id);
        Assert.assertTrue(bean.id < 0);

        Assert.assertNotNull(bean.generated);
        Assert.assertEquals(new Integer(3), bean.generated);

        Assert.assertNotNull(bean.witness);
        Assert.assertTrue(bean.witness < 0);

    }

}
