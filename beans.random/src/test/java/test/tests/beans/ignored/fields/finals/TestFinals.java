package test.tests.beans.ignored.fields.finals;

import org.junit.Assert;
import org.junit.Test;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;

public class TestFinals {

    private static class BeanWithFinalDataMembers {

        private final Integer finalInteger = -100;

        public Integer getFinalInteger() {
            return finalInteger;
        }

    }

    @Test
    public void testDoesNotAffectFinals() {

        BeanWithFinalDataMembers bean = new BeanWithFinalDataMembers();
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() < 0);

        BeanRandom.populate(bean,
                new Configuration().useStrictPositiveNumbers());

        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() < 0);
    }

    @Test
    public void testAffectFinals() {

        BeanWithFinalDataMembers bean = new BeanWithFinalDataMembers();
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() < 0);

        Configuration configuration = new Configuration()
                .useStrictPositiveNumbers();
        configuration.getIgnoredFields().setIgnoreFinals(false);
        BeanRandom.populate(bean, configuration);

        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.getFinalInteger());
        Assert.assertTrue(bean.getFinalInteger() > 0);
    }

}
