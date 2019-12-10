package convertors;

import beans.mapper.Mapper;
import beans.mapper.generators.java.GeneratorsUtils;
import beans.reflection.ReflectionUtils;
import javassist.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JavassistCopy {

    final private static String sourceParameterName = "source";
    final private static String targetParameterName = "target";
    final private static AtomicInteger count = new AtomicInteger();

    @SuppressWarnings("unchecked")
    public static <S, T> Mapper<S, T> getConverter(Class<S> source,
                                                   Class<T> target) {

        ClassPool pool = ClassPool.getDefault();

        CtClass newClass = pool.makeClass(JavassistCopy.class.getPackage().getName() + ".JavassistGenerated" + count.getAndIncrement());
        newClass.setModifiers(Modifier.FINAL);
        newClass.setModifiers(Modifier.PUBLIC);

        try {
            CtClass expectedInterface = pool.get(Mapper.class.getCanonicalName());
            newClass.addInterface(expectedInterface);
        } catch (Exception e) {
            throw new RuntimeException("cannot add implemented interface", e);
        }

        try {
            CtMethod method = CtNewMethod.make(buildClassBody(source, target), newClass);
            method.setModifiers(Modifier.FINAL);
            method.setModifiers(Modifier.PUBLIC);
            newClass.addMethod(method);
        } catch (Exception e) {
            throw new RuntimeException("cannot add method", e);
        }

        try {
            return ((Mapper<S, T>) newClass.toClass().getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException("cannot create instance", e);
        }
    }

    private static String buildClassBody(Class<?> source, Class<?> target) {
        Map<Method, Method> map = ReflectionUtils.matchGettersWithSetters(source, target);
        return "public void map(Object sourceBean, Object targetBean){"
                + "\n"
                + source.getCanonicalName()
                + " source = ("
                + source.getCanonicalName()
                + ") sourceBean;"
                + "\n"
                + target.getCanonicalName()
                + " target = ("
                + target.getCanonicalName()
                + ") targetBean;"
                + GeneratorsUtils.buildConversionMethodContent(map, sourceParameterName, targetParameterName)
                + "\n}";
    }

}
