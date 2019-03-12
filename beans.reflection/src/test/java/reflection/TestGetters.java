package reflection;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import beans.reflection.utils.GettersUtils;

public class TestGetters {

    private static class Bean1 {

        @SuppressWarnings("unused")
        public boolean isBoolean() {
            return true;
        }

    }

    private static class Bean2 {

        @SuppressWarnings("unused")
        public Boolean getBoolean() {
            return null;
        }

    }

    private static class BeanWithOneGetter {

        @SuppressWarnings("unused")
        public Object getObject() {
            return null;
        }

    }

    private static class BeanWithNoGetters {

        @SuppressWarnings("unused")
        public boolean getBoolean() {
            return true;
        }

        @SuppressWarnings("unused")
        public Boolean isBoolean() {
            return null;
        }

        @SuppressWarnings("unused")
        private Object getPrivateObject() {
            return null;
        }

        @SuppressWarnings("unused")
        protected Object getProtectedObject() {
            return null;
        }

        @SuppressWarnings("unused")
        Object getDefaultObject() {
            return null;
        }

        @SuppressWarnings("unused")
        public static Object getStaticObject() {
            return null;
        }

        @SuppressWarnings("unused")
        public Object getObjectWithReturn(Object parameter) {
            return null;
        }

        @SuppressWarnings("unused")
        public Object getObjectsWithReturn(Object... parameters) {
            return null;
        }

        @SuppressWarnings("unused")
        public Object aMethod() {
            return null;
        }

        @SuppressWarnings("unused")
        public Void getVoid() {
            return null;
        }

    }

    @Test
    public void testBoolean1() {
        Map<String, Method> getters = GettersUtils.getGetters(Bean1.class);
        Assert.assertEquals(1, getters.size());
        Assert.assertNotNull(getters.get("Boolean"));
        Assert.assertEquals("isBoolean", getters.get("Boolean").getName());
    }

    @Test
    public void testBoolean2() {
        Map<String, Method> getters = GettersUtils.getGetters(Bean2.class);
        Assert.assertEquals(1, getters.size());
        Assert.assertNotNull(getters.get("Boolean"));
        Assert.assertEquals("getBoolean", getters.get("Boolean").getName());
    }

    @Test
    public void testOneGetter() {
        Map<String, Method> getters = GettersUtils.getGetters(BeanWithOneGetter.class);
        Assert.assertEquals(1, getters.size());
        Assert.assertNotNull(getters.get("Object"));
        Assert.assertEquals("getObject", getters.get("Object").getName());
    }

    @Test
    public void testNoGetters() {
        Map<String, Method> getters = GettersUtils.getGetters(BeanWithNoGetters.class);
        Assert.assertTrue(getters.isEmpty());
    }
}
