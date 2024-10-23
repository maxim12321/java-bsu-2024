package by.MatsveiZorka.quizer.tasks;

public class EquationMathTask extends AbstractMathTask {

    private static String CombineText(boolean x_first, int arg, int ans, Operators op) {
        String text;

        if (x_first)
            text = "X" + op.Symbol + Integer.toString(arg);
        else
            text = Integer.toString(arg) + op.Symbol + "X";

        text += "=" + ans + "  (find X value and give rounded to integer answer)";
        return text;
    }

    public EquationMathTask(boolean x_first, int arg, int ans, Operators op) {
        super((int)(x_first ? op.InverseAFunction(arg, ans)
                : op.InverseBFunction(arg, ans)),
                CombineText(x_first, arg, ans, op));
    }
}
