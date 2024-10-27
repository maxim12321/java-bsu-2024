package by.MikitaShutro.tasks;

import by.MikitaShutro.Operations;

public class EquationTask extends MathAbstractTask{
    public EquationTask (Integer a, Integer b, Operations sign, boolean A) {
        super(A ? "Решите уравнение: x" + sign.Symbol + Integer.toString(b) + "=" + Integer.toString(a) : "Решите уравнение: " + Integer.toString(b) + sign.Symbol +  "x=" + Integer.toString(a),
                A ? sign.InverseAFunction(b, a) : sign.InverseBFunction(b, a));
    }
}
