package by.SanchukS.quizer.tasks.math;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Result;

public abstract class AbstractMathTask implements MathTask {
    String taskText;
    int rightAnswer;

    public AbstractMathTask(String taskText, int rightAnswer) {
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
