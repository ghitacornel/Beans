package test.beans.inner;

public class BeanWithInners {

    private StaticInnerBean staticInnerBean;
    private InnerBean innerBean;
    private PrivateStaticInnerBean privateStaticInnerBean;
    private PrivateInnerBean privateInnerBean;

    public static class StaticInnerBean {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class InnerBean {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class PrivateStaticInnerBean {

        private String name;

        @SuppressWarnings("unused")
        public String getName() {
            return name;
        }

        @SuppressWarnings("unused")
        public void setName(String name) {
            this.name = name;
        }
    }

    private class PrivateInnerBean {

        private String name;

        @SuppressWarnings("unused")
        public String getName() {
            return name;
        }

        @SuppressWarnings("unused")
        public void setName(String name) {
            this.name = name;
        }
    }

    public StaticInnerBean getStaticInnerBean() {
        return staticInnerBean;
    }

    public void setStaticInnerBean(StaticInnerBean innerBean) {
        this.staticInnerBean = innerBean;
    }

    public InnerBean getInnerBean() {
        return innerBean;
    }

    public void setInnerBean(InnerBean innerBean) {
        this.innerBean = innerBean;
    }

    public PrivateStaticInnerBean getPrivateStaticInnerBean() {
        return privateStaticInnerBean;
    }

    public void setPrivateStaticInnerBean(PrivateStaticInnerBean privateStaticInnerBean) {
        this.privateStaticInnerBean = privateStaticInnerBean;
    }

    public PrivateInnerBean getPrivateInnerBean() {
        return privateInnerBean;
    }

    public void setPrivateInnerBean(PrivateInnerBean privateInnerBean) {
        this.privateInnerBean = privateInnerBean;
    }

}
