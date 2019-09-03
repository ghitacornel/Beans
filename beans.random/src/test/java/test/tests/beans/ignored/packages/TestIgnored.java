package test.tests.beans.ignored.packages;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class TestIgnored {

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

    public static class Bean {

        String name;
        Timestamp timestamp;
        Boolean b;

        String witness;

    }

}
