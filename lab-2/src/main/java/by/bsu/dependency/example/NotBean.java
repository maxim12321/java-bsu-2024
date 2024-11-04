package by.bsu.dependency.example;

import by.bsu.dependency.annotation.Bean;

public class NotBean {

    void printSomething() {
        System.out.println("Hello, I'm NOT a bean");
    }

    void doSomething() {
        System.out.println("NOT a bean is working on a project...");
    }
}
