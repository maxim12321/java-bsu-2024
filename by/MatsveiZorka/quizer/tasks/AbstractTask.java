package by.MatsveiZorka.quizer.tasks;

import by.MatsveiZorka.quizer.Task;

public abstract class AbstractTask implements Task {
    private final String Text_; // условие

    public AbstractTask(String text) {
        Text_ = text;
    }

    @Override
    public String getText() {
        return Text_;
    }
}
