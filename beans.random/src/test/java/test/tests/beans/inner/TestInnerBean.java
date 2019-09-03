package test.tests.beans.inner;

import beans.random.BeanRandom;
import org.junit.Assert;
import org.junit.Test;
import test.beans.inner.BeanWithInners;

public class TestInnerBean {

    @Test
    public void testInnerBeans() {

        BeanWithInners bean = BeanRandom.random(BeanWithInners.class);

        Assert.assertNotNull(bean);

        Assert.assertNotNull(bean.getStaticInnerBean());
        Assert.assertNotNull(bean.getStaticInnerBean().getName());

        Assert.assertNotNull(bean.getInnerBean());
        Assert.assertNotNull(bean.getInnerBean().getName());

        Assert.assertNull(bean.getPrivateStaticInnerBean());
        Assert.assertNull(bean.getPrivateInnerBean());

    }

}
