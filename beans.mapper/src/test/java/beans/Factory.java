package beans;

import beans.random.BeanRandom;

final public class Factory {

    private Factory() {
        // factory
    }

    public static SourceBean[] generateRandomSourceBeans(int size) {
        SourceBean[] result = new SourceBean[size];
        for (int i = 0; i < size; i++) {
            result[i] = BeanRandom.random(SourceBean.class);
        }
        return result;
    }

    public static TargetBean[] generateRandomTargetBeans(int size) {
        TargetBean[] result = new TargetBean[size];
        for (int i = 0; i < size; i++) {
            result[i] = BeanRandom.random(TargetBean.class);
        }
        return result;
    }
}
