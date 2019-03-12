package reflection;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import beans.reflection.utils.SettersUtils;

public class TestSetters {

    private static class BeanWithOneSetter {

        @SuppressWarnings("unused")
        public void setObject(Object value) {
        }

    }

    private static class BeanWithNoSetters {

        @SuppressWarnings("unused")
        public void setObjectNoParameters() {
        }

        @SuppressWarnings("unused")
        public void setObjectManyParameters(Object value1, Object value2) {
        }

        @SuppressWarnings("unused")
        public void setObjectManyParameters(Object... value) {
        }

        @SuppressWarnings("unused")
        public Object setObjectWithReturn(Object value) {
            return value;
        }

        @SuppressWarnings("unused")
        private void setPrivateObject(Object value) {
        }

        @SuppressWarnings("unused")
        protected void setProtectedObject(Object value) {
        }

        @SuppressWarnings("unused")
        void setDefautObject(Object value) {
        }

        @SuppressWarnings("unused")
        public static void setStaticObject(Object value) {
        }

        @SuppressWarnings("unused")
        public void aMethod(Object value) {
        }

        @SuppressWarnings("unused")
        public void setVoid(Void value) {
        }

    }

    @Test
    public void testOneSetter() {
        Map<String, Method> setters = SettersUtils.getSetters(BeanWithOneSetter.class);
        Assert.assertEquals(1, setters.size());
        Assert.assertNotNull(setters.get("Object"));
        Assert.assertEquals("setObject", setters.get("Object").getName());
    }

    @Test
    public void testNoSetters() {
        Map<String, Method> setters = SettersUtils.getSetters(BeanWithNoSetters.class);
        Assert.assertTrue(setters.isEmpty());
    }
}
