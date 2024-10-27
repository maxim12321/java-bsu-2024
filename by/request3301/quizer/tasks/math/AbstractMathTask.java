package by.request3301.quizer.tasks.math;

import by.request3301.quizer.MathEngine;
import by.request3301.quizer.Result;

public abstract class AbstractMathTask implements MathTask {
    protected String lhs, rhs;

    public AbstractMathTask(
            String lhs, String rhs
    ) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String getText() {
        return lhs + "=" + rhs;
    }

    public boolean checkEqual(String lhs, String rhs) {
        return MathEngine.eval(lhs) == MathEngine.eval(rhs);
    }

    public abstract Result validate(String answer);
}
