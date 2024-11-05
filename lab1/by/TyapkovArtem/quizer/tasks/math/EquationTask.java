package lab1.by.TyapkovArtem.quizer.tasks.math;

import lab1.by.TyapkovArtem.quizer.Operation;

public class EquationTask extends AbstractMathTask {
    public EquationTask(boolean numb, int answer, int left, int right , Operation oper) {
        String a;
        if (numb) {
            a = left + " " + oper.Symbol + " X = " + right;
        } else {
            a = "X " + oper.Symbol + " " + left + " = " + right;
        }
        super(a, answer);
    }
}