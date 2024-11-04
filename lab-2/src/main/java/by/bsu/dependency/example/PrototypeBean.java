package by.bsu.dependency.example;

import by.bsu.dependency.annotation.Bean;
import by.bsu.dependency.annotation.BeanScope;
import by.bsu.dependency.annotation.Inject;

@Bean(name = "prototypeBean", scope = BeanScope.PROTOTYPE)
public class PrototypeBean {

    @Inject
    private FirstBean randomNameForFirstBean;

    @Inject
    private NotBean notBean;

    public void printSomething() {
        System.out.println("Hello, I'm prototype bean");
    }

    public void doSomething() {
        randomNameForFirstBean.printSomething();
        notBean.printSomething();
    }
}
