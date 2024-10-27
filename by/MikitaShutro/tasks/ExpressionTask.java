package by.MikitaShutro.tasks;
import by.MikitaShutro.Operations;

public class ExpressionTask extends MathAbstractTask{
    public ExpressionTask(Integer a, Integer b, Operations sign) {
        super("Найдите " + Integer.toString(a) + sign.Symbol + Integer.toString(b),
                (Integer) sign.Function(a, b));
    }
}
