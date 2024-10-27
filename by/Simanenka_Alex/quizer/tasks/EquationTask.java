package by.Simanenka_Alex.quizer.tasks;

import java.util.Objects;

public class EquationTask extends AbstractMathTask {

    public EquationTask(int leftArg, Operation op, int rightArg, boolean argIsRigth) {
        super(leftArg, op, rightArg);
        this.argIsRigth = argIsRigth;
    }

    @Override
    protected double calcAns() {
        if (argIsRigth) {
            return switch (op) {
                case ADD -> rightArg - leftArg;
                case SUB -> leftArg + rightArg;
                case MUL -> (double)rightArg / leftArg;
                case DIV -> leftArg * rightArg;
            };
        }
        return switch (op) {
            case ADD -> rightArg - leftArg;
            case SUB -> leftArg - rightArg;
            case MUL -> (double)rightArg / leftArg;
            case DIV -> (double)leftArg / rightArg;
        };
    }

    @Override
    public String getText() {
        if (argIsRigth) {
            return "x" + op.GetCh() + Objects.toString(leftArg) + "=" + Objects.toString(rightArg);
        }
        return Objects.toString(leftArg) + op.GetCh() + "x" + "=" + Objects.toString(rightArg);
    }

    public boolean isArgIsRigth() {
        return argIsRigth;
    }

    private boolean argIsRigth;
}
