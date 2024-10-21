package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Result;

public class AbstractMathTask implements MathTask{
    @Override
    public int calculate() {
        return 0;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public Result validate(String answer) {
        return null;
    }
}
