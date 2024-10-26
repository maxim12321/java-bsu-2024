package by.Alesia.quizer.tasks.math;

import by.Alesia.quizer.Result;

public abstract class AbstractMathTask implements MathTask{
    protected final Operation operation;
    protected final int first;
    protected final int second;
    protected final int answer;

    protected AbstractMathTask(Operation operation, int first, int second) {
        this.operation = operation;
        this.first = first;
        this.second = second;
        this.answer = answer();
    }


    @Override
    public Result validate(String answer) {
        try {
            return this.answer == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    public abstract int answer();
}