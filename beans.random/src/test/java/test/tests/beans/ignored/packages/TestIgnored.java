package test.tests.beans.ignored.packages;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;

public class TestIgnored {

    public static class Bean {

        String name;
        Timestamp timestamp;
        Boolean b;

        String witness;

    }

    @Test
    public void testOk() {

        Bean bean = BeanRandom.random(Bean.class);

        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.name);
        Assert.assertNotNull(bean.timestamp);
        Assert.assertNotNull(bean.b);

        Assert.assertNotNull(bean.witness);

    }

    @Test
    public void testIgnored() {

        Configuration configuration = new Configuration();
        configuration.getIgnoredPackages().ignore("java.sql");
        Bean bean = BeanRandom.random(Bean.class, configuration);

        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.name);
        Assert.assertNull(bean.timestamp);
        Assert.assertNotNull(bean.b);

        Assert.assertNotNull(bean.witness);

    }

}
