package by.bsu.dependency.context;

import by.bsu.dependency.annotation.Bean;
import by.bsu.dependency.annotation.BeanScope;
import by.bsu.dependency.annotation.Inject;
import by.bsu.dependency.exceptions.ApplicationContextNotStartedException;
import by.bsu.dependency.exceptions.NoSuchBeanDefinitionException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleApplicationContext extends AbstractApplicationContext {

    private final Map<String, Class<?>> beanDefinitions;
    private final Map<String, Object> singletons = new HashMap<>();

    /**
     * Создает контекст, содержащий классы, переданные в параметре.
     * <br/>
     * Если на классе нет аннотации {@code @Bean}, имя бина получается из названия класса, скоуп бина по дефолту
     * считается {@code Singleton}.
     * <br/>
     * Подразумевается, что у всех классов, переданных в списке, есть конструктор без аргументов.
     *
     * @param beanClasses классы, из которых требуется создать бины
     */

    public SimpleApplicationContext(Class<?>... beanClasses) {
        this.beanDefinitions = Arrays.stream(beanClasses).collect(
                Collectors.toMap(
                        beanClass ->
                                beanClass.isAnnotationPresent(Bean.class) ?
                                        beanClass.getAnnotation(Bean.class).name() :
                                        generateBeanName(beanClass),
                        Function.identity()
                )
        );
    }

    /**
     * Помимо прочего, метод должен заниматься внедрением зависимостей в создаваемые объекты
     */
    @Override
    public void start() {
        beanDefinitions.forEach(
                (beanName, beanClass) -> {
                    if (!beanClass.isAnnotationPresent(Bean.class) ||
                            beanClass.getAnnotation(Bean.class).scope() == BeanScope.SINGLETON) {
                        singletons.put(beanName, instantiateBean(beanClass));
                    }
                }
        );
        contextStatus = ContextStatus.STARTED;
        singletons.forEach(this::inject);
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
        if (singletons.containsKey(name)) {
            return singletons.get(name);
        } else {
            Object bean = instantiateBean(beanDefinitions.get(name));
            inject(name, bean);
            return bean;
        }
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        if (!isRunning()) {
            throw new ApplicationContextNotStartedException();
        }
        String name = clazz.isAnnotationPresent(Bean.class) ? clazz.getAnnotation(Bean.class).name() : generateBeanName(clazz);
        if (!beanDefinitions.containsKey(name)) {
            throw new NoSuchBeanDefinitionException(name);
        }
        if (singletons.containsKey(name)) {
            return (T) singletons.get(name);
        } else {
            T bean = (T) instantiateBean(beanDefinitions.get(name));
            inject(name, bean);
            return bean;
        }
    }

    @Override
    public boolean isPrototype(String name) {
        return !isSingleton(name);
    }

    @Override
    public boolean isSingleton(String name) {
        if (!beanDefinitions.containsKey(name)) {
            throw new NoSuchBeanDefinitionException(name);
        }
        return !beanDefinitions.get(name).isAnnotationPresent(Bean.class) ||
                beanDefinitions.get(name).getAnnotation(Bean.class).scope() == BeanScope.SINGLETON;
    }

    private String generateBeanName(Class<?> clazz) {
        String name = clazz.getSimpleName();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    private void inject(String beanName, Object bean) {
        Arrays.stream(beanDefinitions.get(beanName).getDeclaredFields()).toList()
                .forEach(
                field -> {
                    if (field.isAnnotationPresent(Inject.class)) {
                        try {
                            field.setAccessible(true);
                            field.set(bean, getBean(field.getType()));
                        } catch (IllegalAccessException ignored) {

                        }
                    }
                }
        );
    }
}
