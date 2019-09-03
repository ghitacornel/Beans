package test.tests.beans.statics;

import beans.random.BeanRandom;
import org.junit.Assert;
import org.junit.Test;

public class TestStatics {

    /**
     * simple test to prove statics are not affected
     */
    @Test
    public void test() {
        BeanRandom.random(BeanWithStaticDataMembers.class);
        Assert.assertNull(BeanWithStaticDataMembers.getString());
    }

    public static class BeanWithStaticDataMembers {

        private static String string;

        public static String getString() {
            return string;
        }

    }

}
