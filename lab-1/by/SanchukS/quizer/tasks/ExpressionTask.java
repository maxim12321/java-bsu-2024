package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.exceptions.NullArgumentException;
import by.SanchukS.quizer.tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(Expression e) {
        super(getExpressionString(e), getExpressionNumber(e));
    }

    private static String getExpressionString(Expression e) {
        if (e == null) throw new NullArgumentException("expression");
        return e.getNumber(0) + e.getOperation().toString() + e.getNumber(1) + "=?";
    }

    private static int getExpressionNumber(Expression e) {
        if (e == null) throw new NullArgumentException("expression");
        return e.getNumber(2);
    }
}
