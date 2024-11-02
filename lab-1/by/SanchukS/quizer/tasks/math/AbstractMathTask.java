package by.SanchukS.quizer.tasks.math;

import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.exceptions.NullArgumentException;

public abstract class AbstractMathTask implements MathTask {
    private final String taskText;
    private final int rightAnswer;

    public AbstractMathTask(String taskText, int rightAnswer) {
        if (taskText == null) throw new NullArgumentException("taskText");
        this.taskText = taskText;
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String getText() {
        return taskText;
    }

    @Override
    public Result validate(String answer) {
        try {
            int userAnswer = Integer.parseInt(answer);
            return userAnswer == rightAnswer ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
