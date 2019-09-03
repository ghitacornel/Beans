package test.tests.beans.ignored.classes;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class TestIgnored {

    @Test
    public void testOk() {

        Bean bean = BeanRandom.random(Bean.class);

        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.name);
        Assert.assertNotNull(bean.integer);
        Assert.assertNotNull(bean.b);

        Assert.assertNotNull(bean.witness);

    }

    @Test
    public void testIgnored() {

        Configuration configuration = new Configuration();
        configuration.getIgnoredClasses().ignore(Integer.class);
        Bean bean = BeanRandom.random(Bean.class, configuration);

        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.name);
        Assert.assertNull(bean.integer);
        Assert.assertNotNull(bean.b);

        Assert.assertNotNull(bean.witness);

    }

    public static class Bean {

        String name;
        Integer integer;
        Boolean b;

        String witness;

    }

}
