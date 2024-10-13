package by.mmaxemm.quizer.tasks;

import by.mmaxemm.quizer.Result;

public class TextTask implements Task {
    @Override
    public String getText() {
        return "";
    }

    @Override
    public Result validate(String answer) {
        return null;
    }
}
