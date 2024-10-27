package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.Task;
import by.SanchukS.quizer.tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(Expression e) {
        super(e.getNumber(0) + e.getOperation() + e.getNumber(1), e.getNumber(2));
    }
}
