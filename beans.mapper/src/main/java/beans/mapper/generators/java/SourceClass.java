package beans.mapper.generators.java;

import beans.mapper.Mapper;
import beans.reflection.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The source code
 *
 * @author Cornel
 */
class SourceClass {

    final private static String PACKAGE_NAME = SourceClass.class.getPackage()
            .getName();
    final private static String CLASS_NAME = "GeneratedOnTheFly";
    final private static String sourceParameterName = "source";
    final private static String targetParameterName = "target";
    final private static AtomicInteger count = new AtomicInteger();

    final private Class<?> source;
    final private Class<?> target;
    final private int id;
    final private StringBuilder content = new StringBuilder("");
    final private String className;
    public SourceClass(Class<?> source, Class<?> target) {
        id = count.getAndIncrement();
        className = CLASS_NAME + id;
        this.source = source;
        this.target = target;
    }

    private void addPackage() {
        content.append("package ").append(PACKAGE_NAME).append(";\n");
    }

    private void addImports() {
        content.append("import ").append(source.getCanonicalName()).append(";\n");
        content.append("import ").append(target.getCanonicalName()).append(";\n");
    }

    private String buildClassBody() {
        Map<Method, Method> map = ReflectionUtils.matchGettersWithSetters(
                source, target);
        return "final public void map(final "
                + source.getCanonicalName()
                + " "
                + sourceParameterName
                + ", final "
                + target.getCanonicalName()
                + " "
                + targetParameterName
                + "){"
                + GeneratorsUtils.buildConversionMethodContent(map,
                sourceParameterName, targetParameterName) + "\n}";
    }

    private void addClassDeclaration(String body) {
        content.append("public final class ");
        content.append(className);
        content.append(" implements ").append(Mapper.class.getPackage().getName()).append(".").append(Mapper.class.getSimpleName()).append("<").append(source.getCanonicalName()).append(",").append(target.getCanonicalName()).append("> {\n");
        content.append(body);
        content.append("\n}");
    }

    /**
     * @return the source code
     */
    public String getContent() {
        addPackage();
        addImports();
        addClassDeclaration(buildClassBody());

        // TODO output just for logging
        System.out.println(content);

        return content.toString();
    }

    public String getCanonicalClassName() {
        return PACKAGE_NAME + '.' + className;
    }
}
