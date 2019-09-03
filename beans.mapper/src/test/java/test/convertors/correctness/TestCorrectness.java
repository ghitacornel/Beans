package test.convertors.correctness;

import beans.SourceBean;
import beans.TargetBean;
import beans.mapper.generators.java.JavaGenerator;
import beans.random.BeanRandom;
import beans.reflection.ReflectionUtils;
import convertors.JavassistCopy;
import convertors.ManualCopy;
import convertors.ManualTypedCopy;
import convertors.ReflectionCopy;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;

public class TestCorrectness {

    @Test
    public void testManualCopyConvertor() {

        SourceBean source = BeanRandom.random(SourceBean.class);
        TargetBean target = BeanRandom.random(TargetBean.class);
        new ManualCopy().map(source, target);

        Assert.assertTrue(ReflectionUtils.equalsReflection(source, target));

    }

    @Test
    public void testManualCopyTypedConvertor() {

        SourceBean source = BeanRandom.random(SourceBean.class);
        TargetBean target = BeanRandom.random(TargetBean.class);
        new ManualTypedCopy().map(source, target);

        Assert.assertTrue(ReflectionUtils.equalsReflection(source, target));
    }

    @Test
    public void testReflectionCopyConvertor() {

        SourceBean source = BeanRandom.random(SourceBean.class);
        TargetBean target = BeanRandom.random(TargetBean.class);
        new ReflectionCopy().map(source, target);

        Assert.assertTrue(ReflectionUtils.equalsReflection(source, target));
    }

    @Test
    public void testGenerated() {

        SourceBean source = new SourceBean();
        TargetBean target = new TargetBean();
        JavaGenerator.buildConvertor(SourceBean.class, TargetBean.class).map(
                source, target);

        Assert.assertTrue(ReflectionUtils.equalsReflection(source, target));

    }

    @Test
    public void testJavassist() {

        SourceBean source = new SourceBean();
        TargetBean target = new TargetBean();
        JavassistCopy.getConverter(SourceBean.class, TargetBean.class).map(
                source, target);

        Assert.assertTrue(ReflectionUtils.equalsReflection(source, target));

    }

    @Test
    public void testDozer() {

        SourceBean source = new SourceBean();
        TargetBean target = new TargetBean();

        // XXX Dozer fails when mapping java.lang.Object
        target.setO(source.getO());

        new DozerBeanMapper().map(source, target);

        Assert.assertTrue(ReflectionUtils.equalsReflection(source, target));

    }
}
