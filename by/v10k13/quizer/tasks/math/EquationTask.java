package by.v10k13.quizer.tasks.math;

public class EquationTask extends AbstractMathTask {
    private static AMTaskConfig CreateConfig(boolean x_first, double b, double c, Operators op) {
        double answer;
        if (x_first) answer = op.InverseAFunction(b, c);
        else answer = op.InverseBFunction(b, c);
        return AMTaskConfig.CreateFromValue(answer, 0.1);
    }

    private static String CreateText(boolean x_first, double b, double c, Operators op) {
        if (x_first)
            return "X " + op.Symbol + " " + b + " = " + c;
        else
            return b + " " + op.Symbol + " X = " + c;
    }

    public EquationTask(boolean x_first, double arg, double ans, Operators op) {
        super(CreateConfig(x_first, arg, ans, op), CreateText(x_first, arg, ans, op));
    }
}
