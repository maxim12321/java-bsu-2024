package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.Task;

public class EquationTask implements Task {
    private final int firstNumber;
    private final int secondNumber;
    private final int thirdNumber;
    private final int answer;

    private final String operation;

    /**
     *
     * @param firstNumber - если null, то это x
     * @param operation - операция
     * @param secondNumber - если null, то это x
     * @param thirdNumber -
     */
    public EquationTask(int firstNumber, String operation, int secondNumber, int thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
        this.operation = operation;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public Result validate(String answer) {
        return null;
    }
}
