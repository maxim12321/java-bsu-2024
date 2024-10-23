package by.MatsveiZorka.quizer.tasks;

public class ExpressionMathTask extends AbstractMathTask {
    public ExpressionMathTask(int a, int b, Operators op) {
        super((int)op.Function(a, b),
                Integer.toString(a) + op.Symbol + b
                        + "=X  (find X and give rounded to integer answer)");
    }
}
