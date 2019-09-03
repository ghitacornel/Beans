package reflection;

import beans.SourceBean;
import beans.reflection.ReflectionUtils;
import beans.reflection.utils.GettersUtils;
import beans.reflection.utils.SettersUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class TestMapGettersSetters {

    @Test
    public void test() {
        Map<Method, Method> map = ReflectionUtils.matchGettersWithSetters(
                SourceBean.class, SourceBean.class);
        Map<String, Method> getters = GettersUtils.getGetters(SourceBean.class);
        Map<String, Method> setters = SettersUtils.getSetters(SourceBean.class);

        Assert.assertEquals(getters.size(), map.size());
        Assert.assertEquals(setters.size(), map.size());

        Assert.assertTrue(map.keySet().containsAll(getters.values()));
        Assert.assertTrue(getters.values().containsAll(map.keySet()));

        Assert.assertTrue(map.values().containsAll(setters.values()));
        Assert.assertTrue(setters.values().containsAll(map.values()));

        for (Method getter : map.keySet()) {
            Method setter = map.get(getter);
            Assert.assertTrue(setter.getParameterTypes()[0]
                    .isAssignableFrom(getter.getReturnType()));
            Assert.assertEquals(GettersUtils.extractGetterPropertyName(getter),
                    SettersUtils.extractSetterPropertyName(setter));
        }

    }

}
