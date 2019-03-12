package test.tests.beans.containers;

import java.util.Map;

import org.junit.Test;

import beans.random.BeanRandom;

public class TestMap {

    public static class Bean {

        @SuppressWarnings("unused")
        private Map<?, ?> map;
    }

    @Test(expected = RuntimeException.class)
    public void test() {
        BeanRandom.random(Bean.class);
    }
}
