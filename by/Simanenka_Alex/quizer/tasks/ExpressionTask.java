package by.Simanenka_Alex.quizer.tasks;

import by.Simanenka_Alex.quizer.Result;
import by.Simanenka_Alex.quizer.Task;

import java.util.Objects;

public class ExpressionTask extends AbstractMathTask {


    public ExpressionTask(int leftArg, Operation op, int rightArg) {
        super(leftArg, op, rightArg);
    }

    @Override
    public String getText() {
        return Objects.toString(leftArg) + op.GetCh() + Objects.toString(rightArg) + "=";
    }

    @Override
    protected double calcAns() {
        return switch (op) {
            case ADD -> leftArg + rightArg;
            case SUB -> leftArg - rightArg;
            case MUL -> leftArg * rightArg;
            case DIV -> (double)leftArg / rightArg;
        };
    }
}
