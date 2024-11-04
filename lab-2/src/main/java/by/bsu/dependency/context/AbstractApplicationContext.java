package by.bsu.dependency.context;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractApplicationContext implements ApplicationContext {

    protected enum ContextStatus {
        NOT_STARTED,
        STARTED
    }

    protected <T> T instantiateBean(Class<T> beanClass) {
        try {
            return beanClass.getConstructor().newInstance();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRunning() {
        return contextStatus == ContextStatus.STARTED;
    }

    protected ContextStatus contextStatus = ContextStatus.NOT_STARTED;
}
