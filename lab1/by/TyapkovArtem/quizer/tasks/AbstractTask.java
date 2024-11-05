package lab1.by.TyapkovArtem.quizer.tasks;

import lab1.by.TyapkovArtem.quizer.Result;
import lab1.by.TyapkovArtem.quizer.Task;

public abstract class AbstractTask implements Task {
    private final String text_;

    public AbstractTask(String text) {
        text_ = text;
    }

    @Override
    public String getText() {
        return text_;
    }
}
