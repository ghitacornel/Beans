package convertors;

import java.lang.reflect.Method;
import java.util.Map;

import beans.SourceBean;
import beans.TargetBean;
import beans.mapper.Mapper;
import beans.reflection.ReflectionUtils;

public class ReflectionCopy implements Mapper<SourceBean, TargetBean> {

    final private Map<Method, Method> map = ReflectionUtils.matchGettersWithSetters(
            SourceBean.class, TargetBean.class);

    @Override
    final public void map(SourceBean source, TargetBean target) {
        for (Method getter : map.keySet()) {
            Method setter = map.get(getter);
            try {
                setter.invoke(target, getter.invoke(source));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
