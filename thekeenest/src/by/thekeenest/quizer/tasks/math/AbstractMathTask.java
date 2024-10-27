package by.thekeenest.quizer.tasks.math;
import by.thekeenest.quizer.Result;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public abstract class AbstractMathTask implements MathTask{
    protected final int firstNumber;
    protected final int secondNumber;
    protected final Operation operation;

    protected AbstractMathTask(int firstNumber, int secondNumber, Operation operation) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operation = operation;
    }

    @Override
    public Set<Operation> getOperations() {
        return EnumSet.of(operation);
    }

    @Override
    public Result validate(String answer) {
        if (answer == null || answer.isBlank()) {
            return Result.INCORRECT_INPUT;
        }
        try {
            int val = Integer.parseInt(answer);
            return (val == getCorrectAnswer()) ? Result.OK : Result.WRONG;
        } catch (Exception e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
