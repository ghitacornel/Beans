package test.tests.beans.statics;

import org.junit.Assert;
import org.junit.Test;

import beans.random.BeanRandom;

public class TestStatics {

    public static class BeanWithStaticDataMembers {

        private static String string;

        public static String getString() {
            return string;
        }

    }

    /**
     * simple test to prove statics are not affected
     */
    @Test
    public void test() {
        BeanRandom.random(BeanWithStaticDataMembers.class);
        Assert.assertNull(BeanWithStaticDataMembers.getString());
    }

}
