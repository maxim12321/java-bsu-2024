package by.chewy_pegasus.quizer.tasks.text;

import by.chewy_pegasus.quizer.Result;
import by.chewy_pegasus.quizer.Task;

import java.util.ArrayList;

public abstract class TextTask implements Task {
    protected String text;
    protected String correctAnswer;

    public TextTask(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getText() {
        return this.text;
    }

    abstract public Result validate(String answer);
}