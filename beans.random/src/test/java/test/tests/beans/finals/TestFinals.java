package test.tests.beans.finals;

import org.junit.Assert;
import org.junit.Test;

import test.beans.finals.BeanWithFinals;
import beans.random.BeanRandom;
import beans.random.configuration.Configuration;

public class TestFinals {

    @Test
    public void testDoesNotAffectFinals() {

        BeanWithFinals bean = new BeanWithFinals();
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() < 0);

        BeanRandom.populate(bean, new Configuration().useStrictPositiveNumbers());
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() < 0);
    }

    @Test
    public void testAffectFinals() {

        BeanWithFinals bean = new BeanWithFinals();
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() < 0);

        Configuration configuration = new Configuration().useStrictPositiveNumbers();
        configuration.getIgnoredFields().setIgnoreFinals(false);
        BeanRandom.populate(bean, configuration);
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() > 0);
    }

}
