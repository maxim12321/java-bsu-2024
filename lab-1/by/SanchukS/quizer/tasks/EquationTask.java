package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask {
    public EquationTask(Expression e, boolean variableRight) {
        super(variableRight ? e.getNumber(0) + e.getOperation() + "x=" + e.getNumber(2)
                : "x" + e.getOperation() + e.getNumber(1) + "=" + e.getNumber(2),
              variableRight ? e.getNumber(1) : e.getNumber(0)
        );
    };
}
