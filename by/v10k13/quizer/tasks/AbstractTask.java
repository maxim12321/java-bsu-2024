package by.v10k13.quizer.tasks;

import by.v10k13.quizer.Task;

public abstract class AbstractTask implements Task {
    private final String Text_;

    public AbstractTask(String text) {
        Text_ = text;
    }

    @Override
    public String getText() {
        return Text_;
    }
}