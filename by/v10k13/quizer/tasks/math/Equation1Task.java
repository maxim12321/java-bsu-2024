package by.v10k13.quizer.tasks.math;

public class Equation1Task extends AbstractMathTask {
    public Equation1Task(double b, double c, Operators op) {
        super(AMTaskConfig.CreateFromValue(op.InverseAFunction(b, c), 0.01), "X" + op.toString() + b + "=" + c + "   Find X.");
    }
}
