package by.maxsavitsky.quizer.tasks.math;

import by.maxsavitsky.quizer.Result;

public abstract class AbstractMathTask implements MathTask {

    protected final int answer;

    protected AbstractMathTask(int answer) {
        this.answer = answer;
    }

    @Override
    public Result validate(String answer) {
        try {
            return this.answer == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }

}
