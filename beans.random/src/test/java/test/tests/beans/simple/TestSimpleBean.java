package test.tests.beans.simple;

import org.junit.Assert;
import org.junit.Test;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;
import test.beans.simple.SimpleBean;

public class TestSimpleBean {

    @Test
    public void testSimpleObjects() {

        SimpleBean bean = BeanRandom.random(SimpleBean.class);

        Assert.assertNotNull(bean);

        Assert.assertNotNull(bean.getByte2());
        Assert.assertNotNull(bean.getShort2());
        Assert.assertNotNull(bean.getInt2());
        Assert.assertNotNull(bean.getLong2());
        Assert.assertNotNull(bean.getFloat2());
        Assert.assertNotNull(bean.getDouble2());
        Assert.assertNotNull(bean.getBoolean2());
        Assert.assertNotNull(bean.getChar2());

        Assert.assertNotNull(bean.getString());
        Assert.assertNotNull(bean.getSimpleEnum());
        Assert.assertNotNull(bean.getBigInteger());
        Assert.assertNotNull(bean.getBigDecimal());
        Assert.assertNotNull(bean.getSqlDate());
        Assert.assertNotNull(bean.getSqlTimestamp());

    }

    @Test
    public void testStrictPositives() {

        Configuration configuration = new Configuration()
                .useStrictPositiveNumbers();
        SimpleBean bean = BeanRandom.random(SimpleBean.class, configuration);

        Assert.assertNotNull(bean);

        Assert.assertTrue(bean.getByte2() > 0);
        Assert.assertTrue(bean.getShort2() > 0);
        Assert.assertTrue(bean.getInt2() > 0);
        Assert.assertTrue(bean.getLong2() > 0);
        Assert.assertTrue(bean.getFloat2() > 0);
        Assert.assertTrue(bean.getDouble2() > 0);

        Assert.assertTrue(bean.getByte1() > 0);
        Assert.assertTrue(bean.getShort1() > 0);
        Assert.assertTrue(bean.getInt1() > 0);
        Assert.assertTrue(bean.getLong1() > 0);
        Assert.assertTrue(bean.getFloat1() > 0);
        Assert.assertTrue(bean.getDouble1() > 0);

        Assert.assertTrue(bean.getBigInteger().signum() > 0);
        Assert.assertTrue(bean.getBigDecimal().signum() > 0);

    }

    @Test
    public void testStrictNegatives() {

        Configuration configuration = new Configuration()
                .useStrictNegativeNumbers();
        SimpleBean bean = BeanRandom.random(SimpleBean.class, configuration);

        Assert.assertNotNull(bean);

        Assert.assertTrue(bean.getByte2() < 0);
        Assert.assertTrue(bean.getShort2() < 0);
        Assert.assertTrue(bean.getInt2() < 0);
        Assert.assertTrue(bean.getLong2() < 0);
        Assert.assertTrue(bean.getFloat2() < 0);
        Assert.assertTrue(bean.getDouble2() < 0);

        Assert.assertTrue(bean.getByte1() < 0);
        Assert.assertTrue(bean.getShort1() < 0);
        Assert.assertTrue(bean.getInt1() < 0);
        Assert.assertTrue(bean.getLong1() < 0);
        Assert.assertTrue(bean.getFloat1() < 0);
        Assert.assertTrue(bean.getDouble1() < 0);

        Assert.assertTrue(bean.getBigInteger().signum() < 0);
        Assert.assertTrue(bean.getBigDecimal().signum() < 0);
    }

}
