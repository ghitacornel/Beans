package test.tests.beans.containers;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;
import org.junit.Assert;
import org.junit.Test;
import test.beans.containers.list.A;
import test.beans.containers.list.B;
import test.beans.containers.list.C;
import test.beans.containers.list.D;

public class TestList {

    @Test
    public void testNoLevel() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(0);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);

        Assert.assertNull(a.getList());

    }

    @Test
    public void testOneLevel() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(1);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);

        Assert.assertNotNull(a.getList());
        Assert.assertEquals(configuration.getCollectionsSize(), a.getList()
                .size());
        for (B b : a.getList()) {
            Assert.assertNotNull(b);
            Assert.assertNull(b.getList());
        }

    }

    @Test
    public void testTwoLevels() {

        Configuration configuration = new Configuration().setContainmentDepthLevel(2);
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);

        Assert.assertNotNull(a.getList());
        Assert.assertEquals(configuration.getCollectionsSize(), a.getList()
                .size());
        for (B b : a.getList()) {
            Assert.assertNotNull(b);

            Assert.assertNotNull(b.getList());
            Assert.assertEquals(configuration.getCollectionsSize(), b.getList()
                    .size());
            for (C c : b.getList()) {
                Assert.assertNotNull(c);
                Assert.assertNull(c.getList());
            }

        }

    }

    @Test
    public void testAllLevels() {

        Configuration configuration = new Configuration();
        A a = BeanRandom.random(A.class, configuration);

        Assert.assertNotNull(a);
        Assert.assertNotNull(a.getList());
        Assert.assertEquals(configuration.getCollectionsSize(), a.getList()
                .size());
        for (B b : a.getList()) {
            Assert.assertNotNull(b);
            Assert.assertNotNull(b.getList());
            Assert.assertEquals(configuration.getCollectionsSize(), b.getList()
                    .size());
            for (C c : b.getList()) {
                Assert.assertNotNull(c);
                Assert.assertNotNull(c.getList());
                Assert.assertEquals(configuration.getCollectionsSize(), c
                        .getList().size());
                for (D d : c.getList()) {
                    Assert.assertNotNull(d);
                    Assert.assertNotNull(d.getId());
                    Assert.assertNotNull(d.getName());
                }
            }
        }

    }
}
