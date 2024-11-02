package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.exceptions.NullArgumentException;
import by.SanchukS.quizer.tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask {
    public EquationTask(Expression e, boolean variableRight) {
        super(getExpressionString(e, variableRight), getExpressionNumber(e, variableRight));
    };

    private static String getExpressionString(Expression e, boolean variableRight) {
        if (e == null) throw new NullArgumentException("expression");
        if (variableRight)
            return e.getNumber(0) + e.getOperation().toString() + "x=" + e.getNumber(2);
        else
            return "x" + e.getOperation() + e.getNumber(1) + "=" + e.getNumber(2);
    }

    private static int getExpressionNumber(Expression e, boolean variableRight) {
        if (e == null) throw new NullArgumentException("expression");
        if (variableRight)
            return e.getNumber(1);
        else
            return e.getNumber(0);
    }
}

