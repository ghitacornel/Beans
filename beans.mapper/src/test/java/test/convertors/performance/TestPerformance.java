package test.convertors.performance;

import beans.Factory;
import beans.SourceBean;
import beans.TargetBean;
import beans.mapper.Mapper;
import beans.mapper.generators.java.JavaGenerator;
import convertors.JavassistCopy;
import convertors.ManualCopy;
import convertors.ManualTypedCopy;
import convertors.ReflectionCopy;
import org.dozer.DozerBeanMapper;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

public class TestPerformance {

    private static final int steps = 1000;
    private static final int size = 10000;
    final private static ManualCopy manualCopyConvertor = new ManualCopy();
    final private static ManualTypedCopy manualTypedCopyConvertor = new ManualTypedCopy();
    final private static ReflectionCopy reflectionConvertor = new ReflectionCopy();
    final private static Mapper<SourceBean, TargetBean> generatedConvertor = JavaGenerator.buildConvertor(SourceBean.class, TargetBean.class);
    final private static Mapper<SourceBean, TargetBean> javassistConvertor = JavassistCopy.getConverter(SourceBean.class, TargetBean.class);
    final private static DozerBeanMapper dozerConvertor = new DozerBeanMapper();
    private static SourceBean[] sourceBeans;
    private static TargetBean[] targetBeans;

    @BeforeClass
    public static void beforeAll() {
        System.out.println("Using " + size + " beans in " + steps + " steps");
        sourceBeans = Factory.generateRandomSourceBeans(size);
        targetBeans = Factory.generateRandomTargetBeans(size);
        System.out.println("Generated " + size + " beans for " + steps + " steps");
    }

    @Test
    public void testManualCopyConvertor() {
        System.out.println("with " + manualCopyConvertor);
        Date start = new Date();

        for (int j = 0; j < steps; j++) {
            for (int i = 0; i < size; i++) {
                SourceBean source = sourceBeans[i];
                TargetBean target = targetBeans[i];
                manualCopyConvertor.map(source, target);
            }
        }

        Date stop = new Date();
        System.out.println("millis diff = " + (stop.getTime() - start.getTime()));
    }

    @Test
    public void testManualCopyTypedConvertor() {
        System.out.println("with " + manualTypedCopyConvertor);
        Date start = new Date();

        for (int j = 0; j < steps; j++) {
            for (int i = 0; i < size; i++) {
                SourceBean source = sourceBeans[i];
                TargetBean target = targetBeans[i];
                manualTypedCopyConvertor.map(source, target);
            }
        }

        Date stop = new Date();
        System.out.println("millis diff = " + (stop.getTime() - start.getTime()));
    }

    @Ignore("timeout occurs")
    @Test(timeout = 500)
    public void testReflectionCopyConvertor() {

        System.out.println("with " + reflectionConvertor);
        Date start = new Date();

        for (int j = 0; j < steps; j++) {
            for (int i = 0; i < size; i++) {
                SourceBean source = sourceBeans[i];
                TargetBean target = targetBeans[i];
                reflectionConvertor.map(source, target);
            }
        }

        Date stop = new Date();
        System.out.println("millis diff = " + (stop.getTime() - start.getTime()));
    }

    @Test
    public void testGenerated() {

        System.out.println("with " + generatedConvertor);
        Date start = new Date();

        for (int j = 0; j < steps; j++) {
            for (int i = 0; i < size; i++) {
                SourceBean source = sourceBeans[i];
                TargetBean target = targetBeans[i];
                generatedConvertor.map(source, target);
            }
        }

        Date stop = new Date();
        System.out.println("millis diff = " + (stop.getTime() - start.getTime()));
    }

    @Test
    public void testJavassist() {

        System.out.println("with " + javassistConvertor);
        Date start = new Date();

        for (int j = 0; j < steps; j++) {
            for (int i = 0; i < size; i++) {
                SourceBean source = sourceBeans[i];
                TargetBean target = targetBeans[i];
                javassistConvertor.map(source, target);
            }
        }

        Date stop = new Date();
        System.out.println("millis diff = " + (stop.getTime() - start.getTime()));
    }

    @Ignore("timeout occurs")
    @Test(timeout = 500)
    public void testDozer() {

        System.out.println("with " + dozerConvertor);
        Date start = new Date();

        for (int j = 0; j < steps; j++) {
            for (int i = 0; i < size; i++) {
                SourceBean source = sourceBeans[i];
                TargetBean target = targetBeans[i];
                dozerConvertor.map(source, target);
            }
        }

        Date stop = new Date();
        System.out.println("millis diff = " + (stop.getTime() - start.getTime()));
    }
}
