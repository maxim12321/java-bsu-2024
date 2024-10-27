package by.v10k13.quizer.tasks.math;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(double a, double b, Operators op) {
        super(AMTaskConfig.CreateFromValue(op.Function(a, b),0.001), (a + " " + op.toString() + " " + b + " = X") + "   Find X.");
    }
}
