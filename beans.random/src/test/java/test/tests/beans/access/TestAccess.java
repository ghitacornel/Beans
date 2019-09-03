package test.tests.beans.access;

import beans.random.BeanRandom;
import beans.random.configuration.Configuration;
import beans.random.exceptions.NonPublicClassEncounteredException;
import beans.random.exceptions.WrappedInstantiationException;
import beans.reflection.exceptions.NoPublicNoArgumentConstructorClassException;
import org.junit.Assert;
import org.junit.Test;

public class TestAccess {

    @Test(expected = NoPublicNoArgumentConstructorClassException.class)
    public void testA1() {

        Configuration configuration = new Configuration()
                .raiseExceptionWhenPublicNoArgumentConstructorIsNotFound(true);
        BeanRandom.random(A.class, configuration);

    }

    @Test
    public void testA2() {

        A a = BeanRandom.random(A.class);
        Assert.assertNotNull(a);
        Assert.assertNull(a.dummyObject);

    }

    @Test(expected = NonPublicClassEncounteredException.class)
    public void testB1() {

        Configuration configuration = new Configuration()
                .raiseExceptionWhenNonPublicClassEncountered(true);
        BeanRandom.random(B.class, configuration);

    }

    @Test
    public void testB2() {
        B b = BeanRandom.random(B.class);
        Assert.assertNotNull(b);
        Assert.assertNull(b.bb);
    }

    @Test(expected = WrappedInstantiationException.class)
    public void testC() {
        BeanRandom.random(C.class);
    }

    public static class DummyObject {

        public DummyObject(int x, int y) {
        }
    }

    /**
     * class with data member with no default constructor
     *
     * @author cornel.ghita
     */
    public static class A {

        DummyObject dummyObject;

    }

    /**
     * class with data member not public class
     *
     * @author cornel.ghita
     */
    public static class B {

        private BB bb;

        private class BB {
        }

    }

    /**
     * class with exceptions on constructor
     *
     * @author cornel.ghita
     */
    public static class C {

        @SuppressWarnings("unused")
        private CC cc;

        public static class CC {

            public CC() {
                throw new RuntimeException();
            }
        }

    }

}
