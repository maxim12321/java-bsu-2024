package by.bsu.dependency.context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import by.bsu.dependency.exceptions.*;

import by.bsu.dependency.annotation.Bean;


public class HardCodedSingletonApplicationContext extends AbstractApplicationContext {

    private final Map<String, Class<?>> beanDefinitions;
    private final Map<String, Object> beans = new HashMap<>();

    /**
     * ! Класс существует только для базового примера !
     * <br/>
     * Создает контекст, содержащий классы, переданные в параметре. Полагается на отсутсвие зависимостей в бинах,
     * а также на наличие аннотации {@code @Bean} на переданных классах.
     * <br/>
     * ! Контекст данного типа не занимается внедрением зависимостей !
     * <br/>
     * ! Создает только бины со скоупом {@code SINGLETON} !
     *
     * @param beanClasses классы, из которых требуется создать бины
     */
    public HardCodedSingletonApplicationContext(Class<?>... beanClasses) {
        this.beanDefinitions = Arrays.stream(beanClasses).collect(
                Collectors.toMap(
                        beanClass -> beanClass.getAnnotation(Bean.class).name(),
                        Function.identity()
                )
        );
    }

    @Override
    public void start() {
        beanDefinitions.forEach((beanName, beanClass) -> beans.put(beanName, instantiateBean(beanClass)));
        contextStatus = ContextStatus.STARTED;
    }

    @Override
    public boolean containsBean(String name) {
        if (!isRunning()) {
            throw new ApplicationContextNotStartedException();
        }
        return beanDefinitions.containsKey(name);
    }

    @Override
    public Object getBean(String name) {
        if (!isRunning()) {
            throw new ApplicationContextNotStartedException();
        }
        if (!beanDefinitions.containsKey(name)) {
            throw new NoSuchBeanDefinitionException(name);
        }
        return beans.get(name);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        if (!isRunning()) {
            throw new ApplicationContextNotStartedException();
        }
        String name = clazz.getAnnotation(Bean.class).name();
        if (!beanDefinitions.containsKey(name)) {
            throw new NoSuchBeanDefinitionException(name);
        }
        return (T) beans.get(name);
    }

    @Override
    public boolean isPrototype(String name) {
        if (!beanDefinitions.containsKey(name)) {
            throw new NoSuchBeanDefinitionException(name);
        }
        return false;
    }

    @Override
    public boolean isSingleton(String name) {
        if (!beanDefinitions.containsKey(name)) {
            throw new NoSuchBeanDefinitionException(name);
        }
        return true;
    }
}
