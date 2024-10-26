package KlimovMikh.quizer.tasks.math;

public abstract class AbstractMathTask implements MathTask {
    protected final int number1;
    protected final int number2;
    protected final Operation operation;
    public AbstractMathTask(int number1, int number2, Operation operation) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
    }
}
