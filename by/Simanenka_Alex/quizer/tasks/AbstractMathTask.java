package by.Simanenka_Alex.quizer.tasks;

import by.Simanenka_Alex.quizer.Result;

import java.util.Objects;

public abstract class AbstractMathTask implements MathTask{


    public AbstractMathTask(int leftArg, Operation op, int rightArg) {
        this.leftArg = leftArg;
        this.op = op;
        this.rightArg = rightArg;
    }

    @Override
    public Result validate(String answer) {
        double myAns = calcAns();
        if (!isValid(answer)) {
            return Result.INCORRECT_INPUT;
        }
        double studAns = Double.parseDouble(answer);
        if (Math.abs(myAns - studAns) < 0.0001) {
            return Result.OK;
        }
        return Result.WRONG;
    }

    protected abstract double calcAns();

    protected boolean isValid(String answer) {
        try {
            double a = Double.parseDouble(answer);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public int getLeftArg() {
        return leftArg;
    }

    public int getRightArg() {
        return rightArg;
    }

    public Operation getOp() {
        return op;
    }

    protected int leftArg;
    protected int rightArg;
    protected Operation op;
}
