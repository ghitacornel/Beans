package test.tests.beans.ignored;

import org.junit.Assert;
import org.junit.Test;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;

public class TestIgnored {

    public static class Bean {

        String name;
        Integer integer;
        Boolean b;

        String witness;

    }

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
        configuration.getIgnoredFields().ignore("name");
        configuration.getIgnoredClasses().ignore(Integer.class);
        configuration.getIgnoredFields()
                .ignoreFieldForClass(Bean.class, "b");
        Bean bean = BeanRandom.random(Bean.class, configuration);

        Assert.assertNotNull(bean);
        Assert.assertNull(bean.name);
        Assert.assertNull(bean.integer);
        Assert.assertNull(bean.b);

        Assert.assertNotNull(bean.witness);

    }

}
