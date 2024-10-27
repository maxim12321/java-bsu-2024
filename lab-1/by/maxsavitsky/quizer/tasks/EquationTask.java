package by.maxsavitsky.quizer.tasks;

import by.maxsavitsky.quizer.tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask {

    private final String text;

    public EquationTask(String text, int answer) {
        super(answer);
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

}
