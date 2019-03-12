package test.tests.beans.containers;

import org.junit.Assert;
import org.junit.Test;

import test.beans.containers.simple.A;
import beans.random.BeanRandom;
import beans.random.configuration.Configuration;

public class TestSimple {

    @Test
    public void testNoLevel() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(0);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);
        Assert.assertNotNull(a.getId());

        Assert.assertNull(a.getB());

    }

    @Test
    public void testOneLevel() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(1);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);
        Assert.assertNotNull(a.getId());

        Assert.assertNotNull(a.getB());
        Assert.assertNotNull(a.getB().getId());

        Assert.assertNull(a.getB().getC());

    }

    @Test
    public void testTwoLevels() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(2);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);
        Assert.assertNotNull(a.getId());

        Assert.assertNotNull(a.getB());
        Assert.assertNotNull(a.getB().getId());

        Assert.assertNotNull(a.getB().getC());
        Assert.assertNotNull(a.getB().getC().getId());

        Assert.assertNull(a.getB().getC().getD());

    }

    @Test
    public void testAllLevels() {

        Configuration configuration = new Configuration();
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);
        Assert.assertNotNull(a.getId());

        Assert.assertNotNull(a.getB());
        Assert.assertNotNull(a.getB().getId());

        Assert.assertNotNull(a.getB().getC());
        Assert.assertNotNull(a.getB().getC().getId());

        Assert.assertNotNull(a.getB().getC().getD());
        Assert.assertNotNull(a.getB().getC().getD().getId());

    }
}
