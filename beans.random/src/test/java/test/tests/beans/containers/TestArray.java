package test.tests.beans.containers;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;
import org.junit.Assert;
import org.junit.Test;
import test.beans.containers.array.A;
import test.beans.containers.array.B;
import test.beans.containers.array.C;
import test.beans.containers.array.D;

public class TestArray {

    @Test
    public void testNoLevel() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(0);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);

        Assert.assertNull(a.getArray());

    }

    @Test
    public void testOneLevel() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(1);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);

        Assert.assertNotNull(a.getArray());
        Assert.assertEquals(configuration.getCollectionsSize(),
                a.getArray().length);
        for (B b : a.getArray()) {
            Assert.assertNotNull(b);
            Assert.assertNull(b.getArray());

        }
    }

    @Test
    public void testTwoLevels() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(2);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);

        Assert.assertNotNull(a.getArray());
        Assert.assertEquals(configuration.getCollectionsSize(),
                a.getArray().length);
        for (B b : a.getArray()) {
            Assert.assertNotNull(b);

            Assert.assertNotNull(b.getArray());
            Assert.assertEquals(configuration.getCollectionsSize(),
                    b.getArray().length);
            for (C c : b.getArray()) {
                Assert.assertNotNull(c);
                Assert.assertNull(c.getArray());
            }

        }

    }

    @Test
    public void testAllLevels() {

        Configuration configuration = new Configuration();
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);

        Assert.assertNotNull(a.getArray());
        Assert.assertEquals(configuration.getCollectionsSize(),
                a.getArray().length);
        for (B b : a.getArray()) {
            Assert.assertNotNull(b);

            Assert.assertNotNull(b.getArray());
            Assert.assertEquals(configuration.getCollectionsSize(),
                    b.getArray().length);
            for (C c : b.getArray()) {
                Assert.assertNotNull(c);

                Assert.assertNotNull(c.getArray());
                Assert.assertEquals(configuration.getCollectionsSize(),
                        c.getArray().length);
                for (D d : c.getArray()) {
                    Assert.assertNotNull(d);
                    Assert.assertNotNull(d.getId());
                    Assert.assertNotNull(d.getName());
                }
            }

        }

    }
}
