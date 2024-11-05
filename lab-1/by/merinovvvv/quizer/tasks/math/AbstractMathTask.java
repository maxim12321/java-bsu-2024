package by.merinovvvv.quizer.tasks.math;

import by.merinovvvv.quizer.Result;

abstract public class AbstractMathTask implements MathTask {

    abstract public String getText();

    abstract public Result validate(String answer);

}
