package test.tests.beans.containers;

import beans.random.BeanRandom;
import org.junit.Test;

import java.util.Map;

public class TestMap {

    @Test(expected = RuntimeException.class)
    public void test() {
        BeanRandom.random(Bean.class);
    }

    public static class Bean {

        @SuppressWarnings("unused")
        private Map<?, ?> map;
    }
}
