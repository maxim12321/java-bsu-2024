package by.request3301.quizer;

public interface Task {
    String getText();

    Result validate(String answer);
}
