package by.v10k13.quizer.tasks.math;


public class Equation2Task extends AbstractMathTask {
    public Equation2Task(double a, double c, MathTask.Operators op) {
        super(AMTaskConfig.CreateFromValue(op.InverseBFunction(a, c), 0.001), a + op.toString() + "X" + "=" + c + "   Find X.");
    }
}
